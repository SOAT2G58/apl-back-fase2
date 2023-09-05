package aplbackfase2.controllers.requestValidations;

import aplbackfase2.entities.Cliente;
import aplbackfase2.entities.Cpf;
import aplbackfase2.entities.Nome;
import aplbackfase2.entities.Email;
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
