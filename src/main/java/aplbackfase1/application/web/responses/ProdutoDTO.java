package aplbackfase1.application.web.responses;

import aplbackfase1.domain.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
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
