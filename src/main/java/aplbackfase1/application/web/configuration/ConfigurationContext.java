package aplbackfase1.application.web.configuration;


import aplbackfase1.domain.ports.in.*;
import aplbackfase1.domain.ports.out.*;
import aplbackfase1.domain.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationContext {

    @Bean
    public IProdutoUseCasePort produtoUseCasePort(IProdutoRepositoryPort produtoRepositoryPort) {
        return new ProdutoUseCaseImpl(produtoRepositoryPort);
    }

    @Bean
    public IPagamentoUseCase pagamentoUseCase() {
        return new PagamentoUseCaseImpl();
    }

    @Bean
    public IFilaUseCasePort iPedidoFilaUseCasePort(IFilaRepositoryPort filaRepositoryPort) {
        return new FilaUseCaseImpl(filaRepositoryPort);
    }

    @Bean
    public IPedidoUseCasePort pedidoUseCasePort(IPedidoRepositoryPort pedidoRepositoryPort,
                                                IPedidoProdutoRepositoryPort pedidoProdutoRepositoryPort,
                                                FilaUseCaseImpl filaUseCaseImpl, PagamentoUseCaseImpl pagamentoUseCaseImpl) {
        return new PedidoUseCaseImpl(pagamentoUseCaseImpl, filaUseCaseImpl, pedidoRepositoryPort, pedidoProdutoRepositoryPort);
    }

    @Bean
    public IPedidoProdutoUseCasePort pedidoProdutoUseCasePort(IPedidoRepositoryPort pedidoRepositoryPort,
                                                              IPedidoProdutoRepositoryPort pedidoProdutoRepositoryPort) {
        return new PedidoProdutoUseCaseImpl(pedidoRepositoryPort, pedidoProdutoRepositoryPort);
    }

    @Bean
    public IClienteUseCasePort clienteUseCasePort(IClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteUseCaseImpl(clienteRepositoryPort);
    }

}
