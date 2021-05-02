package br.com.desenv.microservice.loja.controller;

import br.com.desenv.microservice.loja.dto.CompraDto;
import br.com.desenv.microservice.loja.model.Compra;
import br.com.desenv.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
