import java.util.Map;

public class Exchangerate {
    private String base_code;
    private Map<String, Double> conversion_rates; // Mapeia as moedas para suas taxas de câmbio

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }

    public double getTaxaDeCambio(String moedaDestino) {
        if (conversion_rates.containsKey(moedaDestino)) {
            return conversion_rates.get(moedaDestino);
        } else {
            throw new IllegalArgumentException("Moeda de destino não encontrada: " + moedaDestino);
        }
    }
}