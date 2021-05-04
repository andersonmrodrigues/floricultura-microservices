package br.com.desenv.microservice.loja.client;

import br.com.desenv.microservice.loja.dto.InfoEntregaDto;
import br.com.desenv.microservice.loja.dto.VoucherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("transportador")
public interface TransportadorClient {

    @PostMapping("/entrega")
    public VoucherDto reservaEntrega(@RequestBody InfoEntregaDto pedidoDTO);
}
