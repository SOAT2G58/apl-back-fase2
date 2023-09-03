package aplbackfase3.domain.usecases;


import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.adapters.gateways.interfaces.IProdutoGateway;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.interfaces.IProdutoUseCase;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;
import aplbackfase3.domain.valueObjects.DescricaoProduto;
import aplbackfase3.domain.valueObjects.NomeProduto;
import aplbackfase3.domain.valueObjects.ValorProduto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProdutoUseCaseImpl implements IProdutoUseCase {

    private final IProdutoGateway gtw;

    @Override
    public List<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto) {
        List<Produto> produtos = new ArrayList<>();
        gtw.listarProdutosPorTipo(tipoProduto)
                .forEach(
                        produtoDAO -> produtos.add(this.from(produtoDAO))
                );
        return produtos;
    }

    @Override
    public void criarProduto(ProdutoDAO produtoDAO) {
        Produto produto = this.from(produtoDAO);
        gtw.criarProduto(produto);
    }

    public Produto from(ProdutoDAO produtoDAO) {
        return Produto.builder()
                .descricaoProduto(new DescricaoProduto(produtoDAO.getDescricao()))
                .nomeProduto(new NomeProduto(produtoDAO.getDescricao()))
                .valorProduto(new ValorProduto(produtoDAO.getValor()))
                .tipoProduto(TipoProduto.fromCodigo(produtoDAO.getTipo()))
                .build();
    }

    @Override
    public void deletarProduto(UUID idProduto) {
        gtw.deletarProduto(idProduto);
    }
}
