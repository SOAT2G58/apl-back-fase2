package aplbackfase1.adapter.out.persistence;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.ports.out.IProdutoDataBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoDataBaseImpl implements IProdutoDataBaseService {
    @Override
    public List<Produto> buscarProdutos(TipoProduto tipoProduto) {
        return null;
    }
}
