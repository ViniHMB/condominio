package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.domain.Cliente;
import com.example.demo.filter.ClienteFilter;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable) {
        return clienteRepository.filtrar(filter, pageable);
    }

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
		
	}

    // Busca cliente por ID
    public Optional<ClienteDTO> findById(Integer id) {
        return clienteRepository.findById(id)
                .map(ClienteDTO::new);
    }

    // Salva um novo cliente
    public ClienteDTO save(ClienteDTO dto) {
        Cliente cliente = dto.toEntity(); // converte DTO para entidade
        Cliente saved = clienteRepository.save(cliente);
        return new ClienteDTO(saved); // retorna DTO
    }

    // Atualiza cliente existente
    public ClienteDTO update(Integer id, ClienteDTO dto) {
        return clienteRepository.findById(id)
                .map(existing -> {
                    Cliente updated = dto.toEntity();
                    updated.setId(existing.getId()); // mantém o mesmo ID
                    Cliente saved = clienteRepository.save(updated);
                    return new ClienteDTO(saved);
                }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deleta cliente
    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }
}
