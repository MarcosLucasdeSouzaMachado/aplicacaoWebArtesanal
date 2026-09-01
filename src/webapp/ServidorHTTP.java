package webapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHTTP {
    protected Requisicao lerRequisicao(InputStream in) throws IOException {
        BufferedReader leitorLinhas = new BufferedReader(
                new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = leitorLinhas.readLine()) != null && !linha.isBlank()) {
            sb.append(linha).append("\r\n");
        }
        String documento = sb.toString();
        return new Requisicao(sb.toString());
    }
    protected Pagina getPagina(Requisicao req){
        if ("/".equals(req.getURL())){
            return new Pagina();
        } else {
            return null;
        }
    }
    protected Resposta criarResposta(Pagina pagina){
        Resposta res = new Resposta();
        if (pagina == null) {
            res.setStatus(404, "Not Found");
            return res;
        }
        res.setStatus(200, "OK");
        res.setBody(pagina.getHtml());
        return res;
    }
    protected void enviarResposta(Resposta res, OutputStream out) throws IOException{
        PrintWriter writer = new PrintWriter(out, true);
        writer.printf(res.getDocumentoBruto());
    }
    public void iniciar(int porta){
        try (ServerSocket serverSocket = new ServerSocket(porta)){
            System.out.println("Servidor ouvindo na porta: "+porta);
            System.out.printf("Acesse pelo endereço: %s:%d\n", "http://localhost", porta);
            System.out.println("Para parar o servidor aperte Ctrl + C.");

            while (true) {
                try(Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream()){
                    Requisicao req = lerRequisicao(in);
                    Pagina pag = getPagina(req);
                    Resposta res = criarResposta(pag);
                    enviarResposta(res, out);
                }
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
    }

}
