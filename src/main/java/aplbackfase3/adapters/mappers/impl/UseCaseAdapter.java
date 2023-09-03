package aplbackfase3.adapters.mappers.impl;

import aplbackfase3.adapters.mappers.interfaces.IUseCaseAdapter;
import aplbackfase3.database.entities.ProdutoEntity;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;
import aplbackfase3.web.rest.produto.request.ProdutoRequest;
import org.springframework.stereotype.Component;

@Component
public class UseCaseAdapter implements IUseCaseAdapter {

    @Override
    public ProdutoDAO from(ProdutoRequest request) {
        return ProdutoDAO
                .builder()
                .valor(request.getValor())
                .nome(request.getNome())
                .tipo(request.getTipo())
                .descricao(request.getDescricao())
                .build();
    }

    @Override
    public ProdutoDAO from(ProdutoEntity entity) {
        return ProdutoDAO.builder()
                .valor(entity.getValorProduto())
                .nome(entity.getNomeProduto())
                .descricao(entity.getDescricaoProduto())
                .tipo(entity.getTipoProduto())
                .id(entity.getIdProduto())
                .build();
    }
}
