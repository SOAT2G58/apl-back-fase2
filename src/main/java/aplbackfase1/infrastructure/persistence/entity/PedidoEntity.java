package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.infrastructure.persistence.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID_PEDIDO")
    private UUID idPedido;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoProdutoEntity> produtos;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_STATUS")
    private StatusPedido statusPedido;

    @Column(name = "V_PEDIDO")
    private BigDecimal valorPedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_H_INC")
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_H_ATC")
    private Date dataAtualizacao;

    public Pedido to(PedidoEntity pedidoEntity) {
        List<PedidoProduto> pedidoProdutos = pedidoEntity.getProdutos().stream()
                .map(ppEntity -> PedidoProduto.builder()
                        .idPedido(ppEntity.getPedido().getIdPedido())
                        .idProduto(ppEntity.getProduto().getIdProduto())
                        .valorProduto(ppEntity.getValorProduto())
                        .observacaoProduto(ppEntity.getObservacaoProduto())
                        .build())
                .collect(Collectors.toList());

        return Pedido.builder()
                .idPedido(pedidoEntity.getIdPedido())
                .idCliente(pedidoEntity.getCliente().getId())
                .produtos(pedidoProdutos)
                .statusPedido(pedidoEntity.getStatusPedido())
                .valorPedido(pedidoEntity.getValorPedido())
                .dataInclusao(pedidoEntity.getDataInclusao())
                .dataAtualizacao(pedidoEntity.getDataAtualizacao())
                .build();
    }

    public PedidoEntity fromModel(Pedido pedido, ClienteEntity clienteEntity, ProdutoRepository produtoRepository) {
        List<PedidoProdutoEntity> pedidoProdutoEntities = pedido.getProdutos().stream()
                .map(pp -> {
                    ProdutoEntity produtoEntity = produtoRepository.findById(pp.getIdProduto()).orElseThrow(() -> new RuntimeException("Produto not found"));
                    return PedidoProdutoEntity.builder()
                            .pedido(this) // Set the reference to the current PedidoEntity
                            .produto(produtoEntity)
                            .valorProduto(pp.getValorProduto())
                            .observacaoProduto(pp.getObservacaoProduto())
                            .build();
                })
                .collect(Collectors.toList());

        return PedidoEntity.builder()
                .idPedido(pedido.getIdPedido())
                .cliente(clienteEntity) // Use the existing ClienteEntity
                .produtos(pedidoProdutoEntities)
                .statusPedido(pedido.getStatusPedido())
                .valorPedido(pedido.getValorPedido())
                .dataInclusao(pedido.getDataInclusao())
                .dataAtualizacao(pedido.getDataAtualizacao())
                .build();
    }

}
