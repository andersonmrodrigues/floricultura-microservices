package br.com.desenv.microservice.fornecedor.service;

import br.com.desenv.microservice.fornecedor.model.InfoFornecedor;
import br.com.desenv.microservice.fornecedor.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public InfoFornecedor getInfoPorEstado(String estado) {
        return infoRepository.findByEstado(estado);
    }
}
