package aplbackfase3.adapters.gateways.interfaces;

import aplbackfase3.enums.TipoProduto;
import aplbackfase3.domain.entities.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoGateway {
    List<Produto> listarProdutosPorTipo(TipoProduto tipoProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}
