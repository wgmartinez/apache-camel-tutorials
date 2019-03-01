package co.wgmartinez.bigdata.kakfa.bigdatakafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PartitionProducer {
    private static final String TOPIC = "bigdata-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";


    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "SinglePartitionProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "co.wgmartinez.bigdata.kakfa.bigdatakafka.producer.SimplePartitioner");

        return new KafkaProducer<Long, String>(props);
    }

    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();

        try {
            for (long index = time; index < time + sendMessageCount; index++) {
                final ProducerRecord<Long, String> record =
                        new ProducerRecord<>(TOPIC, index,
                                "Hello Padre " + index);

                RecordMetadata metadata = producer.send(record).get();

                long elapsedTime = System.currentTimeMillis() - time;
                System.out.printf("sent record(key=%s value=%s) " +
                                "meta(partition=%d, offset=%d) time=%d\n",
                        record.key(), record.value(), metadata.partition(),
                        metadata.offset(), elapsedTime);

            }
        } finally {
            producer.flush();
            producer.close();
        }
    }

    public static void main(String... args) throws Exception {
        if (args.length == 0) {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.submit(
                    () -> {
                        try {
                            runProducer(1_000_000);
                        } catch (Exception e){}
                    }

            );

            executorService.shutdown();
        } else {
            runProducer(Integer.parseInt(args[0]));
        }
    }

}
