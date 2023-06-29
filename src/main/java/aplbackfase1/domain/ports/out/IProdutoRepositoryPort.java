package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.enums.TipoProduto;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface IProdutoRepositoryPort {
    Optional<ArrayList<Produto>> listarProdutosPorTipo(TipoProduto tipoProduto);
    Optional<Produto> buscarProdutoPorId(UUID idProduto);

    Produto criarProduto(Produto produto);

    void deletarProduto(UUID idProduto);
}
