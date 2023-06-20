package aplbackfase1.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Cliente {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID idCliente;
    private String nome;
    private String cpf;
    private String email;

    public Cliente getCliente() {
        return this;
    }

    public void identificaViaCpf(String cpf) {
        this.cpf = cpf;
    }

    public void cadastra(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public boolean validaIdentificacao() {
        return (!Objects.isNull(this.cpf)) || (!Objects.isNull(this.nome)) || (!Objects.isNull(this.email));
    }

}
