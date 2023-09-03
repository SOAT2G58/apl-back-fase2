package aplbackfase3.adapters.controllers.interfaces;


import aplbackfase3.web.rest.produto.request.ProdutoRequest;
import aplbackfase3.web.rest.produto.response.ProdutoResponse;

import java.util.List;
import java.util.UUID;

public interface IProdutoController {

    List<ProdutoResponse> buscarProduto(String tipoProduto);

    void criarProduto(ProdutoRequest request);

    void deletarProduto(UUID id);

}
