package aplbackfase2.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aplbackfase2.gateways.entities.ProdutoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Optional<List<ProdutoEntity>> findAllByTipoProduto(String tipoProduto);
}
