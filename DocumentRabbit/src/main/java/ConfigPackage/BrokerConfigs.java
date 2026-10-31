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

        String exchange_name = "documentExchange";
        channel.exchangeDeclare(exchange_name, BuiltinExchangeType.DIRECT, true);

        String pdf_queue = "pdfQueue";
        String word_queue = "wordQueue";
        String image_queue = "imageQueue";
        channel.queueDeclare(pdf_queue, false, false, false, null);
        channel.queueDeclare(word_queue, false, false, false, null);
        channel.queueDeclare(image_queue, false, false, false, null);

        channel.queueBind(pdf_queue, exchange_name, "pdf");
        channel.queueBind(word_queue, exchange_name, "word");
        channel.queueBind(image_queue, exchange_name, "image");

        System.out.println("'documentExchange' is successfully created!");
        System.out.println("'pdfQueue', 'wordQueue' and 'imageQueue' are successfully created and bound!");

        channel.close();
        connection.close();
    }
}
