package aplbackfase1.adapter.out.persistence;

import aplbackfase1.adapter.out.persistence.entity.ProdutoEntity;
import aplbackfase1.adapter.out.persistence.repository.ProdutoRepository;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.ports.out.IProdutoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProdutoRepositoryAdapter implements IProdutoRepositoryPort {

    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Produto> listarProdutosPorTipo(TipoProduto tipoProduto) {
        final var produtoList = new ArrayList<Produto>();
        final Optional<List<ProdutoEntity>> produtoEntityList = this.produtoRepository
                .findAllByTipoProduto(tipoProduto.getCodigo());

        if(produtoEntityList.isPresent())
            produtoEntityList.get().forEach(produtoEntity -> produtoList.add(produtoEntity.to(produtoEntity)));

        return produtoList;
    }

    @Override
    @Transactional()
    public Produto criarProduto(Produto produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity().from(produto, true);
        return this.produtoRepository.save(produtoEntity).to(produtoEntity);
    }

    @Override
    @Transactional()
    public void deletarProduto(UUID idProduto) {
        this.produtoRepository.deleteById(idProduto);
    }
}