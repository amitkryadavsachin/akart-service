package com.akart.user.helper;
import com.akart.user.model.OrderDto;
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
    private static final String ORDER = "order-service";


    private void setHttpHeaders() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    private String getServiceUrl(String appName) {

		switch (appName) {
			case ORDER:
				return "http://localhost:8082";
			case PRODUCT:
				return "http://localhost:8083";
			default:
				break;
		}
        log.error("No service url found for app {}", appName);
        throw new RestClientException("No service url found for app " + appName);
    }

    public Object getOderById(Long orderId) {
        setHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(getServiceUrl(ORDER)+ "/order")
                .pathSegment(orderId.toString())
                .toUriString();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
        log.info("Fetched the Order Successfully ");
        return response.getBody();
    }

    public Object getAllOrderByUserId(Long userId) {
        setHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(getServiceUrl(ORDER)+ "/order/by-user").queryParam("userId", userId).toUriString();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
        log.info("Fetched all Order by userId: {} Successfully ",userId);
        return response.getBody();
    }

    public Object deleteOderById(Long orderId) {
        setHttpHeaders();
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(getServiceUrl(ORDER)).path("/order/delete/{id}").buildAndExpand(orderId).toUriString();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Object.class);
        log.info("Deleted the Order Successfully with ID: {}", orderId);
        return response.getBody();
    }

    public Object createOrder(OrderDto orderDto) {
        setHttpHeaders();
        HttpEntity<OrderDto> requestBody = new HttpEntity<>(orderDto, httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(getServiceUrl(ORDER)+ "/order/create", HttpMethod.POST, requestBody, Object.class);
        log.info("Created the Order Successfully ");
        return response.getBody();
    }
    public Object updateOrder(OrderDto orderDto) {
        setHttpHeaders();
        HttpEntity<OrderDto> requestBody = new HttpEntity<>(orderDto, httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(getServiceUrl(ORDER)+ "/order/update", HttpMethod.POST, requestBody, Object.class);
        log.info("Updated the Order Successfully ");
        return response.getBody();
    }

    public Object getProductList() {
        setHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = UriComponentsBuilder.fromHttpUrl(getServiceUrl(PRODUCT)+ "/product").toUriString();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
        log.info("Fetched Product List Successfully ");
        return response.getBody();
    }
}
