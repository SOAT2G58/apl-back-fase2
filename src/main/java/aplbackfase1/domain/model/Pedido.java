package aplbackfase1.domain.model;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@EqualsAndHashCode
public class Pedido {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID idPedido;

    @Type(type="uuid-char")
    private UUID idCliente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinalizacao;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private StatusPedido status;

    // a implementar relacao junto a Produtos
    @OneToMany
    private List<Produto> itens;

    public void criaPedido(UUID idCliente) {
        this.idCliente = idCliente;
        this.dataCriacao = new Date();
    }

    public Pedido getPedido() {
        return this;
    }

    public StatusPedido getStatusPedido() {
        return this.status;
    }

    public void atualizaStatusPedido(StatusPedido novoStatus) {
        this.status = novoStatus;
        this.dataAtualizacao = new Date();
    }

    public void finalizaPedido() {
        this.status = StatusPedido.F;
        Date dataAtual = new Date();
        this.dataAtualizacao = dataAtual;
        this.dataFinalizacao = dataAtual;
    }

}