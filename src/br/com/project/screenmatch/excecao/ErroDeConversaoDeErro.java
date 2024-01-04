package br.com.project.screenmatch.excecao;

public class ErroDeConversaoDeErro extends RuntimeException {
    private String message;
    public ErroDeConversaoDeErro(String message) {this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
