package bluebomb.urlshortener.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The shortened url you're trying to access does not exist")
public class SequenceNotFoundError extends RuntimeException {}
