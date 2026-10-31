package ClientPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TeacherClient {
    public static void main(String[] args) throws IOException, TimeoutException {

        // --- PUBLISHER ---
        ConnectionFactory factory= new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel=connection.createChannel();

        String msgToPublish = "Please, scan the QR Code for signing the lecture presence";

        String exchange_name="presenceExchange";
        String routing_key = "presence.teacher.request";//presence.person.action
        channel.basicPublish(exchange_name, routing_key, null, msgToPublish.getBytes());

        System.out.println("[Teacher] Our presence request is successfully published!\n Let's take a look at the students");

        channel.close();
        connection.close();
    }
}
