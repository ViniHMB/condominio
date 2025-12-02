package com.example.demo.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.domain.Cliente;
import com.example.demo.filter.ClienteFilter;

public interface ClienteRepositoryQuery {
    Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable);
}
