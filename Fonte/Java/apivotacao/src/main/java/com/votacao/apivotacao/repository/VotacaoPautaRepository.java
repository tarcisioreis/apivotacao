package com.votacao.apivotacao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.votacao.apivotacao.dto.VotacaoPautaDTO;
import com.votacao.apivotacao.entity.VotacaoPauta;

@Repository
public interface VotacaoPautaRepository extends CrudRepository<VotacaoPauta, Long> {

	@Query("SELECT new com.votacao.apivotacao.dto.VotacaoPautaDTO(vp.id, vp.idPauta, vp.idAssociado, vp.voto) " + 
		       "FROM VotacaoPauta vp " + 
			   "WHERE vp.idAssociado = :idAssociado AND vp.idPauta = :idPauta")
	public VotacaoPautaDTO findByAssociado(@Param("idAssociado") Long idAssociado, @Param("idPauta") Long idPauta);
}
