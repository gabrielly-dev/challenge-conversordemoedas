import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTP {
    private static final String API_KEY = "f64b7cf4386b614fca11a2db"; // Chave da API
    private Gson gson = new GsonBuilder().create();
    private Exchangerate exchangerate;
    private ApiMoedas apiMoedas;

    public HTTP(String moedaEscolhida) {
        try {
            String endereco = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + moedaEscolhida;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na requisição: " + response.statusCode());
            }

            this.exchangerate = gson.fromJson(response.body(), Exchangerate.class);
            this.apiMoedas = new ApiMoedas(exchangerate);
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao enviar a requisição HTTP: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    public Exchangerate getExchangerate() {
        return exchangerate;
    }

    public ApiMoedas getApiMoedas() {
        return apiMoedas;
    }
}