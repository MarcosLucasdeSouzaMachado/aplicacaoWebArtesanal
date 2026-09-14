import * as http from 'node:http';
import fs from 'node:fs';
import path from 'node:path';
import { buscar } from './acervo.js';

const servidor = http.createServer((req, res) => {
    let url = new URL(`http://localhost:5500${req.url}`);
    
    if (url.pathname === '/buscarNoAcervo') {
        let termo = url.searchParams.get('termo');
        console.log(`Busca realizada: ${termo}`);
        
        let resultado = buscar(termo);
        let html = gerarHTMLComResultados(termo, resultado);
        res.statusCode = 200;
        res.setHeader('Content-Type', 'text/html; charset=utf-8');
        res.end(html);
        return;
    }
    
    let caminhoArquivo = req.url === '/' ? '/index.html' : req.url;
    
    caminhoArquivo = caminhoArquivo.split('?')[0];
    
    caminhoArquivo = caminhoArquivo.startsWith('/') ? 
        caminhoArquivo.slice(1) : caminhoArquivo;
    
    fs.readFile(caminhoArquivo, (err, data) => {
        if (err) {
            res.statusCode = 404;
            res.setHeader('Content-Type', 'text/html; charset=utf-8');
            res.end(`<!DOCTYPE html>
                <html lang="pt-br">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Erro 404</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            min-height: 100vh;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            margin: 0;
                        }
                        .container {
                            background: white;
                            padding: 50px;
                            border-radius: 10px;
                            text-align: center;
                            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
                        }
                        h1 {
                            color: #667eea;
                            font-size: 48px;
                            margin-bottom: 20px;
                        }
                        p {
                            color: #555;
                            font-size: 18px;
                            margin-bottom: 30px;
                        }
                        a {
                            color: white;
                            text-decoration: none;
                            font-size: 16px;
                            padding: 10px 20px;
                            background: #667eea;
                            border-radius: 5px;
                            transition: all 0.3s;
                            display: inline-block;
                        }
                        a:hover {
                            background: #764ba2;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Erro 404</h1>
                        <p>O recurso "<strong>${caminhoArquivo}</strong>" não foi encontrado!</p>
                        <a href="/">Voltar à página inicial</a>
                    </div>
                </body>
                </html>`);
            return;
        }
        
        // Arquivo encontrado - definir Content-Type apropriado
        let extensao = path.extname(caminhoArquivo).toLowerCase();
        let contentType = 'application/octet-stream';
        
        if (extensao === '.html') {
            contentType = 'text/html; charset=utf-8';
        } else if (extensao === '.css') {
            contentType = 'text/css; charset=utf-8';
        } else if (extensao === '.js') {
            contentType = 'application/javascript; charset=utf-8';
        } else if (extensao === '.json') {
            contentType = 'application/json; charset=utf-8';
        } else if (['.jpg', '.jpeg'].includes(extensao)) {
            contentType = 'image/jpeg';
        } else if (extensao === '.png') {
            contentType = 'image/png';
        } else if (extensao === '.webp') {
            contentType = 'image/webp';
        } else if (extensao === '.gif') {
            contentType = 'image/gif';
        } else if (extensao === '.svg') {
            contentType = 'image/svg+xml';
        }
        
        res.statusCode = 200;
        res.setHeader('Content-Type', contentType);
        res.end(data);
    });
});

function gerarHTMLComResultados(termo, listaDeLivros) {
    let html = `<!DOCTYPE html>
    <html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Resultados da Busca</title>
        <link rel="stylesheet" href="index.css">
    </head>
    <body>
        <div class="container">
            <h1>Resultados da Busca</h1>
            <p class="info">Termo buscado: <span class="search-term">${termo || 'nenhum'}</span></p>
    `;
    
    if (listaDeLivros.length === 0) {
        html += `<div class="no-results">
                    Nenhum livro encontrado com o termo "<strong>${termo}</strong>".
                    <br><br>
                    Tente buscar com outros termos.
                </div>`;
    } else {
        html += `<table>
                    <thead>
                        <tr>
                            <th>Título</th>
                            <th>Autor</th>
                            <th>Localizador</th>
                        </tr>
                    </thead>
                    <tbody>`;
        
        listaDeLivros.forEach(livro => {
            html += `<tr>
                        <td>${livro.titulo}</td>
                        <td>${livro.autor}</td>
                        <td><strong>${livro.localizador}</strong></td>
                    </tr>`;
        });
        
        html += `</tbody>
                </table>
                <p class="info">✓ ${listaDeLivros.length} livro(s) encontrado(s)</p>`;
    }
    
    html += `<div class="links">
                <a href="/">Voltar ao Início</a>
                <a href="/sobre.html">Sobre Nós</a>
            </div>
        </div>
    </body>
    </html>`;
    
    return html;
}

const porta = 3000;
servidor.listen(porta, () => {
    console.log(`Servidor rodando em http://localhost:${porta}`);
    console.log('Pressione Ctrl+C para parar o servidor');
});