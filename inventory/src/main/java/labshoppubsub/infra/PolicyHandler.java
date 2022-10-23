package labshoppubsub.infra;

import javax.transaction.Transactional;

import labshoppubsub.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import labshoppubsub.domain.*;


@Service
@Transactional
public class PolicyHandler{
    @Autowired InventoryRepository inventoryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderPlaced'")
    public void wheneverOrderPlaced_DecreaseStock(@Payload OrderPlaced orderPlaced){

        OrderPlaced event = orderPlaced;
        System.out.println("\n\n##### listener DecreaseStock : " + orderPlaced + "\n\n");


        

        // Sample Logic //
        Inventory.decreaseStock(event);
        

        

    }

}


