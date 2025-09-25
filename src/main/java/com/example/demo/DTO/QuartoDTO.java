package com.example.demo.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.Quarto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoDTO {
    private Integer id;
    private String numero;
    private String andar;
    private String mensalidade;
    private List<ClienteDTO> clientes; // <<< Novo

    public QuartoDTO(Quarto quarto) {
        this.id = quarto.getId();
        this.numero = quarto.getNumero();
        this.andar = quarto.getAndar();
        this.mensalidade = quarto.getMensalidade();

        if (quarto.getClientes() != null) {
            this.clientes = quarto.getClientes().stream()
                                  .map(ClienteDTO::new)
                                  .collect(Collectors.toList());
        }
    }

    public Quarto toEntity() {
        Quarto quarto = new Quarto();
        quarto.setId(this.id);
        quarto.setNumero(this.numero);
        quarto.setAndar(this.andar);
        quarto.setMensalidade(this.mensalidade);

        if (this.clientes != null) {
            quarto.setClientes(this.clientes.stream()
                                            .map(ClienteDTO::toEntity)
                                            .collect(Collectors.toList()));
        }

        return quarto;
    }
}