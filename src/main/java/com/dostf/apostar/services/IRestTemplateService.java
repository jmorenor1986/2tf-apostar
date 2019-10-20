package com.dostf.apostar.services;

import java.util.Optional;

public interface IRestTemplateService {
    /**
     * generic template for consume services apostar
     * @param uri
     * @param request
     * @return
     */
    Optional<String> post(String uri, Object request);

}
