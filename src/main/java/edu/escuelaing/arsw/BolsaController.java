package edu.escuelaing.arsw;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BolsaController {
    private BolsaAdvantageService bolsaAdvantageService = new BolsaAdvantageService();

    @GetMapping("/data")
    public ResponseEntity<String> getData(@RequestParam String functionn, @RequestParam String symbol) {
     
        String dataJson = bolsaAdvantageService.getData(functionn, symbol);
        return ResponseEntity.ok(dataJson); 
    }
}
