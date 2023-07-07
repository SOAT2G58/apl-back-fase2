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
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class PedidoControllerAdapter {

    private final IPedidoUseCasePort pedidoUseCasePort;
    private final IPedidoProdutoUseCasePort pedidoProdutoUseCasePort;

    @PostMapping("/pedido")
    public ResponseEntity<PedidoDTO> cadastrar(@RequestBody @NotNull PedidoRequest request) {
        Pedido pedido = this.pedidoUseCasePort.cadastrar(request.from(request));
        if (pedido != null) {
            return new ResponseEntity<>(PedidoDTO.from(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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

    @PutMapping("/pedido/{idPedido}/produto")
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

    @PutMapping("/pedido/{idPedido}/produto/{id}")
    public ResponseEntity<PedidoProdutoDTO> editarPedidoProduto(
            @PathVariable UUID idPedido, @PathVariable UUID id, @RequestBody PedidoProdutoRequest pedidoProdutoRequest) {
        PedidoProduto pedidoProduto = pedidoProdutoRequest.from(pedidoProdutoRequest);
        pedidoProduto.setPedidoId(idPedido);
        pedidoProduto.setId(id);
        PedidoProduto updatedPedidoProduto = pedidoProdutoUseCasePort.editarPedidoProduto(pedidoProduto);
        if (updatedPedidoProduto != null) {
            PedidoProdutoDTO pedidoProdutoDTO = PedidoProdutoDTO.from(updatedPedidoProduto);
            return new ResponseEntity<>(pedidoProdutoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/pedido/{idPedido}/produto/{id}")
    public ResponseEntity<Void> excluirPedidoProduto(@PathVariable UUID id) {
        pedidoProdutoUseCasePort.deletarPedidoProduto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID idPedido) {
        pedidoUseCasePort.remover(idPedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pedido")
    public ResponseEntity<List<Pedido>> buscarTodos(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "100") int pageSize) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarTodos(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pedido/id/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable UUID id) {
        return pedidoUseCasePort.buscarPorId(id)
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pedido/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorCliente(@PathVariable UUID idCliente) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarPedidosPorCliente(idCliente), HttpStatus.OK);
    }

    @GetMapping("/pedido/status/{statusPedido}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorStatus(@PathVariable StatusPedido statusPedido) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarPedidosPorStatus(statusPedido), HttpStatus.OK);
    }

    @GetMapping("/pedido/cliente/{idCliente}/status/{statusPedido}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorClienteEStatus(@PathVariable UUID idCliente, @PathVariable StatusPedido statusPedido) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarPedidosPorClienteEStatus(idCliente, statusPedido), HttpStatus.OK);
    }

    @PostMapping("/pedido/checkout/{id}")
    public ResponseEntity<Pedido> checkout(@PathVariable UUID id) {
        return new ResponseEntity<>(pedidoUseCasePort.checkout(id), HttpStatus.OK);
    }
}
