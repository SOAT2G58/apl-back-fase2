package aplbackfase1.application.web.configuration;


import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import aplbackfase1.domain.ports.in.IFilaUseCasePort;
import aplbackfase1.domain.ports.in.IClienteUseCasePort;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import aplbackfase1.domain.ports.out.IClienteRepositoryPort;
import aplbackfase1.domain.ports.out.IProdutoRepositoryPort;
import aplbackfase1.domain.usecases.PagamentoUseCaseImpl;
import aplbackfase1.domain.ports.out.IFilaRepositoryPort;
import aplbackfase1.domain.usecases.FilaUseCaseImpl;
import aplbackfase1.domain.usecases.ClienteUseCaseImpl;
import aplbackfase1.domain.usecases.ProdutoUseCaseImpl;
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
    public IClienteUseCasePort clienteUseCasePort(IClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteUseCaseImpl(clienteRepositoryPort);
    }
}
