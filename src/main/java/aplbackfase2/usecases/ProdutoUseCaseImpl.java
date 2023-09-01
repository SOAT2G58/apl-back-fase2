package aplbackfase2.usecases;

import aplbackfase2.utils.enums.TipoProduto;
import aplbackfase2.entities.Produto;
import aplbackfase2.interfaces.gateways.IProdutoRepositoryPort;
import aplbackfase2.interfaces.usecases.IProdutoUseCasePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProdutoUseCaseImpl implements IProdutoUseCasePort {

    private final IProdutoRepositoryPort produtoRepositoryPort;

    @Override
    public List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto) {
        return produtoRepositoryPort.listarProdutosPorTipo(tipoProduto);
    }

    @Override
    public Produto criarProduto(Produto produto) {
        return produtoRepositoryPort.criarProduto(produto);
    }

    @Override
    public void deletarProduto(UUID idProduto) {
        produtoRepositoryPort.deletarProduto(idProduto);
    }
}
