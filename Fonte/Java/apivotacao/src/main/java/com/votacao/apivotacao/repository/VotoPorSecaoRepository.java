package com.votacao.apivotacao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.votacao.apivotacao.dto.SecaoVotacaoDTO;

@Repository
public class VotoPorSecaoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SecaoVotacaoDTO> findByPauta(@Param("idPauta") Long idPauta) {
		String strSQL = "SELECT id, " + 
		                "       DATE_FORMAT(data_abertura,'%Y-%m-%d %T')   as data_abertura,   " +
				        "       DATE_FORMAT(data_fechamento,'%Y-%m-%d %T') as data_fechamento, " +
		                "       id_pauta " +
				        "FROM secao_votacao " +
		                "WHERE id_pauta = :idPauta";
		System.out.println(strSQL);
		
		List result = entityManager.createNativeQuery(strSQL, SecaoVotacaoDTO.class)
								   .setParameter("idPauta", idPauta)	
				                   .getResultList();

		return result;
	}
	
}
