package aplbackfase1.adapter.out.persistence.entity;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.model.valueObject.DescricaoProduto;
import aplbackfase1.domain.model.valueObject.NomeProduto;
import aplbackfase1.domain.model.valueObject.ValorProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue
    private UUID idProduto;
    private NomeProduto nomeProduto;
    private DescricaoProduto descricaoProduto;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private TipoProduto tipoProduto;

    private ValorProduto valorProduto;

    public Produto to(ProdutoEntity produtoEntity) {
        return Produto.builder()
                .idProduto(produtoEntity.getIdProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .tipoProduto(produtoEntity.getTipoProduto())
                .valorProduto(produtoEntity.getValorProduto())
                .build();
    }

    public ProdutoEntity from(Produto produto) {
        return ProdutoEntity.builder()
                .nomeProduto(produto.getNomeProduto())
                .descricaoProduto(produto.getDescricaoProduto())
                .tipoProduto(produto.getTipoProduto())
                .valorProduto(produto.getValorProduto())
                .build();
    }
}