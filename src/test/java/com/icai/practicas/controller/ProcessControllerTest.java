package com.icai.practicas.controller;

import com.icai.practicas.controller.ProcessController.DataRequest;
import com.icai.practicas.controller.ProcessController.DataResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void processDataTestOK() {

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1";

        //Request
        DataRequest example = new DataRequest("Alvaro Olivie", "51500336R", "670986614");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(example, headers);

        //When
        ResponseEntity<DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);

        //Then
        String expectedResult = "OK";
        DataResponse expectedResponse = new DataResponse(expectedResult);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().result()).isEqualTo(expectedResult);
        then(result.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void processDataTestKO() {

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1";

        //Request
        DataRequest example = new DataRequest("Alvaro Olivie", "515003364", "65484sad8");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(example, headers);

        //When
        ResponseEntity<DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);


        //Then
        String expectedResult = "KO";
        DataResponse expectedResponse = new DataResponse(expectedResult);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().result()).isEqualTo(expectedResult);
        then(result.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void processDataTestOKLegacy() {
        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        //Request
        MultiValueMap<String, String> example = new LinkedMultiValueMap<String, String>();
        example.add("fullName", "Alvaro Olivie");
        example.add("dni", "51500336R");
        example.add("telefono", "670986614");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(example, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = ResponseHTMLGenerator.message1;

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void processDataTestKOLegacy() {
        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        //Request
        MultiValueMap<String, String> example = new LinkedMultiValueMap<String, String>();
        example.add("fullName", "Alvaro Olivie");
        example.add("dni", "5150F336R");
        example.add("telefono", "-34670986614");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(example, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = ResponseHTMLGenerator.message2;

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(expectedResult);
    }
}