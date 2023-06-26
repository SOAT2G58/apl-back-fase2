package aplbackfase1.adapter.out.persistence;

import aplbackfase1.adapter.out.persistence.entity.ProdutoEntity;
import aplbackfase1.adapter.out.persistence.repository.ProdutoRepository;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.ports.out.IProdutoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoRepositoryAdapter implements IProdutoRepositoryPort {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listarProdutosPorTipo(TipoProduto tipoProduto) {
        final ArrayList<Produto> produtoList = new ArrayList<>();

        try {
            final List<ProdutoEntity> produtoEntityList = this.produtoRepository
                    .findAllByTipoProduto(tipoProduto.getCodigo())
                    .orElseThrow(() ->
                            new NoSuchElementException("Não foram encontrados produtos do tipo: " + tipoProduto)
                    );

            produtoEntityList.forEach((ProdutoEntity res) -> produtoList.add(res.to(res)));

            return produtoList;

        } catch (NoSuchElementException ex) {
            return produtoList;
        }
    }

    @Override
    public Produto buscarProdutoPorId(UUID idProduto) {
        try {
            final ProdutoEntity produtoEntity = this.produtoRepository
                    .findById(idProduto)
                    .orElseThrow(() ->
                            new NoSuchElementException("Não foi encontrado produto de id: " + idProduto)
                    );
            return produtoEntity.to(produtoEntity);
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    @Override
    public Produto criarProduto(Produto produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity().from(produto);
        return this.produtoRepository.save(produtoEntity).to(produtoEntity);
    }

    @Override
    public void deletarProduto(UUID idProduto) {
        this.produtoRepository.deleteById(idProduto);
    }
}
