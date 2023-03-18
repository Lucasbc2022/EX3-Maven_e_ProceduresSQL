package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString


public class Cliente {

	private String cpf;
	private String nome;
	private String email;
	private float limite_cartao;
	private String dt_nasci;
}
