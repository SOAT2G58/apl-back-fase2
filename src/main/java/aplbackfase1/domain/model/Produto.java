package aplbackfase1.domain.model;

import aplbackfase1.domain.model.valueObject.DescricaoProduto;
import aplbackfase1.domain.model.valueObject.NomeProduto;

import java.util.Date;
import java.util.UUID;

public class Produto {

    private UUID idProduto;
    private NomeProduto nomeProduto;
    private DescricaoProduto descricaoProduto;
    private boolean produtoDisponivel;
    private TipoProduto tipoProduto;
    private Date dataCriacao;
    private Date dataAtualizacao;

    Produto() {}

    public Produto(UUID idProduto, NomeProduto nomeProduto, DescricaoProduto descricaoProduto, TipoProduto tipoProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.tipoProduto = tipoProduto;
        this.dataCriacao = new Date();
        this.produtoDisponivel = true;
    }

    public Produto getProduto() {
        return this;
    }

    public UUID getIdProduto() {
        return this.idProduto;
    }

    public NomeProduto getNomeProduto() {
        return this.nomeProduto;
    }

    public void setNomeProduto(NomeProduto nomeProduto) {
        this.nomeProduto = nomeProduto;
        this.dataAtualizacao = new Date();
    }

    public DescricaoProduto getDescricaoProduto() {
        return this.descricaoProduto;
    }

    public void setDescricaoProduto(DescricaoProduto descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
        this.dataAtualizacao = new Date();
    }

    public boolean isProdutoDisponivel() {
        return this.produtoDisponivel;
    }

    public void setProdutoDisponivel(boolean produtoDisponivel) {
        this.produtoDisponivel = produtoDisponivel;
        this.dataAtualizacao = new Date();
    }

    public TipoProduto getTipoProduto() {
        return this.tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
        this.dataAtualizacao = new Date();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto valor = (Produto) obj;
        if (idProduto == null) {
            return valor.idProduto == null;
        } else return idProduto.equals(valor.idProduto);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
        return result;
    }
}
