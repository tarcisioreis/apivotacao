package com.votacao.apivotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.votacao.apivotacao.dto.VotacaoPautaDTO;
import com.votacao.apivotacao.entity.VotacaoPauta;
import com.votacao.apivotacao.repository.VotacaoPautaRepository;

@Service
public class VotacaoPautaService {

	private final VotacaoPautaRepository votacaoPautaRepository;
	
	@Autowired
	public VotacaoPautaService(VotacaoPautaRepository votacaoPautaRepository) {
		this.votacaoPautaRepository = votacaoPautaRepository;
	}
	
	public VotacaoPauta save(VotacaoPautaDTO votacaoPautaDTO) {
        return votacaoPautaRepository.save(votacaoPautaDTO.valueOf());
    }

	public VotacaoPautaDTO findByAssociado(@Param("idAssociado") Long idAssociado, @Param("idPauta") Long idPauta) {
		return votacaoPautaRepository.findByAssociado(idAssociado, idPauta);
	}
	
}
