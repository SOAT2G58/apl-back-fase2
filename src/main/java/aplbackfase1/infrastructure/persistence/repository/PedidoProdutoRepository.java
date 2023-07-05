package aplbackfase1.infrastructure.persistence.repository;

import aplbackfase1.infrastructure.persistence.entity.PedidoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity, UUID> {
    // Define any custom queries if needed
}
