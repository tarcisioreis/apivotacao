package com.votacao.apivotacao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "votacao_pauta")
@Getter
@Setter
@NoArgsConstructor
public class VotacaoPauta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private Long idPauta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPauta", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Pauta pauta;
	
	@Column(nullable=false)
	private Long idAssociado;
	private int voto;

	public VotacaoPauta(Long id, Long idPauta, Long idAssociado, int voto) {
		this.id = id;
		this.idPauta = idPauta;
		this.idAssociado = idAssociado;
		this.voto = voto;
	}
}
