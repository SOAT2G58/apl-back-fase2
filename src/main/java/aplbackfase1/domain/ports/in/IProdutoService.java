package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;

import java.util.List;

public interface IProdutoService {
    List<Produto> buscarProdutos(TipoProduto tipoProduto);
}
