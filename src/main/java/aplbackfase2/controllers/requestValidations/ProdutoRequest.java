package aplbackfase2.controllers.requestValidations;

import aplbackfase2.utils.enums.TipoProduto;
import aplbackfase2.entities.Produto;
import aplbackfase2.entities.DescricaoProduto;
import aplbackfase2.entities.NomeProduto;
import aplbackfase2.entities.ValorProduto;
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