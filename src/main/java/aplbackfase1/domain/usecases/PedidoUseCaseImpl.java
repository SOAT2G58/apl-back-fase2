package aplbackfase1.domain.usecases;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import aplbackfase1.domain.ports.out.IPedidoProdutoRepositoryPort;
import aplbackfase1.domain.ports.out.IPedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Comparator;
import java.util.Date;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class PedidoUseCaseImpl implements IPedidoUseCasePort {

    private final PagamentoUseCaseImpl pagamentoUseCaseImpl;
    private final FilaUseCaseImpl filaUseCaseImpl;
    private final IPedidoRepositoryPort pedidoRepositoryPort;
    private final IPedidoProdutoRepositoryPort pedidoProdutoRepositoryPort;

    @Override
    public Pedido cadastrar(Pedido pedido) {
        UUID idCliente = pedido.getIdCliente();
        List<Pedido> pedidosAtivos = buscarPedidosPorClienteEStatus(idCliente, StatusPedido.A);
        if (pedidosAtivos.isEmpty()) {
            return pedidoRepositoryPort.cadastrar(pedido);
        } else {
            pedidosAtivos.sort(Comparator.comparing(Pedido::getDataInclusao).reversed());
            return pedidosAtivos.get(0);
        }
    }
    @Override
    public Pedido atualizar(Pedido pedido) {
        Pedido existingPedido = checkPedidoStatus(pedido.getIdPedido());
        existingPedido.setProdutos(pedido.getProdutos());
        existingPedido.setValorPedido(pedido.getValorPedido());
        existingPedido.setStatusPedido(pedido.getStatusPedido());
        existingPedido.setDataAtualizacao(new Date());
        return pedidoRepositoryPort.atualizar(existingPedido);
    }

    @Override
    public void remover(UUID id) {
        pedidoRepositoryPort.remover(id);
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
    public Optional<PedidoProduto> buscarPedidoProdutoPorId(UUID id) {
        return pedidoProdutoRepositoryPort.buscarPorId(id);
    }

    @Override
    public Pedido checkout(UUID id) {
        Pedido pedido = checkPedidoStatus(id);
        boolean pagamentOk = pagamentoUseCaseImpl.realizarPagamento(id);
        if (pagamentOk) {
            pedido.setStatusPedido(StatusPedido.R);
            BigDecimal valorTotal = pedido.getProdutos().stream()
                    .map(PedidoProduto::getValorProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            pedido.setValorPedido(valorTotal);
            pedido.setDataAtualizacao(new Date());

            filaUseCaseImpl.inserirPedidoNaFila(pedido);
            pedidoRepositoryPort.atualizar(pedido);
            return pedido;
        } else {
            throw new IllegalStateException("Pagamento INvalido");
        }

    }

    private Pedido checkPedidoStatus(UUID idPedido) {
        Optional<Pedido> optionalPedido = pedidoRepositoryPort.buscarPorId(idPedido);
        if (optionalPedido.isPresent()) {
            Pedido existingPedido = optionalPedido.get();
            if (existingPedido.getStatusPedido() == StatusPedido.A) {
                return existingPedido;
            } else {
                throw new IllegalStateException("Pedido não está aberto para edição.");
            }
        } else {
            throw new IllegalStateException("Pedido não encontrado.");
        }
    }

}
