package aplbackfase3.adapters.gateways.impl;

import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.adapters.gateways.interfaces.IProdutoGateway;
import aplbackfase3.db.repository.ProdutoRepository;
import aplbackfase3.db.repository.entities.ProdutoEntity;
import aplbackfase3.domain.entities.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoPostgresRepository implements IProdutoGateway {

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