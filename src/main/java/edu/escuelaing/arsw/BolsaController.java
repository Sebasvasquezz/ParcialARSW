package edu.escuelaing.arsw;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling Bolsa service requests.
 * This controller provides an endpoint to fetch stock market data.
 */
@RestController
@CrossOrigin(origins = "*")
public class BolsaController {
    private BolsaAdvantageService bolsaAdvantageService = new BolsaAdvantageService();

    /**
     * Endpoint to retrieve stock market data based on function and symbol.
     *
     * @param functionn The Alpha Vantage function to query.
     * @param symbol The stock symbol to query.
     * @return The JSON response from the BolsaAdvantageService as a ResponseEntity.
     */
    @GetMapping("/data")
    public ResponseEntity<String> getData(@RequestParam String functionn, @RequestParam String symbol) {
     
        String dataJson = bolsaAdvantageService.getData(functionn, symbol);
        return ResponseEntity.ok(dataJson); 
    }
}
