package ServerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AdminServer {
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // --- SUBSCRIBER ---
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("[Admin] I'm waiting for presence messages...\n");

        String queue_name = "confirmationQueue";
        channel.basicConsume(queue_name, true, ((consumerTag, message) -> {
            String exchange_name = message.getEnvelope().getExchange();
            String routing_key = message.getEnvelope().getRoutingKey();
            System.out.println("[Admin] A message has been received from '" + exchange_name + "' (" + routing_key + ") : \n'" + new String(message.getBody()) + "'\n");
        }), consumerTag -> {/*Rien à faire si le consommateur est annulé*/});
    }
}
