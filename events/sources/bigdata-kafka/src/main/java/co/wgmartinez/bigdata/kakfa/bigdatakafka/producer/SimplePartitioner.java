package co.wgmartinez.bigdata.kakfa.bigdatakafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Random;

public class SimplePartitioner implements Partitioner {
    private int numberOfPartitions = 3;

    public SimplePartitioner(){}

    public SimplePartitioner(int numberOfPartitions){
        this.numberOfPartitions = numberOfPartitions;
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return new Random().nextInt(3);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
