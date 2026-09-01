package webapp;
import java.util.HashMap;
import java.util.Map;
import java.net.URLDecoder;

public class Receptor extends Pagina {
    private Requisicao requisicao;
    private Map<String, String> parametros;

    public Receptor(Requisicao requisicao) {
        this.requisicao = requisicao;
        this.parametros = getParametrosQuery();
    }
    public Map<String, String> getParametrosQuery() {
        Map<String, String> params = new HashMap<>();
        String url = requisicao.getURL();
        String corpo = requisicao.getCorpo();
        String queryString = null;

        if ("GET".equals(requisicao.getMetodo()) && url.contains("?")) {
            queryString = url.substring(url.indexOf("?") + 1);
        } else if ("POST".equals(requisicao.getMetodo())) {
            queryString = corpo;
        }

        if (queryString != null && !queryString.isEmpty()) {
            String[] pares = queryString.split("&");
            for (String par : pares) {
                String[] chaveValor = par.split("=");
                if (chaveValor.length == 2) {
                    try {
                        String chave = URLDecoder.decode(chaveValor[0], "UTF-8");
                        String valor = URLDecoder.decode(chaveValor[1], "UTF-8");
                        params.put(chave, valor);
                    } catch (Exception e) {
                        params.put(chaveValor[0], chaveValor[1]);
                    }
                }
            }
        }
        return params;
    }
    @Override
    public String getHtml() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"br\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>Resultado da Busca</title>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>Resultado da Busca</h1>\n");

        if (parametros.isEmpty()) {
            html.append("    <p>Nenhum parâmetro foi enviado.</p>\n");
        } else {
            html.append("    <ul>\n");
            for (String chave : parametros.keySet()) {
                html.append("        <li>").append(chave).append(": ")
                        .append(parametros.get(chave)).append("</li>\n");
            }
            html.append("    </ul>\n");
        }

        html.append("    <a href=\"/\">Voltar</a>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        return html.toString();
    }
}