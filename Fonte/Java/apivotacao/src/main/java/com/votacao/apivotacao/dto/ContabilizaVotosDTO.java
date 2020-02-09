package com.votacao.apivotacao.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContabilizaVotosDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String descricao;
	private int sim;
	private int nao;

    /**
     * Generic put method to map JPA native Query to this object.
     *
     * @param column
     * @param value
     */
    public void put(Object column, Object value) {
        if (((String) column).equals("descricao")) {
            setDescricao((String) value);
        } else if (((String) column).equals("sim")) {
            setSim((int) value);
        } else if (((String) column).equals("nao")) {
            setNao((int) value);
        }
    }	
    
}
