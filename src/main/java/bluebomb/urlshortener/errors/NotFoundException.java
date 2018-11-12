package bluebomb.urlshortener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="To show an example of a custom message")
public class NotFoundException extends RuntimeException { }
