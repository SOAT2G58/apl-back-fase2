package aplbackfase2.usecases;

import aplbackfase2.exceptions.entities.PedidoJaNaFilaException;
import aplbackfase2.exceptions.entities.PedidoNaoEncontradoNaFilaException;
import aplbackfase2.exceptions.entities.PedidoNotSavedException;
import aplbackfase2.entities.Pedido;
import aplbackfase2.entities.PedidoFila;
import aplbackfase2.interfaces.gateways.IFilaRepositoryPort;
import aplbackfase2.interfaces.usecases.IFilaUseCasePort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FilaUseCaseImpl implements IFilaUseCasePort {

    private IFilaRepositoryPort filaRepositoryPort;

    public FilaUseCaseImpl(IFilaRepositoryPort filaRepositoryPort) {
        this.filaRepositoryPort = filaRepositoryPort;
    }

    // TODO - após mergear a feat de pedido, chamar a IPedidoUseCasePort para buscar o pedido pelo seu ID na table de pedidos e passar junto com as respostas
    // A fila só salva o id do pedido, para não haver duplicação nas tabelas de pedidos e manter uma única fonte de dados.
    // o numero na fila é gerado automaticamente


    @Override
    public PedidoFila inserirPedidoNaFila(Pedido pedido) throws PedidoNotSavedException, PedidoJaNaFilaException {
        var idPedido = pedido.getIdPedido();

        if (idPedido == null || idPedido.toString().isBlank()) {
            throw new PedidoNotSavedException();
        }

        if (obterPedidoPorIdPedido(idPedido).isPresent()) {
            throw new PedidoJaNaFilaException(idPedido);
        }

        // pedido.atualizaStatusPedido(StatusPedido.R);
        var pedidoFila = new PedidoFila(pedido);
        var numeroNaFila = filaRepositoryPort.inserir(pedidoFila);
        pedidoFila.setNumeroNaFila(numeroNaFila);
        return pedidoFila;
    }

    @Override
    public Optional<PedidoFila> obterPedidoPorNumeroNaFila(Long numero) {
        return filaRepositoryPort.obterPorNumeroNaFila(numero);
    }

    @Override
    public Optional<PedidoFila> obterPedidoPorIdPedido(UUID idPedido) {
        return filaRepositoryPort.obterPorIdPedido(idPedido);
    }

    @Override
    public List<PedidoFila> obterPedidosNaFila(int page, int size) {
        return filaRepositoryPort.obterPedidos(page, size);
    }

    @Override
    public void removerPedidoDaFila(Long numero) throws PedidoNaoEncontradoNaFilaException {
        var pedidoFilaOptional = filaRepositoryPort.obterPorNumeroNaFila(numero);

        if (pedidoFilaOptional == null) {
            throw new PedidoNaoEncontradoNaFilaException();
        }

        // TODO - chamar usecas de pedido para obter o pedido por id quando estiver pronto. E mudar o status para Finalizado.

    }

}
