package aplbackfase1.infrastructure.persistence.repository;

import aplbackfase1.infrastructure.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Optional<List<ProdutoEntity>> findAllByTipoProduto(String tipoProduto);
}
