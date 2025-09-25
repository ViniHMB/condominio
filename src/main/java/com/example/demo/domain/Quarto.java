package com.example.demo.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quarto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quarto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private String andar;
	private String mensalidade;

    @OneToMany(mappedBy = "quarto")
    private List<Cliente> clientes; // um quarto pode ter vários cliente
}
