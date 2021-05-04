package br.com.desenv.microservice.loja.service;

import br.com.desenv.microservice.loja.client.FornecedorClient;
import br.com.desenv.microservice.loja.client.TransportadorClient;
import br.com.desenv.microservice.loja.dto.CompraDto;
import br.com.desenv.microservice.loja.dto.InfoEntregaDto;
import br.com.desenv.microservice.loja.dto.InfoPedidoDto;
import br.com.desenv.microservice.loja.dto.VoucherDto;
import br.com.desenv.microservice.loja.model.Compra;
import br.com.desenv.microservice.loja.model.CompraState;
import br.com.desenv.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private TransportadorClient transportadorClient;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    public Compra reprocessaCompra(Long id) {
        return null;
    }

    public Compra cancelaCompra(Long id) {
        return null;
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
    public Compra realizaCompra(CompraDto compra) {

        Compra compraSalva = new Compra();
        compraSalva.setState(CompraState.RECEBIDO);
        compraRepository.save(compraSalva);
        compra.setCompraId(compraSalva.getId());

        InfoFornecedorDto infoFornecedorDto = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        InfoPedidoDto pedido = fornecedorClient.realizaPedido(compra.getItens());
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraSalva.setState(CompraState.PEDIDO_REALIZADO);
        compraRepository.save(compraSalva);

        InfoEntregaDto entrega = new InfoEntregaDto();
        entrega.setPedidoId(pedido.getId());
        entrega.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entrega.setEnderecoOrigem(infoFornecedorDto.getEndereco());
        entrega.setEnderecoDestino(compra.getEndereco().toString());
        VoucherDto voucher = transportadorClient.reservaEntrega(entrega);
        compraSalva.setDataParaEntrega(entrega.getDataParaEntrega());
        compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
        compraSalva.setVoucher(voucher.getNumero());
        compraRepository.save(compraSalva);

        compraRepository.save(compraSalva);
        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDto compra) {
        if (compra.getCompraId() != null) {
            return compraRepository.findById(compra.getCompraId()).get();
        }
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compra.getEndereco().toString());
        return compraFallback;
    }


}
