package aplbackfase1.infrastructure.persistence;

import aplbackfase1.domain.model.Fila;
import aplbackfase1.domain.model.PedidoFila;
import aplbackfase1.domain.ports.out.IFilaRepositoryPort;
import aplbackfase1.infrastructure.persistence.entity.PedidoFilaEntity;
import aplbackfase1.infrastructure.persistence.repository.FilaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
