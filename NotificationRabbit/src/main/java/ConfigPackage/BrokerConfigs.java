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

        String exchange_name = "notificationExchange";
        channel.exchangeDeclare(exchange_name, BuiltinExchangeType.FANOUT, true);

        String billing_queue = "billingQueue";
        String delivery_queue = "deliveryQueue";
        String inventory_queue = "inventoryQueue";
        channel.queueDeclare(billing_queue, false, false, false, null);
        channel.queueDeclare(delivery_queue, false, false, false, null);
        channel.queueDeclare(inventory_queue, false, false, false, null);

        channel.queueBind(billing_queue, exchange_name, "");
        channel.queueBind(delivery_queue, exchange_name, "");
        channel.queueBind(inventory_queue, exchange_name, "");

        System.out.println("'notificationExchange' is successfully created!");
        System.out.println("'billingQueue', 'deliveryQueue' and 'inventoryQueue' are successfully created and bound!");

        channel.close();

        connection.close();
    }
}
