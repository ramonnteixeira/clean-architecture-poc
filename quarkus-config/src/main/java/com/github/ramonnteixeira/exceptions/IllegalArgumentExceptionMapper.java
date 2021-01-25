package com.github.ramonnteixeira.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException e) {
        ExceptionMessageDto message = new ExceptionMessageDto(e.getMessage());
        return Response.status(400).entity(message).build();
    }
}
