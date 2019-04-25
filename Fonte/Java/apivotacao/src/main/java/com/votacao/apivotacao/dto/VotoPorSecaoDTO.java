package com.votacao.apivotacao.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class VotoPorSecaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long idSecao;
	private Long idAssociado;
	private int voto;
	
	public Long getIdSecao() {
		return idSecao;
	}
	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
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
	
    /**
     * Generic put method to map JPA native Query to this object.
     *
     * @param column
     * @param value
     */
    public void put(Object column, Object value) {
        if (((String) column).equals("idSecao")) {
            setIdSecao((Long) value);
        } else if (((String) column).equals("idAssociado")) {
            setIdAssociado((Long) value);
        } else if (((String) column).equals("voto")) {
            setVoto((int) value);
        }
    }	
	
}
