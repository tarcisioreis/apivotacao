package com.votacao.apivotacao.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacao.apivotacao.dto.SecaoVotacaoDTO;
import com.votacao.apivotacao.dto.VotacaoPautaDTO;
import com.votacao.apivotacao.dto.VotoPorSecaoDTO;
import com.votacao.apivotacao.exceptions.BusinessException;
import com.votacao.apivotacao.service.PautaService;
import com.votacao.apivotacao.service.SecaoVotacaoService;
import com.votacao.apivotacao.service.VotacaoPautaService;
import com.votacao.apivotacao.service.VotoPorSecaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/secaovotacoes")
@CrossOrigin(origins="*")
@Api(value="API REST SecaoVotacao Controller")
public class SecaoVotacaoController {

	private final SecaoVotacaoService secaoVotacaoService;
	private final PautaService pautaService;
	private final VotacaoPautaService votacaoPautaService;
	private final VotoPorSecaoService votoPorSecaoService;
	
	@Autowired
	SecaoVotacaoController(SecaoVotacaoService secaoVotacaoService, 
			               PautaService pautaService,
			               VotacaoPautaService votacaoPautaService,
			               VotoPorSecaoService votoPorSecaoService) {
		this.secaoVotacaoService = secaoVotacaoService;
		this.pautaService = pautaService;
		this.votacaoPautaService = votacaoPautaService;
		this.votoPorSecaoService = votoPorSecaoService;
	}
	
	@PostMapping("/save")
	@ApiOperation(value="Cadastra seções para votação.")
	ResponseEntity<SecaoVotacaoDTO> save(@Valid @RequestBody SecaoVotacaoDTO secaoVotacaoDTO, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessException(errors.toString());
		}

		// Valida pauta existtente
		if (!pautaService.existsById(secaoVotacaoDTO.getIdPauta())) {
			throw new BusinessException(String.format("Pauta com id=%d não encontrado", secaoVotacaoDTO.getIdPauta()));
		}

		// Valida data de fechamento nao pode ser menor que data de abertura
		if (secaoVotacaoDTO.getDataFechamento() != null && 
			secaoVotacaoDTO.getDataFechamento().isBefore(secaoVotacaoDTO.getDataAbertura())) {
			throw new BusinessException("Data de encerramento da votação não pode ser menor que a data de abertura");
		}
		
		// Valida data de fechamento nao pode nula
		if (secaoVotacaoDTO.getDataFechamento() == null) { 
			secaoVotacaoDTO.setDataFechamento(secaoVotacaoDTO.getDataAbertura().plusMinutes(1));
		}	

		// Valida secao de votacao encerrada ou ainda aberta 
		List<SecaoVotacaoDTO> listSecaoVotacaoDTO = votoPorSecaoService.findByPauta(secaoVotacaoDTO.getIdPauta());
		
		for(SecaoVotacaoDTO secao : listSecaoVotacaoDTO) {
			if (secao.getDataFechamento() != null && 
				secao.getDataFechamento().isBefore(secaoVotacaoDTO.getDataFechamento())) {
				throw new BusinessException("Seção de Votação encerrada.");
			}
			if (secao.getDataFechamento() != null &&
				secao.getDataFechamento().isAfter(secaoVotacaoDTO.getDataFechamento())) {
				throw new BusinessException("Seção de Votação continua aberta.");
			}
		}
		
		return new ResponseEntity<>(SecaoVotacaoDTO.valueOf(secaoVotacaoService.save(secaoVotacaoDTO)), HttpStatus.CREATED);
	}

	@PostMapping("/votoPorSecao")
	@ApiOperation(value="Seção para votação, salva voto de associado antes de expirar sessão de voto.")
	ResponseEntity<SecaoVotacaoDTO> votoPorSecao(@Valid @RequestBody VotoPorSecaoDTO votoPorSecaoDTO, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessException(errors.toString());
		}

		// Valida secao existente
		if (votoPorSecaoDTO.getIdSecao() == null || votoPorSecaoDTO.getIdSecao() <= 0) {
			throw new BusinessException("Seção de Votação não encontrado");
		}
		
		SecaoVotacaoDTO secaoVotacaoDTO = secaoVotacaoService.findById(votoPorSecaoDTO.getIdSecao());
		
		if (secaoVotacaoDTO == null) {
			throw new BusinessException(String.format("Seção de Votação com id=%d não encontrado", votoPorSecaoDTO.getIdSecao()));
		} 
		
		// Valida secao de votacao encerrada
		if (secaoVotacaoDTO.getDataFechamento() != null && 
			secaoVotacaoDTO.getDataFechamento().isBefore(LocalDateTime.now())) {
			throw new BusinessException("Seção de Votação encerrada.");
		}

		// Valida secao de votacao em aberto
		if (secaoVotacaoDTO.getDataFechamento() != null &&
			secaoVotacaoDTO.getDataFechamento().isAfter(LocalDateTime.now())) {
			throw new BusinessException("Seção de Votação continua aberta.");
		}
		
		// Valida associado nao pode votar mais de uma vez para mesma pauta
		if (votacaoPautaService.findByAssociado(votoPorSecaoDTO.getIdAssociado(), secaoVotacaoDTO.getIdPauta()) != null) {
			throw new BusinessException(String.format("Associado id=%d já votou na pauta com id=%d", votoPorSecaoDTO.getIdAssociado(), secaoVotacaoDTO.getIdPauta()));
		} else {
			votacaoPautaService.save(new VotacaoPautaDTO(0L, secaoVotacaoDTO.getIdPauta(), 
					                                         votoPorSecaoDTO.getIdAssociado(), 
					                                         votoPorSecaoDTO.getVoto()));
		}
		
		return new ResponseEntity<>(SecaoVotacaoDTO.valueOf(secaoVotacaoService.save(secaoVotacaoDTO)), HttpStatus.CREATED);
	}
	
}
