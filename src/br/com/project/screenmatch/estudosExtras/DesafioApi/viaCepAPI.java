package br.com.project.screenmatch.estudosExtras.DesafioApi;
import java.io.IOException;
import java.util.Scanner;

public class viaCepAPI extends consulta{
    public static void main(String[] args)  {
        Scanner leia = new Scanner(System.in);
        consulta consultaCep = new consulta();
        String cep = "";

        while(!cep.equalsIgnoreCase("sair")) {
            System.out.println("Digit o cep que desecrate: ");
            cep = leia.nextLine();
            if (cep.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                Endereco novoEndereco = consultaCep.buscaEndereco(cep);
                FileGenerate generate = new FileGenerate();
                System.out.println(novoEndereco);
                generate.saveJson(novoEndereco);
            } catch (RuntimeException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Application Finalization ");
            }
        }
    }
    }
