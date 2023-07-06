package aplbackfase1.application.web.configuration;


import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import aplbackfase1.domain.ports.in.IProdutoUseCasePort;
import aplbackfase1.domain.ports.out.IProdutoRepositoryPort;
import aplbackfase1.domain.usecases.PagamentoUseCaseImpl;
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
}
