package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.NomeInvalidoException;

import javax.persistence.Transient;
import java.util.Objects;

public class Nome implements Comparable<Nome> {
    private String nome;
    @Transient
    private int tamanhoMinimo;
    @Transient
    private int tamanhoMaximo;
    @Transient
    private int minimoCaracter;
    @Transient
    private int maximoCaracter;

    Nome() {}

    public Nome(String valor) {
        this(valor, 5, 2);
    }

    public Nome(String valor, int tamanhoMinimo, int minimoCaracter) {
        this(valor, tamanhoMinimo, 255, minimoCaracter, 6);
    }

    public Nome (String valor, int tamanhoMinimo, int tamanhoMaximo, int minimoCaracter, int maximoCaracter) {
        this.tamanhoMinimo = tamanhoMinimo;
        this.minimoCaracter = minimoCaracter;
        this.tamanhoMaximo = tamanhoMaximo;
        this.maximoCaracter = maximoCaracter;
        if (Objects.nonNull(valor)) {
            this.nome = valor.trim();
        }
        if (isInvalid()) {
            throw new NomeInvalidoException();
        }
    }

    public String getNome() {
        return this.nome;
    }

    public boolean isValid() {
        return !isInvalid();
    }

    public boolean isInvalid() {
        if (Objects.isNull(nome)) {
            return true;
        }
        boolean possuiTamanhoMinimo = getNome().length() >= tamanhoMinimo;
        boolean possuiTamanhoMaximo = getNome().length() <= tamanhoMaximo;
        boolean possuiMinimoCaracter = getNome().split(" ").length >= minimoCaracter;
        boolean possuiMaximoCaracter = getNome().split(" ").length <= maximoCaracter;

        return !possuiTamanhoMinimo || !possuiTamanhoMaximo || !possuiMinimoCaracter || !possuiMaximoCaracter;
    }

    @Override
    public int compareTo(Nome o) {
        return getNome().compareTo(o.getNome());
    }

    @Override
    public String toString() {
        return getNome();
    }
}
