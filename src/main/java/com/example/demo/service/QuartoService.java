package com.example.demo.service;

import com.example.demo.domain.Quarto;
import com.example.demo.domain.Cliente;
import com.example.demo.DTO.QuartoDTO;
import com.example.demo.DTO.ClienteDTO;
import com.example.demo.repository.QuartoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public List<QuartoDTO> findAll() {
        return quartoRepository.findAll()
                .stream()
                .map(QuartoDTO::new)
                .collect(Collectors.toList());
    }

    public QuartoDTO findById(Integer id) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
        return new QuartoDTO(quarto);
    }

    public QuartoDTO save(QuartoDTO dto) {
        Quarto quarto = dto.toEntity();
        return new QuartoDTO(quartoRepository.save(quarto));
    }

    public QuartoDTO update(Integer id, QuartoDTO dto) {
        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        quarto.setNumero(dto.getNumero());
        quarto.setAndar(dto.getAndar());
        quarto.setMensalidade(dto.getMensalidade());

        // Atualiza clientes, se vierem no DTO
        if (dto.getClientes() != null) {
            List<Cliente> clientes = dto.getClientes()
                    .stream()
                    .map(ClienteDTO::toEntity)
                    .collect(Collectors.toList());
            quarto.setClientes(clientes);
        }

        return new QuartoDTO(quartoRepository.save(quarto));
    }

    public void delete(Integer id) {
        if (!quartoRepository.existsById(id)) {
            throw new RuntimeException("Quarto não encontrado");
        }
        quartoRepository.deleteById(id);
    }
}
