package aplbackfase1.domain.ports.out;

import aplbackfase1.adapter.out.persistence.entity.ClienteEntity;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.valueObject.Cpf;

import java.util.List;
import java.util.Optional;

public interface IClienteRepositoryPort {
    Cliente cadastrar(Cliente cliente);
    Cliente identificarPorCpf(Cpf cpf);
    Optional<Cliente> buscarPorCpf(Cpf cpf);
    List<ClienteEntity> bucarTodos(); // TODO - temp remover posteriormente
}
