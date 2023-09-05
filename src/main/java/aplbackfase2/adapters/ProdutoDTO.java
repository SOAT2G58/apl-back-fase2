package aplbackfase2.adapters;

import aplbackfase2.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProdutoDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private String tipo;
    private BigDecimal valor;

    public ProdutoDTO from(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getIdProduto())
                .nome(produto.getNomeProduto().getNome())
                .descricao(produto.getDescricaoProduto().getDescricao())
                .tipo(produto.getTipoProduto().name())
                .valor(produto.getValorProduto().getValorProduto())
                .build();
    }
}
