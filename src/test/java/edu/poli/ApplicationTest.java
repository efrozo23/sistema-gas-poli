package edu.poli;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.poli.dto.Request;

/**
 * 
 * @author Assert Solutions S.A.S
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = { "server.port=8081" })
public class ApplicationTest {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private TestRestTemplate restTemplate;

   

    @Test
    public void testPost() throws Exception {

        // Call the REST API
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    	HttpEntity<String> request = new HttpEntity<String>("{\"param\": 44}",httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/persis", HttpMethod.POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}