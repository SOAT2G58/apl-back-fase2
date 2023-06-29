package aplbackfase1.adapter.out.persistence;

import aplbackfase1.adapter.out.persistence.entity.ProdutoEntity;
import aplbackfase1.adapter.out.persistence.repository.ProdutoRepository;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.ports.out.IProdutoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProdutoRepositoryAdapter implements IProdutoRepositoryPort {

    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Produto> listarProdutosPorTipo(TipoProduto tipoProduto, Pageable pageable) {
        final List<Produto> produtoList = new ArrayList<>();

        final Page<ProdutoEntity> produtoEntityList = this.produtoRepository
                .findAllByTipoProduto(tipoProduto.getCodigo(), pageable);

        produtoEntityList.get().forEach(res -> produtoList.add(res.to(res)));

        return new PageImpl<>(produtoList, produtoEntityList.getPageable(), produtoEntityList.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Produto> buscarProdutoPorId(UUID idProduto) {
        Optional<Produto> produto = Optional.ofNullable(null);

        final Optional<ProdutoEntity> produtoEntity = this.produtoRepository
                .findById(idProduto);

        if(produtoEntity.isPresent())
            produto = Optional.ofNullable(produtoEntity.get().to(produtoEntity.get()));

        return produto;
    }

    @Override
    @Transactional
    public Produto criarProduto(Produto produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity().from(produto, true);
        return this.produtoRepository.save(produtoEntity).to(produtoEntity);
    }

    @Override
    @Transactional
    public void deletarProduto(UUID idProduto) {
        this.produtoRepository.deleteById(idProduto);
    }
}
