package com.votacao.apivotacao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.votacao.apivotacao.entity.Pauta;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {

}
