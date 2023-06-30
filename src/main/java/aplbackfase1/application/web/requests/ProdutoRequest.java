package aplbackfase1.application.web.requests;

import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.valueObject.DescricaoProduto;
import aplbackfase1.domain.model.valueObject.NomeProduto;
import aplbackfase1.domain.model.valueObject.ValorProduto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private String tipo;

    @NotNull
    private BigDecimal valor;

    public Produto from(ProdutoRequest request) {
        return Produto.builder()
                .nomeProduto(new NomeProduto(request.getNome()))
                .tipoProduto(TipoProduto.fromCodigo(request.getTipo()))
                .descricaoProduto(new DescricaoProduto(request.getDescricao()))
                .valorProduto(new ValorProduto(request.getValor()))
                .build();
    }
}