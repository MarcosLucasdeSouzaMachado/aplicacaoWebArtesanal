package webapp;

public class Requisicao {
    private String documento;
    private String metodo;
    private String url;
    private String corpo;

    public Requisicao(String documento) {
        this.documento = documento;
        String[] partes = documento.split("\n\n", 2);  // Separa cabeçalho do corpo

        String cabecalho = partes[0];
        String[] linhas = cabecalho.split("\n");
        String primeiraLinha = linhas[0];
        String[] componentesPrimeiraLinha = primeiraLinha.split(" ");

        metodo = componentesPrimeiraLinha[0];
        url = componentesPrimeiraLinha[1];

        // Se houver corpo, armazena
        if (partes.length > 1) {
            corpo = partes[1].trim();
        } else {
            corpo = "";
        }
    }

    public String getMetodo() {
        return metodo;
    }

    public String getURL() {
        return url;
    }

    public String getCorpo() {
        return corpo;
    }
}