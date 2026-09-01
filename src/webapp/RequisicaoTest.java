package webapp;

import static org.junit.jupiter.api.Assertions.*;

class RequisicaoTest {

    @org.junit.jupiter.api.Test
    void testPegarMetodoEUrl() {

        String documento = """
                GET / HTTP/1.1
                Host: www.pudim.com.br
                Agent: mozilla
                """;

        Requisicao r = new Requisicao(documento);
        assertEquals("GET", r.getMetodo());
        assertEquals("/", r.getURL());
    }

    @org.junit.jupiter.api.Test
    void testPegarMetodoEUrlMaisElaborada() {

        String documento = """
                GET /solicitarPudim.html HTTP/1.1
                Host: www.pudim.com.br
                Agent: mozilla
                """;

        Requisicao r = new Requisicao(documento);
        assertEquals("GET", r.getMetodo());
        assertEquals("/solicitarPudim.html", r.getURL());
    }

    @org.junit.jupiter.api.Test
    void testRequisicaoComCorpo(){
        String documento = """
            POST /receptor HTTP/1.1
            Host: localhost
            Content-Type: application/x-www-form-urlencoded
            Content-Length: 28
            
            livro=1984&categoria=ficcao""";

        Requisicao r = new Requisicao(documento);
        assertEquals("POST", r.getMetodo());
        assertEquals("livro=1984&categoria=ficcao", r.getCorpo());
    }

}