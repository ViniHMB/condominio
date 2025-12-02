package com.example.demo.DTO;

import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Quarto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private Date dataNasc;
    private EnderecoDTO endereco;
    private String email;
    private Integer quartoId;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataNasc = cliente.getDataNasc();
        this.email = cliente.getEmail();
        if (cliente.getEndereco() != null) {
            Endereco e = cliente.getEndereco();
            this.endereco = new EnderecoDTO(
                e.getId(),
                e.getNumero(),
                e.getLogradouro(),
                e.getBairro(),
                e.getCep(),
                e.getCidade(),
                e.getUf(),
                e.getComplemento()
            );
        }
        if (cliente.getQuarto() != null) {
            this.quartoId = cliente.getQuarto().getId();
        }
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setSobrenome(this.sobrenome);
        cliente.setCpf(this.cpf);
        cliente.setTelefone(this.telefone);
        cliente.setDataNasc(this.dataNasc);
        cliente.setEmail(this.email);

        if (this.endereco != null) {
            cliente.setEndereco(this.endereco.toEntity());
        }

        if (this.quartoId != null) {
            Quarto quarto = new Quarto();
            quarto.setId(this.quartoId);
            cliente.setQuarto(quarto);
        }

        return cliente;
    }
}
