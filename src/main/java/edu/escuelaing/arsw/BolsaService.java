package edu.escuelaing.arsw;

/**
 * Interface for the Bolsa service.
 * This interface defines the contract for fetching stock market data.
 */
public interface BolsaService {
    /**
     * Fetches stock market data based on the specified function and symbol.
     *
     * @param functionn 
     * @param symbol 
     * @return 
     */
    public String getData(String functionn, String symbol);
    
}

