package com.votacao.apivotacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacao.apivotacao.dto.ContabilizaVotosDTO;
import com.votacao.apivotacao.dto.VotacaoPautaDTO;
import com.votacao.apivotacao.exceptions.BusinessException;
import com.votacao.apivotacao.service.ContabilizaVotosService;
import com.votacao.apivotacao.service.PautaService;
import com.votacao.apivotacao.service.VotacaoPautaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/votacoes")
@CrossOrigin(origins="*")
@Api(value="API REST VotacaoPauta Controller")
public class VotacaoPautaController {

	private final VotacaoPautaService votacaoPautaService;
	private final PautaService pautaService;
	private final ContabilizaVotosService contabilizaVotosService;
	
	@Autowired
	VotacaoPautaController(VotacaoPautaService votacaoPautaService, 
			               PautaService pautaService,
			               ContabilizaVotosService contabilizaVotosService) {
		this.votacaoPautaService = votacaoPautaService;
		this.pautaService = pautaService;
		this.contabilizaVotosService = contabilizaVotosService;
	}
	
	@PostMapping
	@ApiOperation(value="Cadastra votação de associado.")
	ResponseEntity<VotacaoPautaDTO> save(@Valid @RequestBody VotacaoPautaDTO votacaoPautaDTO, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessException(errors.toString());
		}

		// Valida pauta existente
		if (!pautaService.existsById(votacaoPautaDTO.getIdPauta())) {
			throw new BusinessException(String.format("Pauta com id=%d não encontrado", votacaoPautaDTO.getIdPauta()));
		}
		
		// Valida associado não pode votar mais de uma vez na mesma pauta
		if (votacaoPautaService.findByAssociado(votacaoPautaDTO.getIdAssociado(), votacaoPautaDTO.getIdPauta()) != null) {
			throw new BusinessException(String.format("Associado id=%d já votou na pauta com id=%d", votacaoPautaDTO.getIdAssociado(), votacaoPautaDTO.getIdPauta()));
		}
		
		return new ResponseEntity<>(VotacaoPautaDTO.valueOf(votacaoPautaService.save(votacaoPautaDTO)), HttpStatus.CREATED);
	}
	
	@GetMapping
	@ApiOperation(value="Resultado das votações por pauta.")
	ResponseEntity<List<ContabilizaVotosDTO>> contabilizaVotosPorPauta() {
		List<ContabilizaVotosDTO> lista = contabilizaVotosService.contabilizaVotosPorPauta();

		// Valida resultado de votacao
		if (lista == null || lista.isEmpty()) {
			throw new BusinessException("Não foram calculados votos para pautas");
		}
		
		return new ResponseEntity<>(contabilizaVotosService.contabilizaVotosPorPauta(), HttpStatus.OK);
	}
	
}
