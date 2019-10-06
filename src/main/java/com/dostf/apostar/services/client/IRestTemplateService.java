package com.dostf.apostar.services.client;

import java.util.Optional;

public interface IRestTemplateService {
    /**
     * generic template for consume services apostar
     * @param uri
     * @param request
     * @return
     */
    public Optional<Object> post(String uri, Object request);
}
