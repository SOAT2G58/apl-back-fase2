package aplbackfase1.adapter.out.persistence.repository;

import aplbackfase1.adapter.out.persistence.entity.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Page<ProdutoEntity> findAllByTipoProduto(String tipoProduto, Pageable pageable);
}
