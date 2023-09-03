package aplbackfase3.adapters.gateways.impl;

import aplbackfase3.adapters.mappers.interfaces.IUseCaseAdapter;
import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.adapters.gateways.interfaces.IProdutoGateway;
import aplbackfase3.database.repositories.ProdutoRepository;
import aplbackfase3.database.entities.ProdutoEntity;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;
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

    private final IUseCaseAdapter useCaseAdapter;

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoDAO> listarProdutosPorTipo(TipoProduto tipoProduto) {
        final var produtoList = new ArrayList<ProdutoDAO>();
        final Optional<List<ProdutoEntity>> produtoEntityList = this.produtoRepository
                .findAllByTipoProduto(tipoProduto.getCodigo());

        if(produtoEntityList.isPresent())
            produtoEntityList.get().forEach(produtoEntity -> produtoList.add(useCaseAdapter.from(produtoEntity)));

        return produtoList;
    }

    @Override
    @Transactional()
    public void criarProduto(Produto produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity().from(produto, true);
        this.produtoRepository.save(produtoEntity);
    }

    @Override
    @Transactional()
    public void deletarProduto(UUID idProduto) {
        this.produtoRepository.deleteById(idProduto);
    }
}