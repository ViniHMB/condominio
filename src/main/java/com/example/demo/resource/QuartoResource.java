package com.example.demo.resource;

import com.example.demo.DTO.QuartoDTO;
import com.example.demo.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoResource {

    @Autowired
    private QuartoService quartoService;

    // Listar todos
    @GetMapping
    public List<QuartoDTO> listarTodos() {
        return quartoService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<QuartoDTO> findById(@PathVariable ("id") Integer id) {
        try {
            QuartoDTO dto = quartoService.findById(id); // retorna QuartoDTO ou lança
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Criar quarto
    @PostMapping
    public QuartoDTO criar(@RequestBody QuartoDTO dto) {
        return quartoService.save(dto);
    }

    // Atualizar quarto
    @PutMapping("/{id}")
    public ResponseEntity<QuartoDTO> atualizar(@PathVariable ("id") Integer id, @RequestBody QuartoDTO dto) {
        try {
            return ResponseEntity.ok(quartoService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar quarto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable ("id") Integer id) {
        try {
            quartoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
