package aplbackfase3;

import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.domain.interfaces.IProdutoUseCase;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;

@SpringBootApplication
public class AplBackFase1Application {

	@Autowired
	private IProdutoUseCase produtoUseCase;

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
			this.produtoUseCase.criarProduto(
					ProdutoDAO.builder().nome(TipoProduto.ACOMPANHAMENTO.name() + quant)
							.descricao("Descricao produto: " + quant)
							.tipo(TipoProduto.ACOMPANHAMENTO.getCodigo())
							.valor(new BigDecimal(5.0 + quant)).build()
			);
			this.produtoUseCase.criarProduto(
					ProdutoDAO.builder().nome(TipoProduto.BEBIDA.name() + quant)
							.descricao("Descricao produto: " + quant)
							.tipo(TipoProduto.BEBIDA.getCodigo())
							.valor(new BigDecimal(5.0 + quant)).build()
			);
			this.produtoUseCase.criarProduto(
					ProdutoDAO.builder().nome(TipoProduto.LANCHE.name() + quant)
							.descricao("Descricao produto: " + quant)
							.tipo(TipoProduto.LANCHE.getCodigo())
							.valor(new BigDecimal(5.0 + quant)).build()
			);
			this.produtoUseCase.criarProduto(
					ProdutoDAO.builder().nome(TipoProduto.SOBREMESA.name() + quant)
							.descricao("Descricao produto: " + quant)
							.tipo(TipoProduto.SOBREMESA.getCodigo())
							.valor(new BigDecimal(5.0 + quant)).build()
			);
		}
	}
}
