package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.model.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProdutoRepositoryPort {
    List<Produto> listarProdutosPorTipo(TipoProduto tipoProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}
