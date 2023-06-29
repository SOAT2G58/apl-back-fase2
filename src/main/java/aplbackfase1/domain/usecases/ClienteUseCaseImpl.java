package aplbackfase1.domain.usecases;

import aplbackfase1.adapter.out.persistence.entity.ClienteEntity;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.exceptions.CpfExistenteException;
import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.ports.in.IClienteUseCasePort;
import aplbackfase1.domain.ports.out.IClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteUseCaseImpl implements IClienteUseCasePort {

    private final IClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente cadastrar(Cliente cliente) throws CpfExistenteException {
        Optional<ClienteEntity> clienteDb = buscarPorCpf(cliente.getCpf());
        if (clienteDb.isPresent())
            throw new CpfExistenteException();
        return clienteRepositoryPort.cadastrar(cliente);
    }

    @Override
    public Cliente identificarPorCpf(Cpf cpf) {
        Optional<ClienteEntity> clienteDb = buscarPorCpf(cpf);
        if (clienteDb.isPresent())
            return new ClienteEntity().to(clienteDb.get());
        else
            return clienteRepositoryPort.identificarPorCpf(cpf);
    }

    @Override
    public Optional<ClienteEntity> buscarPorCpf(Cpf cpf) {
        return clienteRepositoryPort.buscarPorCpf(cpf);
    }

    @Override
    public List<ClienteEntity> bucarTodos() { // TODO - temp remover posteriormente
        return clienteRepositoryPort.bucarTodos();
    }

    @Override
    public Optional<ClienteEntity> buscarPorId(UUID uuid) {
        return clienteRepositoryPort.buscarPorId(uuid);
    }
}
