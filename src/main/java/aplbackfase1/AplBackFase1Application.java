package aplbackfase1;

import aplbackfase1.domain.enums.TipoProduto;
import aplbackfase1.domain.exceptions.CpfExistenteException;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.valueObject.*;
import aplbackfase1.domain.ports.in.IClienteUseCasePort;
import aplbackfase1.domain.ports.in.IPedidoProdutoUseCasePort;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;
import java.util.List;

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
		this.mockProduto();
		this.mockCliente();
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

		System.out.println("listando todos os produtos do tipo ".concat(TipoProduto.BEBIDA.name()) + this.produtoUseCasePort
				.listarProdutosPorTipoProduto(TipoProduto.BEBIDA));
		System.out.println("listando todos os produtos do tipo ".concat(TipoProduto.SOBREMESA.name()) + this.produtoUseCasePort
				.listarProdutosPorTipoProduto(TipoProduto.SOBREMESA));
		System.out.println("listando todos os produtos do tipo ".concat(TipoProduto.ACOMPANHAMENTO.name()) + this.produtoUseCasePort
				.listarProdutosPorTipoProduto(TipoProduto.ACOMPANHAMENTO));
		System.out.println("listando todos os produtos do tipo ".concat(TipoProduto.LANCHE.name()) + this.produtoUseCasePort
				.listarProdutosPorTipoProduto(TipoProduto.LANCHE));
	}

	private void mockCliente(){
		try {
			Cliente cliente = this.clienteUseCasePort.cadastrar(
					Cliente.builder()
							.nome(new Nome("Murilo Benjamin Gabriel das Neves"))
							.email(new Email("murilo-dasneves84@ppe.ufrj.br"))
							.cpf(new Cpf("420.390.450-15"))
							.build());
			System.out.println(cliente);
		} catch (CpfExistenteException e) {
			System.out.println("CPF já cadastrado");
		}

		try {
			Cliente cliente = this.clienteUseCasePort.cadastrar(
					Cliente.builder()
							.nome(new Nome("Eliane Catarina Milena Ribeiro"))
							.email(new Email("eliane_catarina_ribeiro@esctech.com.br"))
							.cpf(new Cpf("974.971.471-70"))
							.build());
			System.out.println(cliente);
		} catch (CpfExistenteException e) {
			System.out.println("CPF já cadastrado");
		}

		try {
			Cliente cliente = this.clienteUseCasePort.identificarPorCpf(new Cpf("161.807.409-17"));
			System.out.println(cliente);
		} catch (CpfExistenteException e) {
			System.out.println("CPF já cadastrado");
		}

		try {
			List<Cliente> clientes = this.clienteUseCasePort.bucarTodos();
			System.out.println(clientes);
		} catch (CpfExistenteException e) {
			System.err.println("Problemas na listagem de todos clientes");
		}
	}
}
