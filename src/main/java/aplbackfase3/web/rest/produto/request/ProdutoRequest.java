package aplbackfase3.web.rest.produto.request;

import aplbackfase3.enums.TipoProduto;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.valueObjects.DescricaoProduto;
import aplbackfase3.domain.valueObjects.NomeProduto;
import aplbackfase3.domain.valueObjects.ValorProduto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequest {

    @NotEmpty(message = "nome não pode estar vazio")
    private String nome;
    @NotEmpty(message = "descricao não pode estar vazio")
    private String descricao;
    @NotEmpty(message = "tipo não pode estar vazio")
    private String tipo;
    @NotNull(message = "valor não pode ser nulo")
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