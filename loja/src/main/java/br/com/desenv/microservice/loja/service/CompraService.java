package br.com.desenv.microservice.loja.service;

import br.com.desenv.microservice.loja.client.FornecedorClient;
import br.com.desenv.microservice.loja.dto.CompraDto;
import br.com.desenv.microservice.loja.dto.InfoPedidoDto;
import br.com.desenv.microservice.loja.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private FornecedorClient fornecedorClient;

    public Compra realizaCompra(CompraDto compra) {
        InfoFornecedorDto infoFornecedorDto = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        InfoPedidoDto pedidoDto = fornecedorClient.realizaPedido(compra.getItens());
        Compra compraSalva = new Compra();
        compraSalva.setPedidoId(pedidoDto.getId());
        compraSalva.setTempoDePreparo(pedidoDto.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        return compraSalva;
    }
}
