package aplbackfase1.adapter.in.web;

import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.ports.in.IClienteUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ClienteControllerAdapter {

    private final IClienteUseCasePort clienteUseCasePort;

    @GetMapping(value = "/clientes", produces = "application/json")
    public ResponseEntity<?> buscarTodos(@RequestParam @Nullable String cpf) {
        if (Objects.isNull(cpf)) {
            return ResponseEntity.ok(clienteUseCasePort.bucarTodos());
        } else {
            return ResponseEntity.ok(clienteUseCasePort.buscarPorCpf(new Cpf(cpf)));
        }
    }

    @GetMapping(value = "/clientes/{id}", produces = "application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable(name = "id") UUID uuid) {
        return ResponseEntity.ok(clienteUseCasePort.buscarPorId(uuid));
    }

    @PostMapping("/clientes")
    public ResponseEntity<?>  cadastrar(@RequestBody Cliente cliente) {
        if (Objects.nonNull(cliente)) {
            Cliente clienteDb = clienteUseCasePort.cadastrar(cliente);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDb.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/clientes/{cpf}")
    public ResponseEntity<?> identificarPorCpf(@PathVariable(name = "cpf") String cpf) {
        if (Objects.nonNull(cpf)) {
            Cliente clienteDb = clienteUseCasePort.identificarPorCpf(new Cpf(cpf));
            URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/tech-challenge/clientes/{id}").buildAndExpand(clienteDb.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
