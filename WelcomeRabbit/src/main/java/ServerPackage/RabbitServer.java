package ServerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitServer {
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // --- CONFIGURATION DU CONSUMER - SUBSCRIBER (abonné RabbitMQ) ---
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Message informatif pour indiquer que le consommateur attend des messages
        System.out.println("I'm waiting for new messages...\n");

        // 1. La méthode basicConsume permet d'écouter une file d'attente existante (ici "Welcome-Queue")
        String queue_name = "Welcome-Queue";
        channel.basicConsume(queue_name, true, ((consumerTag, message) -> {
            // Conversion du message reçu (bytes) en texte lisible
            System.out.println("A message has been received : \n'" + new String(message.getBody()) + "'");
        }), consumerTag -> {/*Rien à faire si le consommateur est annulé*/});
    }
}
