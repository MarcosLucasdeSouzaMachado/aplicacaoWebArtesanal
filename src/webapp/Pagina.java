package webapp;

public abstract class Pagina {
    public String getHtml(){
        return """
            <!DOCTYPE html>
            <html>
            <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <title>Página inicial do sistema web</title>
            </head>
            <body>
            <h1>Você é uma pessoa muito gata!</h1>
            <img src="https://www.placekittens.com/200/300">
            </body></html>""";
    }
}
