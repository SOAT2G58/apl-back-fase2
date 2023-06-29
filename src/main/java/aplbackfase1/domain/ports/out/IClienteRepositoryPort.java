package aplbackfase1.domain.ports.out;

import aplbackfase1.adapter.out.persistence.entity.ClienteEntity;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.valueObject.Cpf;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClienteRepositoryPort {
    Cliente cadastrar(Cliente cliente);
    Cliente identificarPorCpf(Cpf cpf);
    Optional<ClienteEntity> buscarPorCpf(Cpf cpf);
    List<ClienteEntity> bucarTodos();
    Optional<ClienteEntity> buscarPorId(UUID uuid);
}
