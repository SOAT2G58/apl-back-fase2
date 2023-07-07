package aplbackfase1.infrastructure.persistence;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.infrastructure.persistence.entity.PedidoEntity;
import aplbackfase1.infrastructure.persistence.entity.PedidoProdutoEntity;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.ports.out.IPedidoProdutoRepositoryPort;
import aplbackfase1.infrastructure.persistence.entity.ProdutoEntity;
import aplbackfase1.infrastructure.persistence.repository.PedidoProdutoRepository;
import aplbackfase1.infrastructure.persistence.repository.PedidoRepository;
import aplbackfase1.infrastructure.persistence.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.math.BigDecimal;

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
    public List<PedidoProduto> buscarPorPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = this.pedidoRepository.findById(pedido.getIdPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado, id: " + pedido.getIdPedido()));

        return this.pedidoProdutoRepository.findByPedido(pedido)
                .stream()
                .map(PedidoProdutoEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoProduto adicionarPedidoProduto(PedidoProduto pedidoProduto) {
        PedidoEntity existPedioEntity = pedidoRepository.findById(pedidoProduto.getPedidoId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado, id: " + pedidoProduto.getPedidoId()));
        ProdutoEntity existProdutoEntity = produtoRepository.findById(pedidoProduto.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado, id: " + pedidoProduto.getProdutoId()));

        PedidoProdutoEntity pedidoProdutoEntity = PedidoProdutoEntity.builder()
                .id(pedidoProduto.getId())
                .pedido(existPedioEntity)
                .produto(existProdutoEntity)
                .valorProduto(new BigDecimal(String.valueOf(existProdutoEntity.getValorProduto())))
                .observacaoProduto(pedidoProduto.getObservacaoProduto())
                .build();

        return PedidoProdutoEntity.to(this.pedidoProdutoRepository.save(pedidoProdutoEntity));
    }


    @Override
    @Transactional
    public PedidoProduto editarPedidoProduto(PedidoProduto pedidoProduto) {
        PedidoProdutoEntity existingPedidoProdutoEntity = this.pedidoProdutoRepository.findById(pedidoProduto.getId())
                .orElseThrow(() -> new IllegalArgumentException("PedidoProduto não encontrado, id: " + pedidoProduto.getId()));
        PedidoEntity existPedioEntity = pedidoRepository.findById(pedidoProduto.getPedidoId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado, id: " + pedidoProduto.getPedidoId()));
        ProdutoEntity existProdutoEntity = produtoRepository.findById(pedidoProduto.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado, id: " + pedidoProduto.getProdutoId()));

        existingPedidoProdutoEntity.setPedido(existPedioEntity);
        existingPedidoProdutoEntity.setProduto(existProdutoEntity);
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
