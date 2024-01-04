package br.com.project.screenmatch.estudosExtras.DesafioApi;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class consulta {
    public Endereco buscaEndereco(String cep)  {
        URI endereco = URI.create("https://viacep.com.br/ws/" + cep+ "/json/");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();
        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Endereco.class);
        }catch (IOException | InterruptedException e){
           throw new RuntimeException("Não consegui localizar esse endereço via esse CEP");
        }

    }
}
