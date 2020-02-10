package com.votacao.apivotacao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;

@Entity
@Table(name = "secao_votacao")
@Getter
@Setter
@NoArgsConstructor
public class SecaoVotacao implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="data_abertura", nullable=false)
	private LocalDateTime dataAbertura;
	
	@Column(name="data_fechamento", nullable=false)
	private LocalDateTime dataFechamento;
	 
	@Column(nullable=false)
	private Long idPauta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPauta", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Pauta pauta;

	public SecaoVotacao(Long id, String dataAbertura, String dataFechamento, Long idPauta) {
		this.id = id;
		this.dataAbertura = LocalDateTime.parse(dataAbertura, formatter);
		
		if (dataFechamento != null) {
			this.dataFechamento = LocalDateTime.parse(dataFechamento, formatter);
		}
		
		this.idPauta = idPauta;
	}

}
