package com.example.demo.filter;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ClienteFilter {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascDe;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascAte;
}
