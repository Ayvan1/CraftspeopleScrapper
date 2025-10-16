package org.HS.Serialization;

import org.HS.data.PersonalInformation;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonInformationSerialization implements Serializer<PersonalInformation> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public  byte[] serialize(String topic, PersonalInformation data){
        try{
            if(data == null)
                return null;
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e){
            throw new SerializationException(e.getMessage());
        }
    }
}
