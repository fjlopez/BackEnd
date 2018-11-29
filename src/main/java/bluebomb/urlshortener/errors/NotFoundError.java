package bluebomb.urlshortener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundError extends RuntimeException {
    public NotFoundError(String s) {
        super(s);
    }
}
