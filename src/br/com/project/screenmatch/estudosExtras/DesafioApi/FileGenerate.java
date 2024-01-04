package br.com.project.screenmatch.estudosExtras.DesafioApi;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileGenerate {
    public void saveJson(Endereco endereco) throws IOException {
        List<Endereco> lista = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting() // Organizar de forma mais visivel o json
                .create();

        FileWriter convertToJson = new FileWriter("Ceps.json");
        lista.add(endereco);
        convertToJson.write(gson.toJson(lista));
        convertToJson.close();
    }
}
