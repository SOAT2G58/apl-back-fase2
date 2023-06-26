package aplbackfase1.adapter.out.persistence.repository;

import aplbackfase1.adapter.out.persistence.entity.ProdutoEntity;
import aplbackfase1.domain.model.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Optional<List<ProdutoEntity>> findAllByTipoProduto(TipoProduto tipoProduto);
}
