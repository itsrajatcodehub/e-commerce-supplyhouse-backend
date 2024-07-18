package com.example.demo.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ApiControllers {

    public static void main(String[] args) {
        SpringApplication.run(ApiControllers.class, args);
    }

    @PostMapping(value = "/customerMsg")
    public Response saveCustomerMsg(@RequestBody(required = false) String payload) {
        if (payload == null || payload.isEmpty()) {
            return new Response("400", null, "warn", "Bad Request");
        }
        try {
            saveToDatabase(payload);
            return new Response("200", payload, "success", "Data saved successfully");
        } catch (Exception e) {
            return new Response("400", null, "error", "An error occurred while saving data");
        }
    }

    @PostMapping(value = "/specialHours")
    public Response saveSpecialHours(@RequestBody(required = false) SpecialHours specialHours) {
        if (specialHours == null || specialHours.getOpeningTime() == null || specialHours.getClosingTime() == null) {
            return new Response("400", null, "warn", "Bad Request");
        }
        try {
            saveToDatabase(specialHours);
            return new Response("200", specialHours, "success", "Data saved successfully");
        } catch (Exception e) {
            return new Response("400", null, "error", "An error occurred while saving data");
        }
    }

    private void saveToDatabase(Object data) {
        // Dummy database connection
        String dbIp = "192.168.1.100";
        int dbPort = 5432;

        System.out.println("Connecting to database at IP: " + dbIp + ", Port: " + dbPort);
        System.out.println("Saving data: " + data.toString());
    }
}

class SpecialHours {
    private String openingTime;
    private String closingTime;

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public String toString() {
        return "SpecialHours{openingTime='" + openingTime + "', closingTime='" + closingTime + "'}";
    }
}

class Response {
    private String statuscode;
    private Object data;
    private String status;
    private String message;

    public Response(String statuscode, Object data, String status, String message) {
        this.statuscode = statuscode;
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
