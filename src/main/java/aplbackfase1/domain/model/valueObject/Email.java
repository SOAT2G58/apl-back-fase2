package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.EmailInvalidoException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    public static final Pattern REGEX_EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String valor;

    Email() {}

    public Email(String email) {
        this.valor = Objects.nonNull(email) ? email : "";
        Matcher matcher = REGEX_EMAIL_VALIDO.matcher(email);
        if (!matcher.find()) {
            throw new EmailInvalidoException();
        }
    }

    public String getValor() {
        return this.valor;
    };

    public boolean isInvalid() {
        return !isValid();
    }

    public boolean isValid() {
        Matcher matcher = REGEX_EMAIL_VALIDO.matcher(valor);
        return matcher.find();
    }

    @Override
    public String toString() {
        return getValor();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Email other = (Email) obj;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }
}
