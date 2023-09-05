package aplbackfase2.controllers;

import aplbackfase2.adapters.ClienteDTO;
import aplbackfase2.controllers.requestValidations.ClienteRequest;
import aplbackfase2.entities.Cliente;
import aplbackfase2.entities.Cpf;
import aplbackfase2.interfaces.usecases.IClienteUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ClienteController {

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
    public ResponseEntity<?>  cadastrar(@RequestBody @Validated ClienteRequest clienteRequest) {
        if (Objects.nonNull(clienteRequest)) {
            Cliente clienteDb = clienteUseCasePort.cadastrar(clienteRequest.from(clienteRequest));
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDb.getId()).toUri();
            return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, uri.toString()).body(new ClienteDTO().from(clienteDb));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/clientes/{cpf}")
    public ResponseEntity<?> identificarPorCpf(@PathVariable(name = "cpf") String cpf) {
        if (Objects.nonNull(cpf)) {
            Cliente clienteDb = clienteUseCasePort.identificarPorCpf(new Cpf(cpf));
            URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/tech-challenge/clientes/{id}").buildAndExpand(clienteDb.getId()).toUri();
            return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, uri.toString()).body(new ClienteDTO().from(clienteDb));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/clientes/id")
    public ResponseEntity<?> gerarId() {
        UUID idCliente = clienteUseCasePort.gerarId();
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/tech-challenge/clientes/{id}").buildAndExpand(idCliente).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, uri.toString()).body(idCliente);
    }

}
