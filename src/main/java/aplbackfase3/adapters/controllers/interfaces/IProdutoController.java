package aplbackfase3.adapters.controllers.interfaces;


import aplbackfase2.adapters.ProdutoDTO;
import aplbackfase3.web.rest.produto.request.ProdutoRequest;

import java.util.List;
import java.util.UUID;

public interface IProdutoController {

    List<ProdutoDTO> buscarProduto(String tipoProduto);

    ProdutoDTO criarProduto(ProdutoRequest request);

    void deletarProduto(UUID id);

}
