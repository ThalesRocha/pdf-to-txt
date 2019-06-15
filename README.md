# PDF-to-TXT

## Descrição

Aplicação para extrair o conteúdo de um PDF (base64) para um arquivo de texto. Implementação para testar os conceitos de microserviços.

## Requisitos para Execução

JRE 1.8+

## Instruções para Execução

1. Clone o repositório do github `git clone https://github.com/ThalesRocha/pdf-to-txt.git`
2. Depois de clonar os repositórios abra o prompt de comando, navegue até a pasta `pdf-to-txt/consumer` e execute o seguinte comando `mvnw clean install`.
3. Repita o procedimento agora navegando até a pasta `pdf-to-txt/producer`.
4. Crie o diretório `C:\conversor` para facilitar a execução da aplicação.
5. Copie os arquivos `consumer.jar` e `producer.jar` das pastas `consumer\target` e `producer\target` respectivamente para o diretório `C:\conversor`.
6. Realize o download do ActiveMQ através do site https://activemq.apache.org/components/classic/download/
7. Extraia o arquivo `apache-activemq-<version>-bin.zip` no diretório `C:\conversor`
8. Abra o prompt de comando e acesse o diretório `C:\conversor\apache-activemq-<version>` em seguida execute o comando `bin\activemq start`para startar o ActiveMQ. Deixe-o rodando.
9. Abra uma nova janela do prompt de comando, navegue até o diretório `C:\conversor` e execute o comando `java -jar producer.jar` para iniciar a API (produtor).
10. Abra uma nova janela do prompt de comando, navegue até o diretório `C:\conversor` e execute o comando `java -jar consumer.jar` para iniciar o consumidor.
11. Com uma ferramenta específica (eg. Postman) realize um `POST` no endpoint `localhost:8080/upload` com o seguinte body:
    ```json
    {
      "file-name": "lorem-ipsum",
      "file-content": "<PDF na base64>"
    }
    ```
12. Se tudo correr bem a chamada deve retornar:
    ```json
    {
    "message": "Sucesso na requisição"
    }
    ```
    E um arquivo `lorem-ipsum.txt` aparecer no diretório `C:\conversor` com o conteúdo do PDF.

13. Caso contrário alguma exceção será retornada.
14. Os logs dos serviços podem ser analisados no diretório `C:\conversor\logs`


That's all folks :D