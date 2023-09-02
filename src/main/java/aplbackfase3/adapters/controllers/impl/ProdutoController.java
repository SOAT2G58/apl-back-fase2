package aplbackfase3.adapters.controllers.impl;

import aplbackfase2.adapters.ProdutoDTO;
import aplbackfase3.enums.TipoProduto;
import aplbackfase3.adapters.controllers.interfaces.IProdutoController;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.interfaces.IProdutoUseCasePort;
import aplbackfase3.web.rest.produto.request.ProdutoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProdutoController implements IProdutoController {

    private final IProdutoUseCasePort produtoUseCasePort;

    public List<ProdutoDTO> buscarProduto(String tipoProduto) {
        List<Produto> produtoArrayList = this.produtoUseCasePort
                .listarProdutosPorTipoProduto(TipoProduto.fromCodigo(tipoProduto));
        final var produtoDTOList = new ArrayList<ProdutoDTO>();
        produtoArrayList.forEach(produto -> produtoDTOList.add(new ProdutoDTO().from(produto)));
        return produtoDTOList;
    }

    public ProdutoDTO criarProduto(ProdutoRequest request) {
        Produto produto = this.produtoUseCasePort.criarProduto(request.from(request));
        return new ProdutoDTO().from(produto);
    }

    public void deletarProduto(UUID id) {
        this.produtoUseCasePort.deletarProduto(id);
    }
}
