package com.example.demo.service;

import com.example.demo.domain.Usuario;
import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Lista todos os usuários
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new) // converte cada entidade para DTO
                .collect(Collectors.toList());
    }

    // Busca usuário por ID
    public Optional<UsuarioDTO> findById(Integer id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDTO::new);
    }

    // Salva um novo usuário
    public UsuarioDTO save(UsuarioDTO dto) {
        Usuario usuario = dto.toEntity(); // converte DTO para entidade
        Usuario saved = usuarioRepository.save(usuario);
        return new UsuarioDTO(saved); // retorna DTO
    }

    // Atualiza usuário existente
    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        return usuarioRepository.findById(id)
                .map(existing -> {
                    Usuario updated = dto.toEntity();
                    updated.setId(existing.getId()); // mantém o mesmo ID
                    Usuario saved = usuarioRepository.save(updated);
                    return new UsuarioDTO(saved);
                }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deleta usuário
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
