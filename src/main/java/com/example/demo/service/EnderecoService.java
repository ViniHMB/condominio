package com.example.demo.service;

import com.example.demo.domain.Endereco;
import com.example.demo.DTO.EnderecoDTO;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private EnderecoDTO toDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getNumero(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getComplemento()
        );
    }

    private Endereco toEntity(EnderecoDTO dto) {
        return new Endereco(
                dto.getId(),
                dto.getNumero(),
                dto.getLogradouro(),
                dto.getBairro(),
                dto.getCep(),
                dto.getCidade(),
                dto.getUf(),
                dto.getComplemento()
        );
    }

    public List<EnderecoDTO> findAll() {
        return enderecoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EnderecoDTO> findById(Integer id) {
        return enderecoRepository.findById(id)
                .map(this::toDTO);
    }

    public EnderecoDTO save(EnderecoDTO dto) {
        Endereco endereco = toEntity(dto);
        return toDTO(enderecoRepository.save(endereco));
    }

    public EnderecoDTO update(Integer id, EnderecoDTO dto) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setNumero(dto.getNumero());
                    endereco.setLogradouro(dto.getLogradouro());
                    endereco.setBairro(dto.getBairro());
                    endereco.setCep(dto.getCep());
                    endereco.setCidade(dto.getCidade());
                    endereco.setUf(dto.getUf());
                    endereco.setComplemento(dto.getComplemento());
                    return toDTO(enderecoRepository.save(endereco));
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public void delete(Integer id) {
        enderecoRepository.deleteById(id);
    }
}
