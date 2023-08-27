package aplbackfase1.domain.usecases;

import aplbackfase1.domain.enums.StatusPagamento;
import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.exceptions.PedidoNaoEncontradoException;
import aplbackfase1.domain.exceptions.PedidoOperacaoNaoSuportadaException;
import aplbackfase1.domain.exceptions.PedidoPagamentoInvalidoException;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.ports.in.IFilaUseCasePort;
import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import aplbackfase1.domain.ports.out.IPedidoProdutoRepositoryPort;
import aplbackfase1.domain.ports.out.IPedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
public class PedidoUseCaseImpl implements IPedidoUseCasePort {

    private final IPagamentoUseCase pagamentoUseCase;
    private final IFilaUseCasePort filaUseCasePort;
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
    public Pedido atualizarStatus(StatusPedido status, UUID idPedido) throws PedidoNaoEncontradoException {
        Optional<Pedido> pedido = buscarPorId(idPedido);
        if (pedido.isEmpty()) {
            throw new PedidoNaoEncontradoException();
        }
        pedido.get().setStatusPedido(status);
        pedido.get().setDataAtualizacao(new Date());
        return pedidoRepositoryPort.atualizar(pedido.get());
    }

    @Override
    public Pedido atualizarStatusPagamento(StatusPagamento status, UUID idPedido) throws PedidoNaoEncontradoException {
        Optional<Pedido> pedido = buscarPorId(idPedido);
        if (pedido.isEmpty()) {
            throw new PedidoNaoEncontradoException();
        }
        pedido.get().setStatusPagamento(status);
        pedido.get().setDataAtualizacao(new Date());
        return pedidoRepositoryPort.atualizar(pedido.get());
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
        boolean pgtoOk = pagamentoUseCase.realizarPagamento(id);
        if (pgtoOk) {
            pedido.setStatusPedido(StatusPedido.R);
            BigDecimal valorTotal = pedido.getProdutos().stream()
                    .map(PedidoProduto::getValorProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            pedido.setValorPedido(valorTotal);
            pedido.setDataAtualizacao(new Date());

            pedidoRepositoryPort.atualizar(pedido);
            filaUseCasePort.inserirPedidoNaFila(pedido);
            return pedido;
        } else {
            throw new PedidoPagamentoInvalidoException("Pagamento Invalido");
        }
    }

    private Pedido checkPedidoStatus(UUID idPedido) {
        Optional<Pedido> optionalPedido = pedidoRepositoryPort.buscarPorId(idPedido);
        if (optionalPedido.isPresent()) {
            Pedido existingPedido = optionalPedido.get();
            if (existingPedido.getStatusPedido() == StatusPedido.A) {
                return existingPedido;
            } else {
                throw new PedidoOperacaoNaoSuportadaException("Pedido não está aberto para edição.");
            }
        } else {
            throw new PedidoNaoEncontradoException("Pedido não encontrado.");
        }
    }

}
