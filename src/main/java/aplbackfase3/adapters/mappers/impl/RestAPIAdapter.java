package aplbackfase3.adapters.mappers.impl;

import aplbackfase3.adapters.mappers.interfaces.IRestAPIAdapter;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.web.rest.produto.response.ProdutoResponse;
import org.springframework.stereotype.Component;

@Component
public class RestAPIAdapter implements IRestAPIAdapter {
    @Override
    public ProdutoResponse from(Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getIdProduto())
                .nome(produto.getNomeProduto().getNome())
                .descricao(produto.getDescricaoProduto().getDescricao())
                .tipo(produto.getTipoProduto().name())
                .valor(produto.getValorProduto().getValorProduto())
                .build();
    }
}
