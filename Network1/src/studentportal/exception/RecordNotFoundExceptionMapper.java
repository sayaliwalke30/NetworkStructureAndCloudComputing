package studentportal.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.http.HttpStatus;

@Provider
public class RecordNotFoundExceptionMapper implements ExceptionMapper<RecordNotFoundException> {

	@Override
	public Response toResponse(RecordNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse(404, exception.getErrorMessage());
		return Response.status(Status.NOT_FOUND).entity(errorResponse).build();
	}
}
