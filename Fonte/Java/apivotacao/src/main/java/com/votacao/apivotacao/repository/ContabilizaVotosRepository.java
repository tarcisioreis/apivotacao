package com.votacao.apivotacao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.votacao.apivotacao.dto.ContabilizaVotosDTO;

@Repository
public class ContabilizaVotosRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ContabilizaVotosDTO> contabilizaVotosPorPauta() {
		String strSQL = "SELECT descricao, SUM(sim) as sim, SUM(nao) as nao " + 
				        "FROM (" + 
				        "SELECT p.descricao, " + 
				        "       COALESCE(case when vp.voto = 1 then count(vp.voto) end, 0) as sim, " + 
				        "       COALESCE(case when vp.voto = 0 then count(vp.voto) end, 0) as nao  " + 
				        "FROM pauta p " + 
				        "     INNER JOIN votacao_pauta vp ON vp.id_pauta = p.id " + 
				        "GROUP BY p.descricao, vp.voto) as q " + 
				        "GROUP BY descricao";
		List result = entityManager.createNativeQuery(strSQL, ContabilizaVotosDTO.class).getResultList();
		
		return result;
	}
	
}
