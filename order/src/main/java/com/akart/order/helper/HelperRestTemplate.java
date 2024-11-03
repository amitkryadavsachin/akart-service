package com.akart.order.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class HelperRestTemplate {

    private HttpHeaders httpHeaders;

    private RestTemplate restTemplate = new RestTemplate();

    private HttpEntity httpEntity;

    private static final String PRODUCT = "product-service";
    private static final String USER = "user-service";


    private void setHttpHeaders() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    private String getServiceUrl(String appName) {

        switch (appName) {
            case USER:
                return "http://localhost:8081";
            case PRODUCT:
                return "http://localhost:8083";
            default:
                break;
        }
        log.error("No service url found for app {}", appName);
        throw new RestClientException("No service url found for app " + appName);
    }

    public Object getProductById(String id) {
        setHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(getServiceUrl(PRODUCT)+ "/product").pathSegment(id).toUriString();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
        log.info("Fetched Product List Successfully ");
        return response.getBody();
    }

    public Object getUserById(Long id) {
        setHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(getServiceUrl(USER)).path("/user/{id}").buildAndExpand(id).toUriString();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
        log.info("Fetched Product List Successfully for id:{} ",id);
        return response.getBody();
    }
}
