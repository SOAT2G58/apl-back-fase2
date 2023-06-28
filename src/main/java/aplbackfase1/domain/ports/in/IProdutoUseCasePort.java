package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface IProdutoUseCasePort {
    Optional<ArrayList<Produto>> listarProdutosPorTipoProduto(TipoProduto tipoProduto);
    Optional<Produto> buscarProdutoPorID(UUID idProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}