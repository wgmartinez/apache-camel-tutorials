package co.wgmartinez.camel.orders.events;

import org.apache.camel.component.google.pubsub.GooglePubsubComponent;
import org.apache.camel.component.google.pubsub.GooglePubsubConnectionFactory;

import java.io.InputStream;
import java.util.Properties;

public interface PubsubUtil {

    static GooglePubsubComponent createComponent() {
        GooglePubsubComponent component = new GooglePubsubComponent();
        Properties properties = loadProperties();
        GooglePubsubConnectionFactory connectionFactory = createConnectionFactory(properties);
        component.setConnectionFactory(connectionFactory);
        return component;
    }

    static GooglePubsubConnectionFactory createConnectionFactory(Properties properties) {
        GooglePubsubConnectionFactory connectionFactory = new GooglePubsubConnectionFactory();
        connectionFactory.setCredentialsFileLocation(properties.getProperty("credentials.fileLocation"));
        connectionFactory.setServiceAccount(properties.getProperty("credentials.account"));
        connectionFactory.setServiceAccountKey(properties.getProperty("credentials.key"));
        connectionFactory.setServiceURL(properties.getProperty("pubsub.serviceUrl"));
        return connectionFactory;
    }

    static Properties loadProperties() {
        Properties properties = new Properties();
        InputStream fileIn = PubsubUtil.class.getClassLoader().getResourceAsStream("gcp.properties");
        try {
            properties.load(fileIn);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return properties;
    }
}
