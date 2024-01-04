package br.com.alura.screenmatch.estudosExtras;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class leituraDeArquivos {
    public static void main(String[] args) {

                try {
                    System.out.println("Imprimindo conteúdo do arquivo .txt");
                    File arquivo = new File("C:\\Users\\gusta\\Desktop\\passo-a-passo-github.txt");
                    Scanner scanner = new Scanner(arquivo);

                    while (scanner.hasNextLine()) {
                        String linha = scanner.nextLine();
                        System.out.println(linha);
                    }

                    scanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Arquivo não encontrado!");
                }
            }
        }


