package ServerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class InventoryService {
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // --- SUBSCRIBER ---
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("[Inventory Service] I'm waiting for new notifications...\n");

        String queue_name = "inventoryQueue";
        channel.basicConsume(queue_name, true, ((consumerTag, message) -> {
            System.out.println("[Inventory Service] A notification has been received : \n'" + new String(message.getBody()) + "'");
            System.out.println("[Inventory Service] Updating the stock...\n");
        }), consumerTag -> {/*Rien à faire si le consommateur est annulé*/});
    }
}
