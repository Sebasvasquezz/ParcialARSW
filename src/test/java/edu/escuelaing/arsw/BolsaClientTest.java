package edu.escuelaing.arsw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Client test class for the Bolsa service.
 * This class provides methods to test the service with simple requests and concurrent requests.
 */
public class BolsaClientTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String DATA_ENDPOINT = BASE_URL + "/data";

     /**
     * The main method to execute the test methods.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        testSimpleRequest();
        testConcurrency();
    }

    /**
     * Method to test a simple request to the Bolsa service.
     * This method makes a GET request to fetch data for a specific function and symbol.
     */
    private static void testSimpleRequest() {
        try {
            URL url = new URL(DATA_ENDPOINT + "?functionn=TIME_SERIES_WEEKLY&symbol=IBM");
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

    /**
     * Method to test concurrent requests to the Bolsa service.
     * This method creates multiple threads to make simultaneous GET requests.
     */
    private static void testConcurrency() {
        ExecutorService executorService = Executors.newFixedThreadPool(10); 

        for (int i = 0; i < 10; i++) {
            
            executorService.execute(() -> {
                try {
                    URL url = new URL(DATA_ENDPOINT + "?functionn=TIME_SERIES_WEEKLY&symbol=IBM");
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
