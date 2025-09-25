package com.example.demo.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")  // nome da tabela no banco
@Data                     // gera getters, setters, toString, equals e hashCode
@NoArgsConstructor         // gera construtor sem argumentos
@AllArgsConstructor        // gera construtor com todos os argumentos
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String sobrenome;

    private String cpf;
    private String telefone;
    private Date dataNasc;
    @OneToOne
    private Endereco endereco;
    private String email;
    @ManyToOne
    @JoinColumn(name = "quarto_id")
    @JsonIgnore
    private Quarto quarto;

}
