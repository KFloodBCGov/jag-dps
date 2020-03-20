package ca.bc.gov.pssg.rsbc.dps.dpsemailworker;

import ca.bc.gov.open.pssg.rsbc.DpsMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DpsEmailConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = Keys.EMAIL_QUEUE_NAME)
    public void receiveMessage(DpsMetadata message) {

        logger.info("received new {}", message);

        try {
            logger.debug("attempting to get message meta data [{}]", message);

            logger.info("message meta data successfully received [{}]", message);

        } catch (Exception e) {
            logger.error("Error in {} while processing message: ", e.getClass().getSimpleName(), e);
        }
    }

}
