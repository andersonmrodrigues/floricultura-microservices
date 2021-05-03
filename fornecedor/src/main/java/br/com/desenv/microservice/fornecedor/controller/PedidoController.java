package br.com.desenv.microservice.fornecedor.controller;

import br.com.desenv.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.desenv.microservice.fornecedor.model.Pedido;
import br.com.desenv.microservice.fornecedor.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {

	private static final Logger LOG = LoggerFactory.getLogger(PedidoController.class);


	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(method = RequestMethod.POST)
	public Pedido realizaPedido(@RequestBody List<ItemDoPedidoDTO> produtos) {
		LOG.info("pedido recebido");
		return pedidoService.realizaPedido(produtos);
	}
	
	@RequestMapping("/{id}")
	public Pedido getPedidoPorId(@PathVariable Long id) {
		return pedidoService.getPedidoPorId(id);
	}
}
