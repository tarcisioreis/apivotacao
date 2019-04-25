package com.votacao.apivotacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.votacao.apivotacao.dto.SecaoVotacaoDTO;
import com.votacao.apivotacao.repository.VotoPorSecaoRepository;

@Service
public class VotoPorSecaoService {
	
	private final VotoPorSecaoRepository votoPorSecaoRepository;
	
	@Autowired
	public VotoPorSecaoService(VotoPorSecaoRepository votoPorSecaoRepository) {
		this.votoPorSecaoRepository = votoPorSecaoRepository;
	}
	
	public List<SecaoVotacaoDTO> findByPauta(@Param("idPauta") Long idPauta) {
		return votoPorSecaoRepository.findByPauta(idPauta);
	}

}
