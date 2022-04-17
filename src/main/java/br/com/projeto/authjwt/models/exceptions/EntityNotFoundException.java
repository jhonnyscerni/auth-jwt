package br.com.projeto.authjwt.models.exceptions;

public class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Long id) {
        this(String.format(message + " %d", id));
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
