package aplbackfase3.adapters.mappers.interfaces;

import aplbackfase3.domain.entities.Produto;
import aplbackfase3.web.rest.produto.response.ProdutoResponse;

public interface IRestAPIAdapter {
    ProdutoResponse from(Produto produto);
}
