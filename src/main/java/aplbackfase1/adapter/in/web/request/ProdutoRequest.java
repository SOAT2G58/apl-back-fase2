package aplbackfase1.adapter.in.web.request;

import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.valueObject.DescricaoProduto;
import aplbackfase1.domain.model.valueObject.NomeProduto;
import aplbackfase1.domain.model.valueObject.ValorProduto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

    @JsonProperty("nome_produto")
    private String nomeProduto;
    @JsonProperty("descricao_produto")
    private String descricaoProduto;
    @JsonProperty("tipo_produto")
    private String tipoProduto;
    @JsonProperty("valor_produto")
    private BigDecimal valorProduto;

    public Produto from(ProdutoRequest request) {
        return Produto.builder()
                .nomeProduto(new NomeProduto(request.getNomeProduto()))
                .tipoProduto(TipoProduto.fromCodigo(request.getTipoProduto()))
                .descricaoProduto(new DescricaoProduto(request.getDescricaoProduto()))
                .valorProduto(new ValorProduto(request.getValorProduto()))
                .build();
    }
}
