package br.com.desenv.microservice.transportador.repository;

import br.com.desenv.microservice.transportador.model.Entrega;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends CrudRepository<Entrega, Long>{

}
