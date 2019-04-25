package com.votacao.apivotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacao.apivotacao.dto.SecaoVotacaoDTO;
import com.votacao.apivotacao.entity.SecaoVotacao;
import com.votacao.apivotacao.repository.SecaoVotacaoRepository;

@Service
public class SecaoVotacaoService {

	private final SecaoVotacaoRepository secaoVotacaoRepository;
	
	@Autowired
	public SecaoVotacaoService(SecaoVotacaoRepository secaoVotacaoRepository) {
		this.secaoVotacaoRepository = secaoVotacaoRepository;
	}
	
	public SecaoVotacao save(SecaoVotacaoDTO secaoVotacaoDTO) {
		return secaoVotacaoRepository.save(secaoVotacaoDTO.valueOf());
	}
	
	public SecaoVotacaoDTO findById(Long id) {
		return SecaoVotacaoDTO.valueOf(secaoVotacaoRepository.findById(id));
	}
	
}
