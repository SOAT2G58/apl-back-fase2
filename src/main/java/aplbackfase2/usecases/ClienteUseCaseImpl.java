package aplbackfase2.usecases;

import aplbackfase2.entities.Cliente;
import aplbackfase2.entities.Cpf;
import aplbackfase2.exceptions.entities.CpfExistenteException;
import aplbackfase2.interfaces.gateways.IClienteRepositoryPort;
import aplbackfase2.interfaces.usecases.IClienteUseCasePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ClienteUseCaseImpl implements IClienteUseCasePort {

    private final IClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente cadastrar(Cliente cliente) throws CpfExistenteException {
        Optional<Cliente> clienteDb = buscarPorCpf(cliente.getCpf());
        if (clienteDb.isPresent())
            throw new CpfExistenteException();
        return clienteRepositoryPort.cadastrar(cliente);
    }

    @Override
    public Cliente identificarPorCpf(Cpf cpf) {
        Optional<Cliente> clienteDb = buscarPorCpf(cpf);
        if (clienteDb.isPresent())
            return clienteDb.get();
        else
            return clienteRepositoryPort.identificarPorCpf(Cliente.builder().cpf(cpf).build());
    }

    @Override
    public UUID gerarId() {
        return clienteRepositoryPort.gerarId();
    }

    @Override
    public Optional<Cliente> buscarPorCpf(Cpf cpf) {
        return clienteRepositoryPort.buscarPorCpf(cpf);
    }

    @Override
    public List<Cliente> bucarTodos() { // TODO - temp remover posteriormente
        return clienteRepositoryPort.bucarTodos();
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID uuid) {
        return clienteRepositoryPort.buscarPorId(uuid);
    }
}
