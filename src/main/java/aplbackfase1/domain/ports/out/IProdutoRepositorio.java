package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;

import java.util.List;
import java.util.UUID;

public interface IProdutoRepositorio {
    List<Produto> listarProdutoPorTipo(TipoProduto tipoProduto);
    Produto buscarProdutoPorId(UUID idProduto);

    Produto criarProduto(Produto produto);
}
