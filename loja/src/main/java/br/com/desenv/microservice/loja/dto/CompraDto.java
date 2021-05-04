package br.com.desenv.microservice.loja.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CompraDto {

    @JsonIgnore
    private Long compraId;
    private List<ItemCompraDto> itens;
    private EnderecoDto endereco;

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public List<ItemCompraDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraDto> itens) {
        this.itens = itens;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }
}
