package aplbackfase2.adapters;

import aplbackfase2.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteDTO {
    private UUID id;
    private String nome;
    private String cpf;
    private String email;

    public ClienteDTO from(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(Objects.isNull(cliente.getNome()) ? null : cliente.getNome().getNome())
                .cpf(Objects.isNull(cliente.getCpf()) ? null : cliente.getCpf().getCpf())
                .email(Objects.isNull(cliente.getEmail()) ? null : cliente.getEmail().getEmail())
                .build()
        ;
    }
}
