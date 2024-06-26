package com.cydeo.controller;

import com.cydeo.dto.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/restTemplate")
public class Consume_RestTemplate {

    private final String URI = "https://jsonplaceholder.typicode.com/users";
    private final RestTemplate restTemplate;
    public Consume_RestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<User[]> readAllUsers(){
        return restTemplate.getForEntity(URI, User[].class); // uri yi ver, ona karşılık gelen veriyi vereyim!
    }                                                        // görmek istemediğimiz field i UserDto da belirtebiliriz.

    /*
    * restTemplate.getForEntity() - if we have dto when we consume the API that output is going to
    * mapped to our dto, we are going see as output whatever our dto has.

    * restTemplate.getForObject() - we do not have dto, method just taking from the third party API and
    * whatever field that API has we see as output.

    * restTemplate.exchange() - we use this method whenever we want to pass headers
     */

    @GetMapping("{id}")
    public Object readUser(@PathVariable("id") Integer id){ // yukarıdaki ile farkı; UserDto da hangi field ler
        String URL = URI + "/{id}";                         // varsa hepsi getirilir istisnasız!
        return restTemplate.getForObject(URL, Object.class, id);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> consumePostFromDummyApi(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id", "6298ebfecd0551211fce37a6");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10", HttpMethod.GET, entity, Object.class);

    }


}
