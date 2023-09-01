package aplbackfase2.interfaces.usecases;

import aplbackfase2.utils.enums.TipoProduto;
import aplbackfase2.entities.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoUseCasePort {
    List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}