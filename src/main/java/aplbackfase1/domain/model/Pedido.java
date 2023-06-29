package aplbackfase1.domain.model;

import aplbackfase1.domain.enums.StatusPedido;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode
public class Pedido {

    private UUID idPedido;

    private UUID idCliente;

    private Date dataCriacao;

    private Date dataAtualizacao;

    private Date dataFinalizacao;

    private StatusPedido status;

    // a implementar relacao junto a Produtos

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