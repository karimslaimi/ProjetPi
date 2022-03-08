package com.esprit.projetpi.Services;

import com.cloudmersive.client.AnalyticsApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.ProfanityAnalysisRequest;
import com.cloudmersive.client.model.ProfanityAnalysisResponse;
import com.cloudmersive.client.model.SentimentAnalysisRequest;
import com.cloudmersive.client.model.SentimentAnalysisResponse;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServiceMessages implements IServiceMessages{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String subject, String body, String to) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(body);

        javaMailSender.send(msg);
    }

    @Override
    public void sendSMS(String num, String mess) {
        VonageClient client = VonageClient.builder().apiKey("e5668206").apiSecret("P4VhyGeOyadg8AVr").build();
        TextMessage message = new TextMessage("Esprit",
                "216"+num,
                mess
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");

        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());

        }
    }



    @Override
    public boolean profanityDetection(String text) {

        //if the score is over 0.6 that means the language is profane that means it s bad words and shouldn't be accepted

        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("128ffe67-a3e7-4ec4-b723-980fa639eaeb");


        AnalyticsApi apiInstance = new AnalyticsApi();
        ProfanityAnalysisRequest input = new ProfanityAnalysisRequest(); // ProfanityAnalysisRequest | Input profanity analysis request
        try {
            input.setTextToAnalyze(text);
            ProfanityAnalysisResponse result = apiInstance.analyticsProfanity(input);

            return result.getProfanityScoreResult()>0.6;

        } catch (ApiException e) {
            System.err.println("Exception when calling AnalyticsApi#analyticsProfanity");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sentimentAnalysis(String text) {

        //if the score is under 0 that means this is a negative sentiment and shouldn't be accepted
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("128ffe67-a3e7-4ec4-b723-980fa639eaeb");


        AnalyticsApi apiInstance = new AnalyticsApi();
        SentimentAnalysisRequest input = new SentimentAnalysisRequest();
        try {
            input.setTextToAnalyze(text);
            SentimentAnalysisResponse result = apiInstance.analyticsSentiment(input);

            return result.getSentimentScoreResult()>=0;

        } catch (ApiException e) {
            System.err.println("Exception when calling AnalyticsApi#analyticsProfanity");
            e.printStackTrace();
        }
        return false;
    }


}
