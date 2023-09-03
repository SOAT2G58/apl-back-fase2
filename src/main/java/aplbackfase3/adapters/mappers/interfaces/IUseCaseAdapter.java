package aplbackfase3.adapters.mappers.interfaces;

import aplbackfase3.database.entities.ProdutoEntity;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;
import aplbackfase3.web.rest.produto.request.ProdutoRequest;

public interface IUseCaseAdapter {
    ProdutoDAO from(ProdutoRequest request);
    ProdutoDAO from(ProdutoEntity entity);
}
//+ callUseCaseAdapterFromController(Request): EntityDAO
//        + callUseCaseAdapterFromGTW(EntityDB): EntityDAO