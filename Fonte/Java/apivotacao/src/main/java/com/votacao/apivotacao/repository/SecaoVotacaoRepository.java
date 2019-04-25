package com.votacao.apivotacao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.votacao.apivotacao.entity.SecaoVotacao;

@Repository
public interface SecaoVotacaoRepository extends CrudRepository<SecaoVotacao, Long>{

}
