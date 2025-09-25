package com.example.demo.DTO;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String numero;
    private String cpf;
    private String email;
    private String senha;
    private String celular;
    private EnderecoDTO endereco;

    // Construtor que recebe a entidade Usuario
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.numero = usuario.getNumero();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.celular = usuario.getCelular();
        if (usuario.getEndereco() != null) {
            Endereco e = usuario.getEndereco();
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
    }

    // Converte DTO para entidade Usuario
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNumero(this.numero);
        usuario.setCpf(this.cpf);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setCelular(this.celular);

        if (this.endereco != null) {
            EnderecoDTO dto = this.endereco;
            Endereco e = new Endereco();
            e.setId(dto.getId());
            e.setNumero(dto.getNumero());
            e.setLogradouro(dto.getLogradouro());
            e.setBairro(dto.getBairro());
            e.setCep(dto.getCep());
            e.setCidade(dto.getCidade());
            e.setUf(dto.getUf());
            e.setComplemento(dto.getComplemento());
            usuario.setEndereco(e);
        }

        return usuario;
    }
}
