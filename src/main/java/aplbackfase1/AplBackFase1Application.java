package aplbackfase1;

import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.model.valueObject.DescricaoProduto;
import aplbackfase1.domain.model.valueObject.NomeProduto;
import aplbackfase1.domain.model.valueObject.ValorProduto;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AplBackFase1Application {

	@Autowired
	private IProdutoUseCasePort produtoUseCasePort;

	public static void main(String[] args) {
		SpringApplication.run(AplBackFase1Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		Produto produto = this.produtoUseCasePort.criarProduto(
				Produto
						.builder()
						.nomeProduto(new NomeProduto("sdfff"))
						.descricaoProduto(new DescricaoProduto("sdfasfsafaf"))
						.tipoProduto(TipoProduto.ACOMPANHAMENTO)
						.valorProduto(new ValorProduto(new BigDecimal(5.0)))
						.build()
		);
		System.out.println(produto);

		List<Produto> produtos = this.produtoUseCasePort
					.listarProdutosPorTipoProduto(TipoProduto.ACOMPANHAMENTO);

		System.out.println(produtos);
	}
}
