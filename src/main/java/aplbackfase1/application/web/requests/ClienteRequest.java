package aplbackfase1.application.web.requests;

import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.model.valueObject.Email;
import aplbackfase1.domain.model.valueObject.Nome;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ClienteRequest {

    @NotEmpty(message = "nome não pode estar vazio")
    private String nome;
    @NotEmpty(message = "cpf não pode estar vazio")
    private String cpf;
    @NotEmpty(message = "email não pode estar vazio")
    private String email;

    public Cliente from(ClienteRequest request) {
        return Cliente.builder()
                .nome(new Nome(request.getNome()))
                .cpf(new Cpf(request.getCpf()))
                .email(new Email(request.getEmail()))
                .build();
    }

}
