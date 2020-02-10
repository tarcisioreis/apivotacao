package com.votacao.apivotacao.dto;

import com.votacao.apivotacao.entity.VotacaoPauta;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
