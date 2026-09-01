package webapp;

public class RespostaTest {
    void getDocumentoBruto(){
        Resposta res = new Resposta();
        res.setStatus(200, "OK");
        res.setContentLength(28);
        res.setContentType("text/html");
        res.setBody("<html><body>Oi</body></html>");

        String bruto = """
                HTTP/1.1 200 OK
                Content-Type: text/html
                Content-Length: 28
                
                <html><body>Oi</body></html>
                """;
    }
}
