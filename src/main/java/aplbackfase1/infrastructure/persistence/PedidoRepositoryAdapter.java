package aplbackfase1.infrastructure.persistence;

import aplbackfase1.infrastructure.persistence.entity.PedidoEntity;
import aplbackfase1.infrastructure.persistence.repository.PedidoRepository;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.ports.out.IPedidoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements IPedidoRepositoryPort {

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Pedido cadastrar(Pedido pedido) {
        PedidoEntity pedidoEntity = new PedidoEntity().from(pedido);
        return this.pedidoRepository.save(pedidoEntity).to(pedidoEntity);
    }

    @Override
    @Transactional
    public Pedido atualizar(Pedido pedido) {
        PedidoEntity pedidoEntity = new PedidoEntity().from(pedido);
        return this.pedidoRepository.save(pedidoEntity).to(pedidoEntity);
    }

    @Override
    @Transactional
    public PedidoProduto adicionarPedidoProduto(PedidoProduto pedidoProduto) {
        // Implementation depends on your PedidoProduto entity and repository
    }

    @Override
    @Transactional
    public PedidoProduto editarPedidoProduto(PedidoProduto pedidoProduto) {
        // Implementation depends on your PedidoProduto entity and repository
    }

    @Override
    @Transactional
    public PedidoProduto excluirPedidoProduto(PedidoProduto pedidoProduto) {
        //
