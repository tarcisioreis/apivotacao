package com.votacao.apivotacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacao.apivotacao.dto.ContabilizaVotosDTO;
import com.votacao.apivotacao.repository.ContabilizaVotosRepository;

@Service
public class ContabilizaVotosService {

	private final ContabilizaVotosRepository contabilizaVotosRepository;
	
	@Autowired
	public ContabilizaVotosService(ContabilizaVotosRepository contabilizaVotosRepository) {
		this.contabilizaVotosRepository = contabilizaVotosRepository;
	}

	public List<ContabilizaVotosDTO> contabilizaVotosPorPauta() {
		return contabilizaVotosRepository.contabilizaVotosPorPauta();
	}
	
}
