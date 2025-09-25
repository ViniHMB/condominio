package com.example.demo.service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Quarto;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final QuartoRepository quartoRepository;

    public ClienteService(ClienteRepository clienteRepository, QuartoRepository quartoRepository) {
        this.clienteRepository = clienteRepository;
        this.quartoRepository = quartoRepository;
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
    }

    public ClienteDTO findById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteDTO(cliente);
    }

    public ClienteDTO save(ClienteDTO dto) {
        Cliente cliente = dto.toEntity();

        // Valida se o quarto existe
        if (dto.getQuartoId() != null) {
            Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                    .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
            cliente.setQuarto(quarto);
        }

        return new ClienteDTO(clienteRepository.save(cliente));
    }

    public ClienteDTO update(Integer id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        cliente.setDataNasc(dto.getDataNasc());
        cliente.setEmail(dto.getEmail());

        if (dto.getEndereco() != null) {
            cliente.setEndereco(dto.getEndereco().toEntity());
        }

        if (dto.getQuartoId() != null) {
            Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                    .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
            cliente.setQuarto(quarto);
        } else {
            cliente.setQuarto(null);
        }

        return new ClienteDTO(clienteRepository.save(cliente));
    }

    public void delete(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }
}
