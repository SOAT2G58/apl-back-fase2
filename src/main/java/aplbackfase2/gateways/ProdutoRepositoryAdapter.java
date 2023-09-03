package aplbackfase2.gateways;

import aplbackfase2.interfaces.gateways.IProdutoRepositoryPort;
import aplbackfase2.interfaces.repositories.ProdutoRepository;
import aplbackfase2.entities.Produto;
import aplbackfase2.gateways.entities.ProdutoEntity;
import aplbackfase2.utils.enums.TipoProduto;
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