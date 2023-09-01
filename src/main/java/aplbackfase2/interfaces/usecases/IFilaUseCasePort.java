package aplbackfase2.interfaces.usecases;

import aplbackfase2.exceptions.entities.PedidoJaNaFilaException;
import aplbackfase2.exceptions.entities.PedidoNotSavedException;
import aplbackfase2.exceptions.entities.PedidoNaoEncontradoNaFilaException;
import aplbackfase2.entities.Pedido;
import aplbackfase2.entities.PedidoFila;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFilaUseCasePort {

    public PedidoFila inserirPedidoNaFila(Pedido pedido) throws PedidoNotSavedException, PedidoJaNaFilaException;

    public Optional<PedidoFila> obterPedidoPorNumeroNaFila(Long numero);

    public Optional<PedidoFila> obterPedidoPorIdPedido(UUID id);

    public List<PedidoFila> obterPedidosNaFila(int page, int size);

    public void removerPedidoDaFila(Long numero) throws PedidoNaoEncontradoNaFilaException;
}
