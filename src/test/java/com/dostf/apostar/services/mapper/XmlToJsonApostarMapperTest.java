package com.dostf.apostar.services.mapper;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class XmlToJsonApostarMapperTest {
    private final String EXPECTED_RESULT = "{\"recaudo-output\":{\"listado-departamentos\":{\"departamento\":[{\"codigo\":63,\"nombre\":\"Quindío\"},{\"codigo\":17,\"nombre\":\"Caldas\"},{\"codigo\":66,\"nombre\":\"Risaralda\"}]}}}".trim();
    private static final String XML_LIST = "" +
        "<recaudo-output>\n" +
        "    <listado-departamentos>\n" +
        "        <departamento>\n" +
        "            <codigo>63</codigo>\n" +
        "            <nombre>Quindío</nombre>\n" +
        "        </departamento>\n" +
        "        <departamento>\n" +
        "            <codigo>17</codigo>\n" +
        "            <nombre>Caldas</nombre>\n" +
        "        </departamento>\n" +
        "        <departamento>\n" +
        "            <codigo>66</codigo>\n" +
        "            <nombre>Risaralda</nombre>\n" +
        "        </departamento>\n" +
        "    </listado-departamentos>\n" +
        "</recaudo-output>";
    private XmlApostarMapper xml;

    @Before
    public void setup() {
        xml = new XmlApostarMapper();
    }

    @Test
    public void testToJsonString() {
        Optional<String> result = xml.toJsonString(XML_LIST);
        assertTrue(result.isPresent());
        assertEquals(EXPECTED_RESULT, result.get().trim());
    }


}
