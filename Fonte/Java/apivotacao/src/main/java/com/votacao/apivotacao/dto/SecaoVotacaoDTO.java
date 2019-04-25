package com.votacao.apivotacao.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.votacao.apivotacao.entity.SecaoVotacao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SecaoVotacaoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 

	@Id
	private Long id;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;
	private Long idPauta;
	
	public SecaoVotacaoDTO() {
		super();
	}
	
	public SecaoVotacaoDTO(Long id, String dataAbertura, String dataFechamento, Long idPauta) {
		this.id = id;
		this.dataAbertura = LocalDateTime.parse(dataAbertura, formatter);
		
		if (dataFechamento != null) {
			this.dataFechamento = LocalDateTime.parse(dataFechamento, formatter);
		}
		
		this.idPauta = idPauta;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	public Long getIdPauta() {
		return idPauta;
	}
	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}

	public SecaoVotacao valueOf() {
		return new SecaoVotacao(getId(), 
				                getDataAbertura().format(formatter), 
				                getDataFechamento().format(formatter), 
				                getIdPauta());
	}
	
	public static SecaoVotacaoDTO valueOf(SecaoVotacao secaoVotacao) {
		return new SecaoVotacaoDTO(secaoVotacao.getId(), 
				                   secaoVotacao.getDataAbertura().format(formatter), 
				                   secaoVotacao.getDataFechamento().format(formatter), 
				                   secaoVotacao.getIdPauta());
	}

	public static SecaoVotacaoDTO valueOf(Optional<SecaoVotacao> secaoVotacao) {
		if (secaoVotacao.isPresent()) {
			return new SecaoVotacaoDTO(secaoVotacao.get().getId(), 
					                   secaoVotacao.get().getDataAbertura().format(formatter), 
					                   secaoVotacao.get().getDataFechamento().format(formatter), 
					                   secaoVotacao.get().getIdPauta());
		} else {
			return (SecaoVotacaoDTO) Collections.EMPTY_LIST;
		}
	}
	
}
