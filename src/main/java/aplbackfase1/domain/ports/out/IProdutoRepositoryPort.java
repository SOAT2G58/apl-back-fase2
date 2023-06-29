package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.enums.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface IProdutoRepositoryPort {
    Page<Produto> listarProdutosPorTipo(TipoProduto tipoProduto, Pageable pageable);
    Optional<Produto> buscarProdutoPorId(UUID idProduto);

    Produto criarProduto(Produto produto);

    void deletarProduto(UUID idProduto);
}
