package br.com.desenv.microservice.loja.service;

import br.com.desenv.microservice.loja.dto.CompraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {

    @Autowired
    private RestTemplate client;

    public void realizaCompra(CompraDto compra) {
        ResponseEntity<InfoFornecedorDto> exchange = client.exchange(
                "http://fornecedor/info/" + compra.getEndereco().getEstado(),
                HttpMethod.GET,
                null,
                InfoFornecedorDto.class);
        System.out.println(exchange.getBody().getEndereco());
    }
}
