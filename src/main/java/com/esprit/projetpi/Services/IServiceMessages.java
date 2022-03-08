package com.esprit.projetpi.Services;

public interface IServiceMessages {

    public void sendMail(String subject,String body, String to);

    public void sendSMS(String num,String message);

    public boolean profanityDetection(String text);

    public boolean sentimentAnalysis(String text);


}
