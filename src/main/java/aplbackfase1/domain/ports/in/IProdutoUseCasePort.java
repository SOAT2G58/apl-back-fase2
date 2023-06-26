package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;

import java.util.List;
import java.util.UUID;

public interface IProdutoUseCasePort {
    List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto);
    Produto buscarProdutoPorID(UUID idProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}