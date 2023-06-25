package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.model.Cliente;

import java.util.UUID;

public interface IClienteService {
    UUID cadastrarCliente(String nome, String email, String cpf);

    Cliente buscarCliente(String cpf);
}
