package aplbackfase2.interfaces.gateways;

import aplbackfase2.entities.Cliente;
import aplbackfase2.entities.Cpf;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClienteRepositoryPort {
    Cliente cadastrar(Cliente cliente);
    Cliente identificarPorCpf(Cliente cliente);
    UUID gerarId();
    Optional<Cliente> buscarPorCpf(Cpf cpf);
    List<Cliente> bucarTodos();
    Optional<Cliente> buscarPorId(UUID uuid);
}
