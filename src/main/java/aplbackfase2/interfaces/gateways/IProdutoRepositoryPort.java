package aplbackfase2.interfaces.gateways;

import aplbackfase2.utils.enums.TipoProduto;
import aplbackfase2.entities.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoRepositoryPort {
    List<Produto> listarProdutosPorTipo(TipoProduto tipoProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}
