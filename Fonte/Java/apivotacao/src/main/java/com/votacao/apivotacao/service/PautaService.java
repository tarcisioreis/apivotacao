package com.votacao.apivotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacao.apivotacao.dto.PautaDTO;
import com.votacao.apivotacao.entity.Pauta;
import com.votacao.apivotacao.repository.PautaRepository;

@Service
public class PautaService {

	private final PautaRepository pautaRepository;
	
	@Autowired
	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}
	
	public Pauta save(PautaDTO pautaDTO) {
        return pautaRepository.save(pautaDTO.valueOf());
    }
	
	public boolean existsById(Long id) {
		return pautaRepository.existsById(id);
	}
	
}
