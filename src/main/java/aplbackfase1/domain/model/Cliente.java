package aplbackfase1.domain.model;

import aplbackfase1.domain.model.valueObject.Cpf;
import aplbackfase1.domain.model.valueObject.Email;
import aplbackfase1.domain.model.valueObject.Nome;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public final class Cliente {
    private UUID id;
    private Nome nome;
    private Cpf cpf;
    private Email email;
}
