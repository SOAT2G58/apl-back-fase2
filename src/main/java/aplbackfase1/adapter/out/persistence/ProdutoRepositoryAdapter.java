package aplbackfase1.adapter.out.persistence;

import aplbackfase1.adapter.out.persistence.entity.ProdutoEntity;
import aplbackfase1.adapter.out.persistence.repository.ProdutoRepository;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.ports.out.IProdutoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProdutoRepositoryAdapter implements IProdutoRepositoryPort {

    private final ProdutoRepository produtoRepository;

    @Override
    public Optional<ArrayList<Produto>> listarProdutosPorTipo(TipoProduto tipoProduto) {
        final Optional<ArrayList<Produto>> produtoList = Optional.of(new ArrayList<>());

        final Optional<List<ProdutoEntity>> produtoEntityList = this.produtoRepository
                .findAllByTipoProduto(tipoProduto.getCodigo());

        if(produtoEntityList.isPresent())
            produtoEntityList.get().forEach(res -> produtoList.get().add(res.to(res)));

        return produtoList;
    }

    @Override
    public Optional<Produto> buscarProdutoPorId(UUID idProduto) {
        Optional<Produto> produto = Optional.ofNullable(null);

        final Optional<ProdutoEntity> produtoEntity = this.produtoRepository
                .findById(idProduto);

        if(produtoEntity.isPresent())
            produto = Optional.ofNullable(produtoEntity.get().to(produtoEntity.get()));

        return produto;
    }

    @Override
    public Produto criarProduto(Produto produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity().from(produto, true);
        return this.produtoRepository.save(produtoEntity).to(produtoEntity);
    }

    @Override
    public void deletarProduto(UUID idProduto) {
        this.produtoRepository.deleteById(idProduto);
    }
}
