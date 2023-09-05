package aplbackfase2.gateways;

import aplbackfase2.interfaces.gateways.IClienteRepositoryPort;
import aplbackfase2.interfaces.repositories.ClienteRepository;
import aplbackfase2.entities.Cliente;
import aplbackfase2.entities.Cpf;
import aplbackfase2.gateways.entities.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  ClienteRepositoryAdapter implements IClienteRepositoryPort {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente cadastrar(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity().from(cliente);
        return clienteRepository.save(clienteEntity).to(clienteEntity);
    }

    @Override
    public Cliente identificarPorCpf(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity().from(cliente);
        return clienteRepository.save(clienteEntity).to(clienteEntity);
    }

    @Override
    public UUID gerarId() {
        return clienteRepository.save(new ClienteEntity()).getId();
    }

    @Override
    public Optional<Cliente> buscarPorCpf(Cpf cpf) {
        return clienteRepository.findAllByCpf(cpf).map(obj -> obj.to(obj));
    }

    @Override
    public List<Cliente> bucarTodos() { // TODO - temp remover posteriormente
        List<ClienteEntity> test = clienteRepository.findAll();
        List<Cliente> test2 = test.stream().map(e -> e.to(e)).collect(Collectors.toList());
        return test2;
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID uuid) {
        return clienteRepository.findById(uuid).map(obj -> obj.to(obj));
    }
}
