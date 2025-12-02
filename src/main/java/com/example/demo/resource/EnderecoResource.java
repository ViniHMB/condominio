package com.example.demo.resource;

import com.example.demo.DTO.EnderecoDTO;
import com.example.demo.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDTO> listarTodos() {
        return enderecoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable ("id") Integer id) {
        return enderecoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnderecoDTO criar(@RequestBody EnderecoDTO dto) {
        return enderecoService.save(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable ("id") Integer id, @RequestBody EnderecoDTO dto) {
        try {
            return ResponseEntity.ok(enderecoService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable ("id") Integer id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
