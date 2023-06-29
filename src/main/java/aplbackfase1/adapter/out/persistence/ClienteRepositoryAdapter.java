package aplbackfase1.adapter.out.persistence;

import aplbackfase1.adapter.out.persistence.entity.ClienteEntity;
import aplbackfase1.adapter.out.persistence.repository.ClienteRepository;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.ports.out.IClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Cliente identificarPorCpf(Cpf cpf) {
        ClienteEntity clienteEntity = new ClienteEntity().from(Cliente.builder().cpf(cpf).build());
        return clienteRepository.save(clienteEntity).to(clienteEntity);
    }

    @Override
    public Optional<ClienteEntity> buscarPorCpf(Cpf cpf) {
        return clienteRepository.findAllByCpf(cpf);
    }

    @Override
    public List<ClienteEntity> bucarTodos() { // TODO - temp remover posteriormente
        /*List<Cliente> clientes = clienteRepository.findAll().stream().map(e -> e.to(e)).collect(Collectors.toList());
        System.out.println(clientes);
        System.out.println(clientes.size());
        System.out.println(clientes.get(0));*/
        return clienteRepository.findAll();
    }

    @Override
    public Optional<ClienteEntity> buscarPorId(UUID uuid) {
        return clienteRepository.findById(uuid);
    }
}
