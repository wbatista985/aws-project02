package br.com.siecola.aws_project02.service;

import br.com.siecola.aws_project02.model.Envelope;
import br.com.siecola.aws_project02.model.ProductEvent;
import br.com.siecola.aws_project02.model.SnsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;

@Service
public class ProductEventsConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProductEventsConsumer.class);

    private final ObjectMapper objectMapper;

    public ProductEventsConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void recieveProductEvent(TextMessage textMessage) throws JMSException, IOException {

        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(), SnsMessage.class);

        Envelope envelope = objectMapper.readValue(snsMessage.getMessage(), Envelope.class);

        ProductEvent productEvent = objectMapper.readValue(
                envelope.getData(), ProductEvent.class);

        log.info("Product event received - Event: {} - ProductId: {} - MessageId: {}",
                envelope.getEventType(),
                productEvent.getProductId(),
                snsMessage.getMessageId());
    }


}
