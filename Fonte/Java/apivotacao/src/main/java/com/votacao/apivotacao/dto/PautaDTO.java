package com.votacao.apivotacao.dto;

import com.votacao.apivotacao.entity.Pauta;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	
	public Pauta valueOf() {
		return new Pauta(getId(), getDescricao());
	}
	
	public static PautaDTO valueOf(Pauta pauta) {
		return new PautaDTO(pauta.getId(), pauta.getDescricao());
	}

}
