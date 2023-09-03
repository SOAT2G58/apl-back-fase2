package aplbackfase3.adapters.gateways.interfaces;

import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;

import java.util.List;
import java.util.UUID;

public interface IProdutoGateway {
    List<ProdutoDAO> listarProdutosPorTipo(TipoProduto tipoProduto);
    void criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}
