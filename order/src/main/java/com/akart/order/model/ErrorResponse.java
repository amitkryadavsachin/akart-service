package com.akart.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorResponse {

    private String message;
    @JsonProperty("agency_signature")
    private Map<String, String> agencySignature;

    public ErrorResponse() {
        Map<String, String> agencySignature = new HashMap<>();
        agencySignature.put("agency_name", "akart order service Pvt.Ltd.");
        agencySignature.put("agency_address",
                "Patna Bihar");
        agencySignature.put("agency_contact", "+917294960430");
        agencySignature.put("agency_email", "amitkryadavjee@gmail.com");
        agencySignature.put("agency_website", "https://www.linkedin.com/in/amit-kumar-yadav-b16b35197");
        this.agencySignature = agencySignature;
    }
}
