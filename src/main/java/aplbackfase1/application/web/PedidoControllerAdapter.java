package aplbackfase1.application.web;

import aplbackfase1.application.web.requests.PedidoProdutoRequest;
import aplbackfase1.application.web.requests.PedidoRequest;
import aplbackfase1.application.web.responses.PedidoDTO;
import aplbackfase1.application.web.responses.PedidoProdutoDTO;
import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.ports.in.IPedidoProdutoUseCasePort;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class PedidoControllerAdapter {

    private final IPedidoUseCasePort pedidoUseCasePort;
    private final IPedidoProdutoUseCasePort pedidoProdutoUseCasePort;

    @PostMapping("/pedido")
    public ResponseEntity<PedidoDTO> cadastrar(@RequestBody @NotNull PedidoRequest request) {
        if (request.getIdCliente() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Pedido pedido = this.pedidoUseCasePort.cadastrar(request.from(request));
        if (pedido != null) {
            return new ResponseEntity<>(PedidoDTO.from(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*
    // Seria para editar o Pedido como um todo
    @PutMapping("/pedido")
    public ResponseEntity<PedidoDTO> atualizar(@RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoRequest.from(pedidoRequest);
        Pedido updatedPedido = pedidoUseCasePort.atualizar(pedido);
        if (updatedPedido != null) {
            PedidoDTO pedidoDTO = PedidoDTO.from(updatedPedido);
            return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    */

    @PostMapping("/pedido/{idPedido}/produto")
    public ResponseEntity<PedidoProdutoDTO> adicionarPedidoProduto(
            @PathVariable UUID idPedido, @RequestBody @NotNull PedidoProdutoRequest request) {
        PedidoProduto pedidoProduto = request.from(request);
        pedidoProduto.setPedidoId(idPedido);
        PedidoProduto addedPedidoProduto = pedidoProdutoUseCasePort.adicionarPedidoProduto(pedidoProduto);
        if (addedPedidoProduto != null) {
            PedidoProdutoDTO pedidoProdutoDTO = PedidoProdutoDTO.from(addedPedidoProduto);
            return new ResponseEntity<>(pedidoProdutoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/pedido/{idPedido}/produto/{idPedidoProduto}")
    public ResponseEntity<PedidoProdutoDTO> editarPedidoProduto(
            @PathVariable UUID idPedido, @PathVariable UUID idPedidoProduto, @RequestBody PedidoProdutoRequest pedidoProdutoRequest) {
        PedidoProduto pedidoProduto = pedidoProdutoRequest.from(pedidoProdutoRequest);
        pedidoProduto.setPedidoId(idPedido);
        pedidoProduto.setId(idPedidoProduto);
        PedidoProduto updatedPedidoProduto = pedidoProdutoUseCasePort.editarPedidoProduto(pedidoProduto);
        if (updatedPedidoProduto != null) {
            PedidoProdutoDTO pedidoProdutoDTO = PedidoProdutoDTO.from(updatedPedidoProduto);
            return new ResponseEntity<>(pedidoProdutoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/pedido/{idPedido}/produto/{id}")
    public ResponseEntity<PedidoDTO> excluirPedidoProduto(@PathVariable UUID idPedido, @PathVariable UUID id) {
        pedidoProdutoUseCasePort.deletarPedidoProduto(id);
        Optional<Pedido> pedidoOptional = pedidoUseCasePort.buscarPorId(idPedido);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            PedidoDTO pedidoDTO = PedidoDTO.from(pedido);
            return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pedido/{idPedido}")
    public ResponseEntity<Void> remover(@PathVariable UUID idPedido) {
        pedidoUseCasePort.remover(idPedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoDTO>> buscarTodos(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "100") int pageSize) {
        List<Pedido> pedidos = pedidoUseCasePort.buscarTodos(pageNumber, pageSize);
        List<PedidoDTO> pedidoDTOs = pedidos.stream()
                .map(PedidoDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidoDTOs, HttpStatus.OK);
    }


    @GetMapping("/pedidos/id/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable UUID id) {
        return pedidoUseCasePort.buscarPorId(id)
                .map(pedido -> new ResponseEntity<>(PedidoDTO.from(pedido), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pedidos/cliente/{idCliente}")
    public ResponseEntity<List<PedidoDTO>> buscarPedidosPorCliente(@PathVariable UUID idCliente) {
        List<Pedido> pedidos = pedidoUseCasePort.buscarPedidosPorCliente(idCliente);
        List<PedidoDTO> pedidoDTOs = pedidos.stream()
                .map(PedidoDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidoDTOs, HttpStatus.OK);
    }

    @GetMapping("/pedidos/status/{statusPedido}")
    public ResponseEntity<List<PedidoDTO>> buscarPedidosPorStatus(@PathVariable StatusPedido statusPedido) {
        List<Pedido> pedidos = pedidoUseCasePort.buscarPedidosPorStatus(statusPedido);
        List<PedidoDTO> pedidoDTOs = pedidos.stream()
                .map(PedidoDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidoDTOs, HttpStatus.OK);
    }

    @GetMapping("/pedidos/cliente/{idCliente}/status/{statusPedido}")
    public ResponseEntity<List<PedidoDTO>> buscarPedidosPorClienteEStatus(@PathVariable UUID idCliente, @PathVariable StatusPedido statusPedido) {
        List<Pedido> pedidos = pedidoUseCasePort.buscarPedidosPorClienteEStatus(idCliente, statusPedido);
        List<PedidoDTO> pedidoDTOs = pedidos.stream()
                .map(PedidoDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pedidoDTOs, HttpStatus.OK);
    }

    @PostMapping("/pedido/checkout/{idPedido}")
    public ResponseEntity<Pedido> checkout(@PathVariable UUID idPedido) {
        return new ResponseEntity<>(pedidoUseCasePort.checkout(idPedido), HttpStatus.OK);
    }
}
