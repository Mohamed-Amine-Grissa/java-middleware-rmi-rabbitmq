package ServerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class StudentServer {
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // --- SUBSCRIBER + PUBLISHER ---
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("[Student] I'm waiting for the teacher's presence request...\n");

        String queue_name = "studentPresenceQueue";
        channel.basicConsume(queue_name, true, ((consumerTag, message) -> {
            System.out.println("[Student] A message has been received from the teacher : \n'" + new String(message.getBody()) + "'");

            // Renvoi d'une confirmation de présence vers confirmationExchange
            String confirmation = "QR Code scanned, presence confirmed at " + LocalDateTime.now();
            String exchange_name = "confirmationExchange";
            String routing_key = "presence.student.confirmed";//presence.person.action
            channel.basicPublish(exchange_name, routing_key, null, confirmation.getBytes());

            System.out.println("[Student] Presence confirmation is successfully sent!\n");
        }), consumerTag -> {/*Rien à faire si le consommateur est annulé*/});
    }
}
