package aplbackfase1.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID idProduto;

    @Column(length = 20)
    private String descricaoProduto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    private boolean disponivel;

    public Produto() {
    }


    public void criaProduto(String descricaoProduto) {
        // a implementar
    }
    public Produto getProduto() {
        // a implementar
        return this;
    }
}
