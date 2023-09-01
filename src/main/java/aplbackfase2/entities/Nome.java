package aplbackfase2.entities;

import aplbackfase2.exceptions.entities.NomeInvalidoException;

import java.util.Objects;

public final class Nome implements Comparable<Nome> {
    private String nome;
    private static final int tamanhoMinimo = 5;
    private static final int tamanhoMaximo = 255;
    private static final int minimoCaracter = 2;
    private static final int maximoCaracter = 6;

    Nome() {}

    public Nome (String valor) {
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
