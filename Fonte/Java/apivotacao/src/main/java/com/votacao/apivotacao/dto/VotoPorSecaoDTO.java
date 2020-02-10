package com.votacao.apivotacao.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VotoPorSecaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long idSecao;
	private Long idAssociado;
	private int voto;
	
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
