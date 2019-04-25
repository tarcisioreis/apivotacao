package com.votacao.apivotacao.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.votacao.apivotacao.entity.VotacaoPauta;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class VotacaoPautaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private Long idPauta;
	private Long idAssociado;
	private int voto;

	public VotacaoPautaDTO(Long id, Long idPauta, Long idAssociado, int voto) {
		this.id = id;
		this.idPauta = idPauta;
		this.idAssociado = idAssociado;
		this.voto = voto;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPauta() {
		return idPauta;
	}
	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}

	public Long getIdAssociado() {
		return idAssociado;
	}
	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}

	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		this.voto = voto;
	}

	public VotacaoPauta valueOf() {
		return new VotacaoPauta(getId(), 
				                getIdPauta(), 
				                getIdAssociado(), 
				                getVoto());
	}
	
	public static VotacaoPautaDTO valueOf(VotacaoPauta votacaoPauta) {
		return new VotacaoPautaDTO(votacaoPauta.getId(), 
				                   votacaoPauta.getIdPauta(), 
				                   votacaoPauta.getIdAssociado(), 
				                   votacaoPauta.getVoto());
	}
	
}
