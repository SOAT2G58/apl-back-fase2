package aplbackfase1.adapter.in.web.responses;

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
                .nome(Objects.isNull(produto.getNomeProduto()) ? null : produto.getNomeProduto().getNome())
                .descricao(Objects.isNull(produto.getDescricaoProduto()) ? null : produto.getDescricaoProduto().getDescricao())
                .tipo(Objects.isNull(produto.getTipoProduto()) ? null : produto.getTipoProduto().name())
                .valor(Objects.isNull(produto.getValorProduto())? null : produto.getValorProduto().getValorProduto())
                .build();
    }
}
