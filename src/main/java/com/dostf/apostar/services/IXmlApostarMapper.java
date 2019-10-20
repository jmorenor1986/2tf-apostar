package com.dostf.apostar.services;

import java.util.Optional;

public interface IXmlApostarMapper {
    Optional<String> toJsonString(String xml);
}
