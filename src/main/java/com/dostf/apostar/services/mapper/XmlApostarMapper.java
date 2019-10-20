package com.dostf.apostar.services.mapper;

import com.dostf.apostar.common.enums.ErrorEnum;
import com.dostf.apostar.common.exceptions.XmlParsingException;
import com.dostf.apostar.services.IXmlApostarMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class XmlApostarMapper implements IXmlApostarMapper {

    @Override
    public Optional<String> toJsonString(String xml) {
        try {
            JSONObject result = XML.toJSONObject(xml);
            return Optional.ofNullable(result.toString());
        } catch(JSONException jex) {
            throw new XmlParsingException(ErrorEnum.XML_PARSING.getMessage());
        }
    }
}
