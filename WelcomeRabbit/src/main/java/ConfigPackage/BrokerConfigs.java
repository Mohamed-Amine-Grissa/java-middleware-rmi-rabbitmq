package ConfigPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BrokerConfigs {
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // 1.Création d'une instance de ConnectionFactory qui permet de configurer les paramètres de connexion à RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        // 2.Définition de l'adresse "localhost" du serveur RabbitMQ
        factory.setHost("localhost");
        // 3.Définition du port par défaut de RabbitMQ (5672 pour les connexions AMQP)
        factory.setPort(5672);

        // 4.Création d'une nouvelle connexion entre ce programme et RabbitMQ en utilisant la configuration de la factory
        Connection connection = factory.newConnection();
        // 5.Ouverture d'un canal de communication (Channel) sur cette connexion
        Channel channel = connection.createChannel();

        // 6.Création d'une file de messages nommée "Welcome-Queue"
        String queue_name = "Welcome-Queue";
        channel.queueDeclare(queue_name, false, false, false, null);

        // 7.Affichage dans la console pour confirmer la création de la queue
        System.out.println("'Welcome-Queue' is successfully created!");

        // 8.Fermeture du canal pour libérer les ressources
        channel.close();

        // 9.Fermeture de la connexion au serveur RabbitMQ
        connection.close();
    }
}
