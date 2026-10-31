package ClientPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class NotificationClient {
    public static void main(String[] args) throws IOException, TimeoutException {
        
        ConnectionFactory factory= new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel=connection.createChannel();

        String msgToPublish = "Order #" + System.currentTimeMillis() + " has been validated at " + LocalDateTime.now();

        String exchange_name="notificationExchange";
        String routing_key = "";
        channel.basicPublish(exchange_name, routing_key, null, msgToPublish.getBytes());

        System.out.println("Our notification is successfully published!\n Let's take a look at the services");

        channel.close();
        connection.close();
    }
}
