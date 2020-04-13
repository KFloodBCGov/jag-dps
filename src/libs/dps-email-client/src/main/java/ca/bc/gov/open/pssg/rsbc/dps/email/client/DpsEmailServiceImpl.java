package ca.bc.gov.open.pssg.rsbc.dps.email.client;

import ca.bc.gov.open.dps.email.client.api.DpsEmailProcessingApi;
import ca.bc.gov.open.dps.email.client.api.handler.ApiException;
import ca.bc.gov.open.dps.email.client.api.model.DpsEmailProcessedOrdsRequest;
import ca.bc.gov.open.dps.email.client.api.model.DpsEmailProcessedOrdsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dps Email Service Implementation.
 *
 * @author carolcarpenterjustice
 */
public class DpsEmailServiceImpl implements DpsEmailService {

    private final DpsEmailProcessingApi dpsEmailProcessingApi;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DpsEmailServiceImpl(DpsEmailProcessingApi dpsEmailProcessingApi) {
        this.dpsEmailProcessingApi = dpsEmailProcessingApi;
    }

    @Override
    public DpsEmailProcessedResponse dpsEmailProcessed(String id, String correlationId) {

        try {

            DpsEmailProcessedOrdsRequest request = new DpsEmailProcessedOrdsRequest();
            request.setCorrelationId(correlationId);

            DpsEmailProcessedOrdsResponse response = this.dpsEmailProcessingApi.processedUsingPUT(id, request);
            return DpsEmailProcessedResponse.successResponse(response.getAcknowledge(), response.getMessage());

        } catch (ApiException ex) {
            logger.error("Exception caught in dpsEmailProcessed", ex);
            return DpsEmailProcessedResponse.errorResponse(ex.getMessage());
        }
    }
}