package aplbackfase2.gateways;

import aplbackfase2.entities.PedidoFila;
import aplbackfase2.gateways.entities.PedidoFilaEntity;
import aplbackfase2.interfaces.gateways.IFilaRepositoryPort;
import aplbackfase2.interfaces.repositories.FilaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilaRepositoryAdapter implements IFilaRepositoryPort {

    @Autowired
    private final FilaRepository filaRepository;

    @Override
    public Long inserir(PedidoFila pedidoFila) {
        var pedidoFilaEntity = new PedidoFilaEntity(pedidoFila);
        return filaRepository.save(pedidoFilaEntity).getNumeroNaFila();
    }

    @Override
    public Optional<PedidoFila> obterPorNumeroNaFila(Long numero) {
        var pedidoFilaEntity = filaRepository.findByNumeroNaFila(numero);
        return pedidoFilaEntity.map(PedidoFilaEntity::toPedidoFila);
    }

    @Override
    public Optional<PedidoFila> obterPorIdPedido(UUID idPedido) {
        var pedidoFilaEntity = filaRepository.findByIdPedido(idPedido);
        return pedidoFilaEntity.map(PedidoFilaEntity::toPedidoFila);
    }

    @Override
    public List<PedidoFila> obterPedidos(int page, int size) {
        return filaRepository.findAll(PageRequest.of(page, size)).toList().stream().map(obj -> obj.toPedidoFila()).collect(Collectors.toList());
    }
}
