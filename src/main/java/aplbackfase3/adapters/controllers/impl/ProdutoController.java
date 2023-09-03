package aplbackfase3.adapters.controllers.impl;

import aplbackfase3.adapters.controllers.interfaces.IProdutoController;
import aplbackfase3.adapters.mappers.interfaces.IUseCaseAdapter;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.domain.interfaces.IProdutoUseCase;
import aplbackfase3.web.rest.produto.request.ProdutoRequest;
import aplbackfase3.web.rest.produto.response.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProdutoController implements IProdutoController {

    private final IProdutoUseCase produtoUseCase;

    private final IUseCaseAdapter useCaseAdapter;

    public List<ProdutoResponse> buscarProduto(String tipoProduto) {
        List<Produto> produtoArrayList = this.produtoUseCase
                .listarProdutosPorTipoProduto(TipoProduto.fromCodigo(tipoProduto));
        final var produtoDTOList = new ArrayList<ProdutoResponse>();
        produtoArrayList.forEach(produto -> produtoDTOList.add(new ProdutoResponse().from(produto)));
        return produtoDTOList;
    }

    public void criarProduto(ProdutoRequest request) {
        this.produtoUseCase.criarProduto(useCaseAdapter.from(request));
    }

    public void deletarProduto(UUID id) {
        this.produtoUseCase.deletarProduto(id);
    }
}
