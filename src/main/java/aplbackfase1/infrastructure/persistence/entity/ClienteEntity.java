package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase2.entities.Cliente;
import aplbackfase2.entities.valueObject.Cpf;
import aplbackfase2.entities.valueObject.Nome;
import aplbackfase2.entities.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clientes")
public class ClienteEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Embedded
    private Nome nome;
    @Embedded
    private Cpf cpf;
    @Embedded
    private Email email;

    public Cliente to(ClienteEntity clienteEntity) {
        return Cliente.builder()
                .id(clienteEntity.getId())
                .nome(clienteEntity.getNome())
                .cpf(clienteEntity.getCpf())
                .email(clienteEntity.getEmail())
                .build();

    }

    public ClienteEntity from(Cliente cliente) {
        return ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .build();
    }

}
