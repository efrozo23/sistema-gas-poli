package edu.poli.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import edu.poli.beans.ReciveData;



@Component
public class RestDslMain extends RouteBuilder {
	
	
	
	@Autowired
	private Environment env;
	
	@Value("${server.servlet-path}")
    private String contextPath;

	@Override
	public void configure() throws Exception {
		 restConfiguration()
         .component("servlet")
         .bindingMode(RestBindingMode.json)
         .dataFormatProperty("prettyPrint", "true")
         .enableCORS(true)
         .port(env.getProperty("server.port", "8080"))
         .contextPath(contextPath.substring(0, contextPath.length() - 2))
         // turn on swagger api-doc
         .apiContextPath("/api-doc")
         .apiProperty("api.title",  env.getProperty("api.title"))
         .apiProperty("api.version", env.getProperty("api.version"));
		 
		 rest().description(env.getProperty("api.description"))
         .consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
         .produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
         
         .post("/saveData").type(edu.poli.dto.Request.class).outType(edu.poli.dto.Response.class) 
             .responseMessage().code(200).message("All users successfully created").endResponseMessage().to("direct:save-data-route");
		 
		 from("direct:save-data-route").id("RutaGuardaInfo").streamCaching("true")
		 	.log(LoggingLevel.INFO, "Ingreso a consumir : ${body}")
		 	.bean(ReciveData.class)
		 	.setBody(simple("{'MESSAGE':'OK'}"))
		 .end();
		
	}
	
	

}
