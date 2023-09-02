package aplbackfase3.domain.usecases;


import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.adapters.gateways.interfaces.IProdutoGateway;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.interfaces.IProdutoUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProdutoUseCaseImpl implements IProdutoUseCase {

    private final IProdutoGateway gtw;

    @Override
    public List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto) {
        return gtw.listarProdutosPorTipo(tipoProduto);
    }

    @Override
    public Produto criarProduto(Produto produto) {
        return gtw.criarProduto(produto);
    }

    @Override
    public void deletarProduto(UUID idProduto) {
        gtw.deletarProduto(idProduto);
    }
}
