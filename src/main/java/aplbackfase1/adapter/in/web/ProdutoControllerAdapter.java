package aplbackfase1.adapter.in.web;

import aplbackfase1.adapter.in.web.exceptions.StandardError;
import aplbackfase1.adapter.in.web.requests.ProdutoRequest;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ProdutoControllerAdapter {

    private final IProdutoUseCasePort produtoUseCasePort;

    @GetMapping("/produtos")
    public ResponseEntity<?> buscarProduto(@RequestParam(value="tipo_produto") String tipoProduto) {
        List<Produto> produtoArrayList = this.produtoUseCasePort
                .listarProdutosPorTipoProduto(TipoProduto.fromCodigo(tipoProduto));
        return new ResponseEntity<>(produtoArrayList, HttpStatus.OK);
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> criarProduto(@RequestBody @NotNull ProdutoRequest request) {
        Produto produto = this.produtoUseCasePort.criarProduto(request.from(request));
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @DeleteMapping("/produtos")
    public ResponseEntity<?> deletarProduto(@RequestParam @NotNull String id) {
        this.produtoUseCasePort.deletarProduto(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}