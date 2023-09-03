package aplbackfase3.domain.interfaces;



import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;

import java.util.List;
import java.util.UUID;

public interface IProdutoUseCase {
    List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto);
    void criarProduto(ProdutoDAO produto);
    void deletarProduto(UUID idProduto);
}