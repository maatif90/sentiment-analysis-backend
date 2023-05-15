package com.priceline.sentimentanalysis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {

    @CrossOrigin
    @PostMapping("/processQuery")
    public String sendResponse(@RequestBody ReviewBody reviewBody) {

        String responseBody = null;
        String apiKey = "ENTER CHAT GPT ACCOUNT API KEY";
        String questionAppend="give me a sentiment and appropriate human like response in follow json format. if sentiment is negative please add this toll free number n response 140073938 and ask customer for trip number. \n" +
                "{\n" +
                "isnegative : boolean,\n" +
                "response : text\n" +
                "}";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String endpointUrl = "https://api.openai.com/v1/chat/completions";


            RoleDetails roleDetails = new RoleDetails();
            roleDetails .setContent(reviewBody.getReview()+questionAppend);
            List<RoleDetails> rols= new ArrayList<>();
            rols.add(roleDetails);
            ChatGptPayload chatGptPayload =  new ChatGptPayload();
            chatGptPayload .setMessages(rols);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(chatGptPayload);

            // Create the HTTP POST request
            HttpPost request = new HttpPost(endpointUrl);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", "Bearer " + apiKey);
            request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

            // Execute the request and get the response
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            // Extract and print the response body
             responseBody = EntityUtils.toString(responseEntity);
            System.out.println(responseBody);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return responseBody;
    }
}

@Data
class ReviewBody {
    String review;
    String username;

}
@Data
class ChatGptPayload{
    String model="gpt-3.5-turbo";
    List<RoleDetails> messages;
}

@Data
class RoleDetails {
    String role ="user";
    String content;
}
