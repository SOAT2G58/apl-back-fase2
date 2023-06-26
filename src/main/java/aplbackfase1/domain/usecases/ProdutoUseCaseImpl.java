package aplbackfase1.domain.usecases;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import aplbackfase1.domain.ports.out.IProdutoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoUseCaseImpl implements IProdutoUseCasePort {

    private final IProdutoRepositoryPort produtoRepositoryPort;

    @Override
    public List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto) {
        //TODO TRATAR CENÁRIO EM QUE NÃO HÁ PRODUTO
        return produtoRepositoryPort.listarProdutosPorTipo(tipoProduto);
    }

    @Override
    public Produto buscarProdutoPorID(UUID idProduto) {
        //TODO TRATAR CENÁRIO EM QUE NÃO HÁ PRODUTO
        return produtoRepositoryPort.buscarProdutoPorId(idProduto);
    }

    @Override
    public Produto criarProduto(Produto produto) {
        //TODO TRATAR CENÁRIO EM QUE PRODUTO É NULO
        if(null != produto)
            return produtoRepositoryPort.criarProduto(produto);
        return null;
    }

    @Override
    public void deletarProduto(UUID idProduto) {
        produtoRepositoryPort.deletarProduto(idProduto);
    }
}
