package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.domain.Cliente;
import com.example.demo.filter.ClienteFilter;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/filtro")
    public Page<ClienteDTO> filtrarClientes(ClienteFilter filter, Pageable pageable) {
        Page<Cliente> clientes = clienteService.filtrar(filter, pageable);

        // converte a lista de entidades para DTOs
        return clientes.map(ClienteDTO::new);
    }
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable ("id") Integer id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteDTO criar(@RequestBody ClienteDTO dto) {
        return clienteService.save(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable ("id") Integer id, @RequestBody ClienteDTO dto) {
        try {
            return ResponseEntity.ok(clienteService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable ("id") Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
