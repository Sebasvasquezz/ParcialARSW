package edu.escuelaing.arsw;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Service class that implements BolsaService to interact with the Alpha Vantage API.
 * This service provides methods to fetch stock market data and caches the responses.
 */
@Service
public class BolsaAdvantageService implements BolsaService {

    public BolsaAdvantageService() {
    }

    private final String apiKey = "demo";
    private final String alphaUrl = "https://www.alphavantage.co/query?function=";

    private final Map<String, String> cache = new ConcurrentHashMap<>();


    /**
     * Fetches data from the Alpha Vantage API based on the specified function and symbol.
     * Caches the response to minimize API calls.
     *
     * @param functionn The Alpha Vantage function to query.
     * @param symbol The stock symbol to query.
     * @return The JSON response from the API as a String.
     */
    public String getData(String functionn, String symbol) {
        String cacheKey = functionn + "_" + symbol;

        if (cache.containsKey(cacheKey)) {
            System.out.println("Cache funciona");
            return cache.get(cacheKey);
        }

        String url;
        
        RestTemplate restTemplate = new RestTemplate();
        if (functionn == "TIME_SERIES_INTRADAY") {
            url = alphaUrl + functionn + "&symbol=" + symbol + "&interval=5min" + "&apikey=" + apiKey;
        } else{
            url = alphaUrl + functionn + "&symbol=" + symbol + "&apikey=" + apiKey;
        }
        
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                String jsonResponse = objectMapper.writeValueAsString(rootNode);
                cache.put(cacheKey, jsonResponse);

                return jsonResponse;
            } catch (Exception e) {
                e.printStackTrace();
                return "{}";
            }
        }
        return "{}";
    }
}
