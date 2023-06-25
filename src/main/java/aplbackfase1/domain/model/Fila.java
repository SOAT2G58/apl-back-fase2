package aplbackfase1.domain.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Fila {

    private UUID idFila;
    private UUID idPedido;
    private int numeroNaFila;
    private Date dataInclusaoFila;

    public Fila(UUID idFila, UUID idPedido, int numeroNaFila, Date dataInclusaoFila) {
        this.idFila = idFila;
        this.idPedido = idPedido;
        this.numeroNaFila = numeroNaFila;
        this.dataInclusaoFila = dataInclusaoFila;
    }

    public UUID getIdFila() {
        return idFila;
    }

    public void setIdFila(UUID idFila) {
        this.idFila = idFila;
    }

    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    public int getNumeroNaFila() {
        return numeroNaFila;
    }

    public void setNumeroNaFila(int numeroNaFila) {
        this.numeroNaFila = numeroNaFila;
    }

    public Date getDataInclusaoFila() {
        return dataInclusaoFila;
    }

    public void setDataInclusaoFila(Date dataInclusaoFila) {
        this.dataInclusaoFila = dataInclusaoFila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fila fila = (Fila) o;
        return numeroNaFila == fila.numeroNaFila &&
                Objects.equals(idFila, fila.idFila) &&
                Objects.equals(idPedido, fila.idPedido) &&
                Objects.equals(dataInclusaoFila, fila.dataInclusaoFila);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFila, idPedido, numeroNaFila, dataInclusaoFila);
    }
}
