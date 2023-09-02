package aplbackfase3.web.rest;

import aplbackfase2.adapters.ProdutoDTO;
import aplbackfase3.adapters.controllers.interfaces.IProdutoController;
import aplbackfase3.web.rest.requestValidations.ProdutoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ProdutoAPIController {
    private final IProdutoController produtoController;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> buscarProduto(@RequestParam(value="tipo_produto") @Validated @NotBlank String tipoProduto) {
        return new ResponseEntity<>(this.produtoController.buscarProduto(tipoProduto), HttpStatus.OK);
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> criarProduto(@RequestBody @Validated ProdutoRequest request) {
        return new ResponseEntity<>(this.produtoController.criarProduto(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable @NotNull UUID id) {
        this.produtoController.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}