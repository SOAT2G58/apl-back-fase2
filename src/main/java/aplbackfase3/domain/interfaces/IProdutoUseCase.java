package aplbackfase3.domain.interfaces;



import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.domain.entities.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoUseCase {
    List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}