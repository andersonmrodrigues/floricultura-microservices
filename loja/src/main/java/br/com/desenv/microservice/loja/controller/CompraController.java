package br.com.desenv.microservice.loja.controller;

import br.com.desenv.microservice.loja.dto.CompraDto;
import br.com.desenv.microservice.loja.model.Compra;
import br.com.desenv.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("")
    public ResponseEntity<Compra> realizaCompra(@RequestBody CompraDto dto) {
        Compra compra = compraService.realizaCompra(dto);
        return ResponseEntity.ok(compra);
    }

    @GetMapping("/{id}")
    public Compra getByid(@PathVariable("id") Long id) {
        return compraService.getById(id);
    }
}
