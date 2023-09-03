package aplbackfase3.database.repositories;

import aplbackfase3.database.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Optional<List<ProdutoEntity>> findAllByTipoProduto(String tipoProduto);
}
