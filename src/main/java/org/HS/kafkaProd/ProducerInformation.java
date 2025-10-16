package org.HS.kafkaProd;

import org.HS.data.PersonalInformation;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerInformation {
    private Properties props;
    private KafkaProducer<String, PersonalInformation> producer;

    public ProducerInformation() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.HS.Serialization.PersonInformationSerialization");
        producer = new KafkaProducer<>(props);
    }

    public void send(String topic, PersonalInformation data) {
        ProducerRecord<String, PersonalInformation> record = new ProducerRecord<>(topic, data);
        producer.send(record);
    }
}
