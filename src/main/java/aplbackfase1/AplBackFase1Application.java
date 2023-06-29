package aplbackfase1;

import aplbackfase1.adapter.out.persistence.entity.ClienteEntity;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.exceptions.CpfExistenteException;
import aplbackfase1.domain.model.valueObject.*;
import aplbackfase1.domain.ports.in.IClienteUseCasePort;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AplBackFase1Application {

	@Autowired
	private IProdutoUseCasePort produtoUseCasePort;

	@Autowired
	private IClienteUseCasePort clienteUseCasePort;

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
		System.out.println("Produto criado:" + produto);

		System.out.println("listando todos os produtos do tipo bebida:" + this.produtoUseCasePort
				.listarProdutosPorTipoProduto(TipoProduto.BEBIDA));

		System.out.println("buscando produto por id:" + this.produtoUseCasePort
				.buscarProdutoPorID(UUID.randomUUID()));
		try {
			Cliente cliente = this.clienteUseCasePort.cadastrar(
					Cliente.builder()
							.nome(new Nome("Teste fulano de tal"))
							.email(new Email("contato@email.com"))
							.cpf(new Cpf("541.699.355-35"))
							.build());
			System.out.println(cliente);
			List<ClienteEntity> clientes = this.clienteUseCasePort.bucarTodos();

			System.out.println(clientes);
		} catch (CpfExistenteException e) {
			System.out.println("CPF já cadastrado");
		}

	}
}
