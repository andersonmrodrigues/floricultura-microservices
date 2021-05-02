package br.com.desenv.microservice.fornecedor.repository;

import br.com.desenv.microservice.fornecedor.model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
