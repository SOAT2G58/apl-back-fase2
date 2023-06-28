package aplbackfase1.adapter.in.web;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ProdutoControllerAdapter {

    private final IProdutoUseCasePort produtoUseCasePort;

    @GetMapping("/produtos")
    @ResponseBody
    public ResponseEntity<?> buscarProduto(@RequestParam @Nullable String tipoProduto, @RequestParam @Nullable String id) {
        if(null != tipoProduto) {
            return new ResponseEntity<>(this.produtoUseCasePort
                    .listarProdutosPorTipoProduto(
                            TipoProduto.fromCodigo(tipoProduto)).get(),
                            HttpStatus.OK
            );
        } else if(null != id) {
            Optional<Produto> produto = this.produtoUseCasePort.buscarProdutoPorID(UUID.fromString(id));
            return produto.isEmpty() ?
                    new ResponseEntity<>(HttpStatus.OK) :
                    new ResponseEntity<>(produto.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/produtos")
    @ResponseBody
    public ResponseEntity<?> criarProduto(Produto request) {
        if(null != request) {
           Produto produto = this.produtoUseCasePort.criarProduto(request);
            return new ResponseEntity<>(produto, HttpStatus.CREATED);
        } else {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/produtos")
    @ResponseBody
    public ResponseEntity<?> deletarProduto(@RequestParam String id) {
        if(null != id) {
            this.produtoUseCasePort.deletarProduto(UUID.fromString(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}