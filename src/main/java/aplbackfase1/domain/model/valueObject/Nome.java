package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.NomeInvalidoException;

import java.util.Objects;

public class Nome implements Comparable<Nome> {
    private String valor;

    private int tamanhoMinimo;
    private int tamanhoMaximo;
    private int minimoCaracter;
    private int maximoCaracter;

    Nome() {}

    public Nome(String valor) {
        this(valor, 5, 2);
    }

    public Nome(String valor, int tamanhoMinimo, int minimoCaracter) {
        this(valor, tamanhoMinimo, minimoCaracter, 255, 6);
    }

    public Nome (String valor, int tamanhoMinimo, int tamanhoMaximo, int minimoCaracter, int maximoCaracter) {
        this.tamanhoMinimo = tamanhoMinimo;
        this.minimoCaracter = minimoCaracter;
        this.tamanhoMaximo = tamanhoMaximo;
        this.maximoCaracter = maximoCaracter;
        if (Objects.nonNull(valor)) {
            this.valor = valor.trim();
        }
        if (isInvalid()) {
            throw new NomeInvalidoException();
        }
    }

    public String getValor() {
        return this.valor;
    }

    public boolean isValid() {
        return !isInvalid();
    }

    public boolean isInvalid() {
        if (Objects.isNull(valor)) {
            return true;
        }
        boolean possuiTamanhoMinimo = getValor().length() >= tamanhoMinimo;
        boolean possuiTamanhoMaximo = getValor().length() >= tamanhoMaximo;
        boolean possuiMinimoCaracter = getValor().length() >= minimoCaracter;
        boolean possuiMaximoCaracter = getValor().length() >= maximoCaracter;

        return !possuiTamanhoMinimo || !possuiTamanhoMaximo || !possuiMinimoCaracter || !possuiMaximoCaracter;
    }

    @Override
    public int compareTo(Nome o) {
        return getValor().compareTo(o.getValor());
    }

    @Override
    public String toString() {
        return getValor();
    }
}
