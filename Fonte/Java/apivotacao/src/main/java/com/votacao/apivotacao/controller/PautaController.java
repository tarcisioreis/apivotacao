package com.votacao.apivotacao.controller;

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

import com.votacao.apivotacao.dto.PautaDTO;
import com.votacao.apivotacao.exceptions.BusinessException;
import com.votacao.apivotacao.service.PautaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/pautas")
@CrossOrigin(origins="*")
@Api(value="API REST Pauta Controller")
public class PautaController {

	private final PautaService service;
	
	@Autowired
	PautaController(PautaService service) {
		this.service = service;
	}

	@PostMapping
	@ApiOperation(value="Cadastra pautas para votação.")
	ResponseEntity<PautaDTO> save(@Valid @RequestBody PautaDTO pautaDTO, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessException(errors.toString());
		}

		// Valida descricao da pauta
		if (pautaDTO.getDescricao() == null) {
			throw new BusinessException("Descrição da pauta é obrigatório. Verifique preenchimento.");
		}
		
		return new ResponseEntity<>(PautaDTO.valueOf(service.save(pautaDTO)), HttpStatus.CREATED);
	}
	
}
