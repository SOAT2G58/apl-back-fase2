package aplbackfase1.adapter.in.web;

import aplbackfase1.adapter.in.web.responses.ClienteDTO;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.ports.in.IClienteUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ClienteControllerAdapter {

    private final IClienteUseCasePort clienteUseCasePort;

    @GetMapping(value = "/clientes", produces = "application/json")
    public ResponseEntity<List<ClienteDTO>> buscarTodos(@RequestParam @Nullable String cpf) {
        if (Objects.isNull(cpf)){
            return ResponseEntity.ok(clienteUseCasePort.bucarTodos().stream().map(obj -> new ClienteDTO().from(obj)).collect(Collectors.toList()));
        } else {
            Optional<Cliente> cliente = clienteUseCasePort.buscarPorCpf(new Cpf(cpf));
            if (cliente.isPresent())
                return ResponseEntity.ok(Arrays.asList(new ClienteDTO().from(cliente.get())));
            else
                return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/clientes/{id}", produces = "application/json")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable(name = "id") UUID uuid) {
        Optional<Cliente> cliente = clienteUseCasePort.buscarPorId(uuid);
        if (cliente.isPresent())
            return ResponseEntity.ok(new ClienteDTO().from(cliente.get()));
        else
            return ResponseEntity.notFound().build();
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
