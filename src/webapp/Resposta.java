package webapp;

public class Resposta {
    private int codigoStatus;
    private String msgStatus;
    private String contentType;
    private int contentLength;
    private String body;

    public Resposta() {}
    public void setStatus(int codigo, String msg) {
        codigoStatus = codigo;
        msgStatus = msg;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public void setContentLength(int length) {
        this.contentLength = contentLength;
    }
    public void setBody(String body) {
        this.body = body;
        this.contentLength = body != null ? body.getBytes().length : 0;
    }
    public String getDocumentoBruto() {
        return """
                HTTP/1.1 %d %s
                Content-Type: %s
                Content-Length: %d
                
                %s
                """.formatted(codigoStatus, msgStatus, contentType, contentLength, body);
    }
}
