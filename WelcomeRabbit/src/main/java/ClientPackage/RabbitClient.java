package ClientPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class RabbitClient {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException,
            IOException, TimeoutException {

        // --- PRODUCTEUR (Publisher / Sender) ---
        ConnectionFactory factory= new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel=connection.createChannel();

        // 1.Création du message à envoyer
        String msgToPublish = "Welcome to everyone! It's " + LocalDateTime.now();

        // 2.Envoi du message via le Default Exchange
        String exchange_name="";
        String routing_key = "Welcome-Queue";//routing_key==queue_name
        channel.basicPublish(exchange_name, routing_key, null, msgToPublish.getBytes());

        // 3.Confirmation dans la console
        System.out.println("Our message is successfully published!\n Let's take a look at the consumer");

        channel.close();
        connection.close();
    }
}
