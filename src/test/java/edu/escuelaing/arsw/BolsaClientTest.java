package edu.escuelaing.arsw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BolsaClientTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String MOVIES_ENDPOINT = BASE_URL + "/data";

    public static void main(String[] args) {
        testSimpleRequest();
        testConcurrency();
    }

    private static void testSimpleRequest() {
        try {
            URL url = new URL(MOVIES_ENDPOINT + "?functionn=TIME_SERIES_WEEKLY&symbol=IBM");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode(); 
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine); 
            }
            in.close();

            System.out.println("Response Body : " + response.toString()); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    private static void testConcurrency() {
        ExecutorService executorService = Executors.newFixedThreadPool(10); 

        for (int i = 0; i < 10; i++) {
            
            executorService.execute(() -> {
                try {
                    URL url = new URL(MOVIES_ENDPOINT + "?functionn=TIME_SERIES_WEEKLY&symbol=IBM");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode(); 
                    System.out.println("Thread " + Thread.currentThread().getId() + " - Response Code : " + responseCode);

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine); 
                    }
                    in.close();

                    System.out.println("Thread " + Thread.currentThread().getId() + " - Response Body : " + response.toString());
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
            });
        }

        executorService.shutdown(); 
        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS); 
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }
}
