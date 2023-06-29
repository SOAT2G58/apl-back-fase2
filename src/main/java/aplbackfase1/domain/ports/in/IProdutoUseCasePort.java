package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.enums.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface IProdutoUseCasePort {
    Page<Produto> listarProdutosPorTipoProduto(TipoProduto tipoProduto, Pageable pageable);
    Optional<Produto> buscarProdutoPorID(UUID idProduto);
    Produto criarProduto(Produto produto);
    void deletarProduto(UUID idProduto);
}