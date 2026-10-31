package ServerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ImageConsumer {
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // --- SUBSCRIBER ---
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("[Image Consumer] I'm waiting for new image documents...\n");

        String queue_name = "imageQueue";
        channel.basicConsume(queue_name, true, ((consumerTag, message) -> {
            System.out.println("[Image Consumer] An image document has been received : '" + new String(message.getBody()) + "'");
        }), consumerTag -> {/*Rien à faire si le consommateur est annulé*/});
    }
}
