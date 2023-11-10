package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeErro;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String apikey = "ab2be20e";
        String busca = "";
        List<Titulo> listaDeTitulos = new ArrayList<>();
        // Java library that can be used to convert Java Objects into their JSON representation.
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting() // Organiza de forma mais visivel o json
                .create();
        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme para busca: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=" + apikey;
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);


                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(meuTituloOmdb);

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo convertido");
                System.out.println(meuTitulo);
                // Classe usada para criar arquivos
                FileWriter escrita = new FileWriter("filmes&Series.txt");
                // metodo para escrever dentro do nosso arquivo .txt
                escrita.write(meuTitulo.toString());
                // metodo para sinalizar o fim da operação do write
                escrita.close();
                listaDeTitulos.add(meuTitulo);
            } catch (ErroDeConversaoDeErro e) {
                System.out.println("ERRO!!\n\t Apresentando problema abaixo:");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO!! \n\t Apresentando problema abaixo:");
                System.out.println(e.getMessage());
                System.out.println("Erro de argumentação");
            } finally {
                System.out.println("finalizado");
            }
        }
        System.out.println("Lista de filmes e Séries \n" + listaDeTitulos);
        FileWriter convertePraJson = new FileWriter("titulos&Series.txt");
        convertePraJson.write(gson.toJson(listaDeTitulos));
        convertePraJson.close();
    }
}
