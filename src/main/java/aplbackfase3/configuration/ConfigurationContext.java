package aplbackfase3.configuration;


import aplbackfase3.adapters.gateways.interfaces.IProdutoGateway;
import aplbackfase3.domain.interfaces.IProdutoUseCasePort;
import aplbackfase3.domain.usecases.ProdutoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationContext {

    @Bean
    public IProdutoUseCasePort produtoUseCasePort(IProdutoGateway gtw) {
        return new ProdutoUseCaseImpl(gtw);
    }
}
