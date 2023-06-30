package aplbackfase1.application.web;

import aplbackfase1.application.web.requests.ProdutoRequest;
import aplbackfase1.application.web.responses.ProdutoDTO;
import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ProdutoControllerAdapter {

    private final IProdutoUseCasePort produtoUseCasePort;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> buscarProduto(@RequestParam(value="tipo_produto") String tipoProduto) {
        List<Produto> produtoArrayList = this.produtoUseCasePort
                .listarProdutosPorTipoProduto(TipoProduto.fromCodigo(tipoProduto));
        final var produtoDTOList = new ArrayList<ProdutoDTO>();
        produtoArrayList.forEach(produto -> produtoDTOList.add(new ProdutoDTO().from(produto)));
        return new ResponseEntity<>(produtoDTOList, HttpStatus.OK);
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> criarProduto(@RequestBody @NotNull ProdutoRequest request) {
        Produto produto = this.produtoUseCasePort.criarProduto(request.from(request));
        return new ResponseEntity<>(new ProdutoDTO().from(produto), HttpStatus.CREATED);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable @NotNull UUID id) {
        this.produtoUseCasePort.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}