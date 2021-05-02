package br.com.desenv.microservice.fornecedor.repository;

import br.com.desenv.microservice.fornecedor.model.InfoFornecedor;
import org.springframework.data.repository.CrudRepository;

public interface InfoRepository extends CrudRepository<InfoFornecedor, Long> {

    InfoFornecedor findByEstado(String estado);

}
