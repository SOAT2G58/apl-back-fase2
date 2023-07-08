package aplbackfase1.infrastructure.persistence;

import aplbackfase1.domain.model.Fila;
import aplbackfase1.domain.model.PedidoFila;
import aplbackfase1.domain.ports.out.IFilaRepositoryPort;
import aplbackfase1.infrastructure.persistence.entity.PedidoFilaEntity;
import aplbackfase1.infrastructure.persistence.repository.FilaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
