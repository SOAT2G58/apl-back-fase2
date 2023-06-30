package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.model.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoUseCasePort {
    List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}