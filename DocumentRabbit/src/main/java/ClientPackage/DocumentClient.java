package ClientPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DocumentClient {
    public static void main(String[] args) throws IOException, TimeoutException {

        // --- PUBLISHER ---
        ConnectionFactory factory= new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel=connection.createChannel();

        String[] documents = {
                "C:/Documents/rapport.pdf",
                "C:/Documents/cv.docx",
                "C:/Documents/photo.jpeg",
                "C:/Documents/facture.pdf",
                "C:/Documents/lettre.doc",
                "C:/Documents/logo.png"
        };

        String exchange_name="documentExchange";
        for (String document : documents) {
            String routing_key = getDocumentType(document);//pdf, word ou image
            channel.basicPublish(exchange_name, routing_key, null, document.getBytes());
            System.out.println("[Client] '" + document + "' is successfully published with routing key '" + routing_key + "'");
        }

        channel.close();
        connection.close();
    }

    private static String getDocumentType(String document) {
        String extension = document.substring(document.lastIndexOf('.') + 1).toLowerCase();
        return switch (extension) {
            case "pdf" -> "pdf";
            case "doc", "docx" -> "word";
            case "jpg", "jpeg", "png" -> "image";
            default -> "unknown";
        };
    }
}
