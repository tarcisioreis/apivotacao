package com.votacao.apivotacao.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "pauta")
@Getter
@Setter
@NoArgsConstructor
public class Pauta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String descricao;

	@OneToMany(targetEntity = VotacaoPauta.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<VotacaoPauta> votacaoPautas;

	public Pauta(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

}
