package edu.wgu.d387_sample_code.model;

import edu.wgu.d387_sample_code.D387SampleCodeApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;


public class Greeting {
    static ExecutorService messageExecutor = Executors.newFixedThreadPool(4);

    public static List<String> getGreetings() throws InterruptedException {
        Properties properties = new Properties();
        List<String> messages = new ArrayList<>();

        Callable<String> englishMessage = () -> {
            try{
                InputStream stream = new ClassPathResource("welcome_message_en_US.properties").getInputStream();
                properties.load(stream);
                messages.add(properties.getProperty("message"));
                return properties.getProperty("message");
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }};

        Callable<String> frenchMessage = () -> {
            try {
                InputStream stream = new ClassPathResource("welcome_message_fr_FR.properties").getInputStream();
                properties.load(stream);
                messages.add(properties.getProperty("message"));
                return properties.getProperty("message");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }};

        Future<String> futureEnglish = messageExecutor.submit(englishMessage);
        Future<String> futureFrench = messageExecutor.submit(frenchMessage);

        try {
            futureEnglish.get();
            futureFrench.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(messages);
        return messages;
    }

}
