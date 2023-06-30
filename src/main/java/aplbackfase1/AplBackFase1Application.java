package aplbackfase1;

import aplbackfase1.adapter.out.persistence.entity.ClienteEntity;
import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.Produto;
import aplbackfase1.domain.model.TipoProduto;
import aplbackfase1.domain.model.exceptions.CpfExistenteException;
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
		System.out.println(produto);

		List<Produto> produtos = this.produtoUseCasePort
					.listarProdutosPorTipoProduto(TipoProduto.ACOMPANHAMENTO);

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
