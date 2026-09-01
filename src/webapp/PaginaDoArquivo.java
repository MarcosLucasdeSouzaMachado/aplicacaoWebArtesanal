package webapp;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PaginaDoArquivo extends Pagina {
    private String nomeArquivo;

    public PaginaDoArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public String getHtml(){
        try {
            return Files.readString(Paths.get("src/webapp/" + nomeArquivo));
        } catch (Exception e) {
            return "<h1>Erro ao carregar página</h1>";
        }
    }

    public String getNomeDoArquivo() {
        return nomeArquivo;
    }
}