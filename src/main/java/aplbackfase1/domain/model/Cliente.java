package aplbackfase1.domain.model;

import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.model.valueObject.Email;
import aplbackfase1.domain.model.valueObject.Nome;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private UUID id;
    private Nome nome;
    private Cpf cpf;
    private Email email;

    public Cliente(UUID id, String nome, String email, String cpf) {
        this(id, new Nome(nome), new Email(email), new Cpf(cpf));
    }

    public Cliente(UUID id, Nome nome, Email email, Cpf cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente getCliente() {
        return this;
    }

    public UUID getId() {
        return this.id;
    }

    public Nome getNome() {
        return this.nome;
    }

    public Email getEmail() {
        return this.email;
    }

    public Cpf getCpf() {
        return this.cpf;
    }

}
