/**
 * 
 */
package com.wf.baas.pay.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author u180362
 *
 */
@Provider
public class BaasApplicationExceptionMapper implements
		ExceptionMapper<BaaSApplicationException> {

	public Response toResponse(BaaSApplicationException ex) {
		return Response.status(ex.getStatus()).entity(ex.getMessage())
				.type(MediaType.APPLICATION_JSON).build();
	}

}
