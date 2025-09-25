package com.example.demo.DTO;

import com.example.demo.domain.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Integer id;
    private String numero;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    
    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.numero = endereco.getNumero();
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.complemento = endereco.getComplemento();
    }

    // Converte DTO para entidade
    public Endereco toEntity() {
        Endereco e = new Endereco();
        e.setId(this.id);
        e.setLogradouro(this.logradouro);
        e.setNumero(this.numero);
        e.setBairro(this.bairro);
        e.setCep(this.cep);
        e.setCidade(this.cidade);
        e.setBairro(this.uf);
        e.setComplemento(this.complemento);
        return e;
    }
}
