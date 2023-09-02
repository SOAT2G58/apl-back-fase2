package aplbackfase3;

import aplbackfase2.utils.enums.TipoProduto;
import aplbackfase3.domain.entities.Produto;
import aplbackfase3.domain.interfaces.IProdutoUseCasePort;
import aplbackfase3.domain.valueObjects.DescricaoProduto;
import aplbackfase3.domain.valueObjects.NomeProduto;
import aplbackfase3.domain.valueObjects.ValorProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;

@SpringBootApplication
public class AplBackFase1Application {

	@Autowired
	private IProdutoUseCasePort produtoUseCasePort;

	public static void main(String[] args) {
		SpringApplication.run(AplBackFase1Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		this.mockProduto();
	}

	private void mockProduto() {
		for(var i = 0; i<= 5; i++) {
			var quant = i + 1;
			this.produtoUseCasePort.criarProduto(
					Produto.builder().nomeProduto(new NomeProduto(TipoProduto.ACOMPANHAMENTO.name() + quant))
							.descricaoProduto(new DescricaoProduto("Descricao produto: " + quant))
							.tipoProduto(TipoProduto.ACOMPANHAMENTO)
							.valorProduto(new ValorProduto(new BigDecimal(5.0 + quant))).build()
			);
			this.produtoUseCasePort.criarProduto(
					Produto.builder().nomeProduto(new NomeProduto(TipoProduto.BEBIDA.name() + quant))
							.descricaoProduto(new DescricaoProduto("Descricao produto: " + quant))
							.tipoProduto(TipoProduto.BEBIDA)
							.valorProduto(new ValorProduto(new BigDecimal(5.0 + quant))).build()
			);
			this.produtoUseCasePort.criarProduto(
					Produto.builder().nomeProduto(new NomeProduto(TipoProduto.LANCHE.name() + quant))
							.descricaoProduto(new DescricaoProduto("Descricao produto: " + quant))
							.tipoProduto(TipoProduto.LANCHE)
							.valorProduto(new ValorProduto(new BigDecimal(5.0 + quant))).build()
			);
			this.produtoUseCasePort.criarProduto(
					Produto.builder().nomeProduto(new NomeProduto(TipoProduto.SOBREMESA.name() + quant))
							.descricaoProduto(new DescricaoProduto("Descricao produto: " + quant))
							.tipoProduto(TipoProduto.SOBREMESA)
							.valorProduto(new ValorProduto(new BigDecimal(5.0 + quant))).build()
			);
		}
	}
}
