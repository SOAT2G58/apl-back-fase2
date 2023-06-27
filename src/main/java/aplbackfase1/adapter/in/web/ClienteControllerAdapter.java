package aplbackfase1.adapter.in.web;

import aplbackfase1.adapter.out.persistence.entity.ClienteEntity;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.ports.in.IClienteUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ClienteControllerAdapter {

    private final IClienteUseCasePort clienteUseCasePort;

    @GetMapping(value = "/clientes", produces = "application/json")
    public ResponseEntity<List<ClienteEntity>> buscarTodos() {
        return ResponseEntity.ok(clienteUseCasePort.bucarTodos());
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente>  cadastrar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteUseCasePort.cadastrar(cliente));
    }

    @PostMapping("/clientes/{cpf}")
    public ResponseEntity<Cliente> identificarPorCpf(@RequestParam Cpf cpf) {
        return ResponseEntity.ok(clienteUseCasePort.identificarPorCpf(cpf));
    }

    @GetMapping("/clientes/{cpf}")
    public ResponseEntity<Optional<Cliente>> buscarPorCpf(@PathVariable(name = "cpf") Cpf cpf) {
        return ResponseEntity.ok(clienteUseCasePort.buscarPorCpf(cpf));
    }


}
