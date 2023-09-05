package aplbackfase2.controllers;

import aplbackfase2.utils.enums.TipoProduto;
import aplbackfase2.adapters.ProdutoDTO;
import aplbackfase2.interfaces.usecases.IProdutoUseCasePort;
import aplbackfase2.usecases.requestValidations.ProdutoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class ProdutoController {

    private final IProdutoUseCasePort produtoUseCasePort;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> buscarProduto(@RequestParam(value="tipo_produto") @Validated @NotBlank String tipoProduto) {
        List<ProdutoDTO> produtoArrayList = this.produtoUseCasePort
                .listarProdutosPorTipoProduto(TipoProduto.fromCodigo(tipoProduto));
        final var produtoDTOList = new ArrayList<ProdutoDTO>();
        produtoArrayList.forEach(produto -> produtoDTOList.add(new ProdutoDTO().from(produto)));
        return new ResponseEntity<>(produtoDTOList, HttpStatus.OK);
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> criarProduto(@RequestBody @Validated ProdutoRequest request) {
        ProdutoDTO produto = this.produtoUseCasePort.criarProduto(request.from(request));
        return new ResponseEntity<>(new ProdutoDTO().from(produto), HttpStatus.CREATED);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable @NotNull UUID id) {
        this.produtoUseCasePort.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}