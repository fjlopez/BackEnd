package bluebomb.urlshortener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not found")
public class NotFoundError extends RuntimeException {
    private String message;
    private static final long serialVersionUID = 1L;
}
