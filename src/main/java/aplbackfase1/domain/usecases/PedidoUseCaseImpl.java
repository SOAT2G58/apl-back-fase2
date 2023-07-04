package aplbackfase1.domain.usecases;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import aplbackfase1.domain.ports.out.IPedidoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Comparator;
import java.util.Date;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PedidoUseCaseImpl implements IPedidoUseCasePort {

    private final IPedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public Pedido cadastrar(Pedido pedido) {
        UUID idCliente = pedido.getCliente().getId();
        List<Pedido> pedidosAtivos = buscarPedidosPorClienteEStatus(idCliente, StatusPedido.A);
        if (pedidosAtivos.isEmpty()) {
            return pedidoRepositoryPort.cadastrar(pedido);
        } else {
            pedidosAtivos.sort(Comparator.comparing(Pedido::getDataInclusao).reversed());
            return pedidosAtivos.get(0);
        }
    }

    @Override
    public Pedido adicionarProduto(PedidoProduto produto) {
        Optional<Pedido> optionalPedido = pedidoRepositoryPort.buscarPorId(produto.getIdPedido());
        if (optionalPedido.isPresent() && optionalPedido.get().getStatusPedido() == StatusPedido.A) {
            /*
            // Verifica se produto está disponível
            Optional<Produto> optionalProduto = produtoRepositoryPort.buscarPorId(produto.getIdProduto());

            if (optionalProduto.isPresent() && optionalProduto.get().isAvailable()) {
                return pedidoRepositoryPort.adicionarProduto(produto);
            } else {
               throw new IllegalStateException("Produto não disponível");
            }
            */
            return pedidoRepositoryPort.adicionarProduto(produto);
        } else {
            throw new IllegalStateException("Pedido não encontrado ou com status impeditivo.");
        }
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        return pedidoRepositoryPort.buscarPorId(id);
    }

    @Override
    public List<Pedido> buscarTodos(int pageNumber, int pageSize) {
        return pedidoRepositoryPort.buscarTodos(pageNumber, pageSize);
    }

    @Override
    public List<Pedido> buscarPedidosPorCliente(UUID idCliente) {
        return pedidoRepositoryPort.buscarPedidosPorCliente(idCliente);
    }

    @Override
    public List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido) {
        return pedidoRepositoryPort.buscarPedidosPorStatus(statusPedido);
    }

    @Override
    public List<Pedido> buscarPedidosPorClienteEStatus(UUID idCliente, StatusPedido statusPedido) {
        return pedidoRepositoryPort.buscarPedidosPorClienteEStatus(idCliente, statusPedido);
    }

    @Override
    public Pedido atualizar(Pedido pedido) {
        Optional<Pedido> optionalPedido = pedidoRepositoryPort.buscarPorId(pedido.getIdPedido());
        if (optionalPedido.isPresent() && optionalPedido.get().getStatusPedido() == StatusPedido.A) {
            return pedidoRepositoryPort.atualizar(pedido);
        } else {
            throw new IllegalStateException("Pedido não encontrado ou com status impeditivo.");
        }
    }

    @Override
    public void remover(UUID id) {
        pedidoRepositoryPort.remover(id);
    }

    @Override
    public Pedido checkout(UUID id) {
        Optional<Pedido> optionalPedido = pedidoRepositoryPort.buscarPorId(id);
        if (optionalPedido.isEmpty()) {
            throw new IllegalStateException("Pedido não encontrado.");
        }
        Pedido pedido = optionalPedido.get();
        pedido.setStatusPedido(StatusPedido.C);
        BigDecimal valorTotal = pedido.getProdutos().stream()
                .map(PedidoProduto::getValorProduto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setValorPedido(valorTotal);
        pedido.setDataAtualizacao(new Date());
        pedidoRepositoryPort.atualizar(pedido);
        // Adiciona a Fila - a implementar
        // filaRepositoryPort.adicionar(id);
        return pedido;
    }

}
