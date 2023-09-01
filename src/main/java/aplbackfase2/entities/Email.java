package aplbackfase2.entities;

import aplbackfase2.exceptions.entities.EmailInvalidoException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Email {
    public static final Pattern REGEX_EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String email;

    Email() {}

    public Email(String email) {
        this.email = Objects.nonNull(email) ? email : "";
        Matcher matcher = REGEX_EMAIL_VALIDO.matcher(email);
        if (!matcher.find()) {
            throw new EmailInvalidoException();
        }
    }

    public String getEmail() {
        return this.email;
    };

    public boolean isInvalid() {
        return !isValid();
    }

    public boolean isValid() {
        Matcher matcher = REGEX_EMAIL_VALIDO.matcher(email);
        return matcher.find();
    }

    @Override
    public String toString() {
        return getEmail();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }
}
