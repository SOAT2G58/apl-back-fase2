package aplbackfase2.facade;

import aplbackfase2.interfaces.gateways.IClienteRepositoryPort;
import aplbackfase2.interfaces.gateways.IFilaRepositoryPort;
import aplbackfase2.interfaces.gateways.IPedidoProdutoRepositoryPort;
import aplbackfase2.interfaces.gateways.IPedidoRepositoryPort;
import aplbackfase2.interfaces.gateways.IProdutoRepositoryPort;
import aplbackfase2.interfaces.usecases.IClienteUseCasePort;
import aplbackfase2.interfaces.usecases.IFilaUseCasePort;
import aplbackfase2.interfaces.usecases.IPagamentoUseCase;
import aplbackfase2.interfaces.usecases.IPedidoProdutoUseCasePort;
import aplbackfase2.interfaces.usecases.IPedidoUseCasePort;
import aplbackfase2.interfaces.usecases.IProdutoUseCasePort;
import aplbackfase2.usecases.*;

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
                                                IFilaUseCasePort filaUseCasePort, IPagamentoUseCase pagamentoUseCase) {
        return new PedidoUseCaseImpl(pagamentoUseCase, filaUseCasePort, pedidoRepositoryPort, pedidoProdutoRepositoryPort);
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
