package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")  // nome da tabela no banco
@Data                     // gera getters, setters, toString, equals e hashCode
@NoArgsConstructor         // gera construtor sem argumentos
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String complemento;

}
