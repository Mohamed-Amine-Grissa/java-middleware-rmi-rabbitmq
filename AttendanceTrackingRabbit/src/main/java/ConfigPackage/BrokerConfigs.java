package ConfigPackage;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BrokerConfigs {
    public static void main(String[] args) throws IOException, TimeoutException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String presence_exchange = "presenceExchange";
        String confirmation_exchange = "confirmationExchange";
        channel.exchangeDeclare(presence_exchange, BuiltinExchangeType.TOPIC, true);
        channel.exchangeDeclare(confirmation_exchange, BuiltinExchangeType.TOPIC, true);

        String student_presence_queue = "studentPresenceQueue";
        String confirmation_queue = "confirmationQueue";
        channel.queueDeclare(student_presence_queue, false, false, false, null);
        channel.queueDeclare(confirmation_queue, false, false, false, null);

        channel.queueBind(student_presence_queue, presence_exchange, "presence.teacher.*");

        channel.queueBind(confirmation_queue, confirmation_exchange, "presence.#");
        channel.queueBind(confirmation_queue, presence_exchange, "presence.#");

        System.out.println("'presenceExchange' and 'confirmationExchange' are successfully created!");
        System.out.println("'studentPresenceQueue' and 'confirmationQueue' are successfully created and bound!");

        channel.close();
        connection.close();
    }
}
