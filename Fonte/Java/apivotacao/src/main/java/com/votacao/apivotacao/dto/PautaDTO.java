package com.votacao.apivotacao.dto;

import java.io.Serializable;

import com.votacao.apivotacao.entity.Pauta;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PautaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	
	public PautaDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pauta valueOf() {
		return new Pauta(getId(), getDescricao());
	}
	
	public static PautaDTO valueOf(Pauta pauta) {
		return new PautaDTO(pauta.getId(), pauta.getDescricao());
	}

}
