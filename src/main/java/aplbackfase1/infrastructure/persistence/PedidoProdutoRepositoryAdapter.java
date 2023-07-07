package aplbackfase1.infrastructure.persistence;

import aplbackfase1.infrastructure.persistence.entity.PedidoEntity;
import aplbackfase1.infrastructure.persistence.entity.PedidoProdutoEntity;
import aplbackfase1.infrastructure.persistence.entity.ProdutoEntity;
import aplbackfase1.infrastructure.persistence.repository.PedidoProdutoRepository;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.ports.out.IPedidoProdutoRepositoryPort;
import aplbackfase1.infrastructure.persistence.repository.PedidoRepository;
import aplbackfase1.infrastructure.persistence.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PedidoProdutoRepositoryAdapter implements IPedidoProdutoRepositoryPort {

    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoProduto> buscarTodos() {
        return this.pedidoProdutoRepository.findAll().stream()
                .map(PedidoProdutoEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PedidoProduto> buscarPorId(UUID id) {
        return this.pedidoProdutoRepository.findById(id)
                .map(PedidoProdutoEntity::to);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoProduto> buscarIdPedido(UUID idPedido) {
        return this.pedidoProdutoRepository.findbyidPedido(idPedido)
                .stream()
                .map(PedidoProdutoEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoProduto adicionarPedidoProduto(PedidoProduto pedidoProduto) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(pedidoProduto.getIdPedido())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pedido não encontrado, id: " + pedidoProduto.getIdPedido()));
        ProdutoEntity produtoEntity = produtoRepository.findById(pedidoProduto.getIdProduto())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Produto não encontrado, id: " + pedidoProduto.getIdProduto()));
        PedidoProdutoEntity pedidoProdutoEntity = PedidoProdutoEntity.builder()
                .pedido(pedidoEntity)
                .produto(produtoEntity)
                .valorProduto(pedidoProduto.getValorProduto())
                .observacaoProduto(pedidoProduto.getObservacaoProduto())
                .build();
        return PedidoProdutoEntity.to(this.pedidoProdutoRepository.save(pedidoProdutoEntity));

    }

    @Override
    @Transactional
    public PedidoProduto editarPedidoProduto(PedidoProduto pedidoProduto) {
        PedidoProdutoEntity existingPedidoProdutoEntity = this.pedidoProdutoRepository.findById(pedidoProduto.getId())
                .orElseThrow(() -> new IllegalArgumentException("PedidoProduto não encontrado, id: " + pedidoProduto.getId()));
        PedidoEntity pedidoEntity = pedidoRepository.findById(pedidoProduto.getIdPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado, id: " + pedidoProduto.getIdPedido()));
        ProdutoEntity produtoEntity = produtoRepository.findById(pedidoProduto.getIdProduto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado, id: " + pedidoProduto.getIdProduto()));

        existingPedidoProdutoEntity.setPedido(pedidoEntity);
        existingPedidoProdutoEntity.setProduto(produtoEntity);
                existingPedidoProdutoEntity.setValorProduto(pedidoProduto.getValorProduto());
        existingPedidoProdutoEntity.setObservacaoProduto(pedidoProduto.getObservacaoProduto());

        PedidoProdutoEntity updatedPedidoProdutoEntity = this.pedidoProdutoRepository.save(existingPedidoProdutoEntity);

        return PedidoProdutoEntity.to(updatedPedidoProdutoEntity);
    }

    @Override
    @Transactional
    public void deletar(UUID idPedidoProduto) {
        this.pedidoProdutoRepository.deleteById(idPedidoProduto);
    }
}
