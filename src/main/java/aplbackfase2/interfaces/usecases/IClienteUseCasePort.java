package aplbackfase2.interfaces.usecases;

import aplbackfase2.entities.Cliente;
import aplbackfase2.exceptions.entities.CpfExistenteException;
import aplbackfase2.entities.Cpf;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClienteUseCasePort {
    Cliente cadastrar(Cliente cliente) throws CpfExistenteException;
    Cliente identificarPorCpf(Cpf cpf);
    UUID gerarId();
    Optional<Cliente> buscarPorCpf(Cpf cpf);
    List<Cliente> bucarTodos();
    Optional<Cliente> buscarPorId(UUID uuid);
}
