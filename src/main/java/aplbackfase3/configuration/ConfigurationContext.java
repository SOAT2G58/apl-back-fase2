package aplbackfase3.configuration;


import aplbackfase3.adapters.gateways.interfaces.IProdutoGateway;
import aplbackfase3.domain.interfaces.IProdutoUseCase;
import aplbackfase3.domain.usecases.ProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationContext {

    @Bean
    public IProdutoUseCase produtoUseCase(IProdutoGateway gtw) {
        return new ProdutoUseCaseImpl(gtw);
    }
}
