package br.com.desenv.microservice.loja.service;

import br.com.desenv.microservice.loja.client.FornecedorClient;
import br.com.desenv.microservice.loja.dto.CompraDto;
import br.com.desenv.microservice.loja.dto.InfoPedidoDto;
import br.com.desenv.microservice.loja.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorClient fornecedorClient;

    public Compra realizaCompra(CompraDto compra) {
        final String estado = compra.getEndereco().getEstado();
        LOG.info("buscando informações do fornecedor de {}", estado);
        InfoFornecedorDto infoFornecedorDto = fornecedorClient.getInfoPorEstado(estado);
        LOG.info("localizando um pedido");
        InfoPedidoDto pedidoDto = fornecedorClient.realizaPedido(compra.getItens());
        Compra compraSalva = new Compra();
        compraSalva.setPedidoId(pedidoDto.getId());
        compraSalva.setTempoDePreparo(pedidoDto.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        return compraSalva;
    }
}
