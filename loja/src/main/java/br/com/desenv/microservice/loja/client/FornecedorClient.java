package br.com.desenv.microservice.loja.client;

import br.com.desenv.microservice.loja.dto.InfoPedidoDto;
import br.com.desenv.microservice.loja.dto.ItemCompraDto;
import br.com.desenv.microservice.loja.service.InfoFornecedorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @GetMapping("/info/{estado}")
    InfoFornecedorDto getInfoPorEstado(@PathVariable String estado);

    @PostMapping("/pedido")
    InfoPedidoDto realizaPedido(List<ItemCompraDto> itens);
}
