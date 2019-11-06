package edu.poli.beans;

import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import edu.poli.dto.Response;

import io.swagger.annotations.ApiModelProperty;

@Component
public class ResponseHandler {

    @Handler
    @ApiModelProperty(notes = "Parametro De Salida")
    public Response handler() {
	Response dto = new Response();
	dto.setParam("Hello");
	return dto;
    }
}
