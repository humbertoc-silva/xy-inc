/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.presentation.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class PoiDTOJsonUnitTest {

    @Autowired
    private JacksonTester<PoiDTO> jacksonTester;
    
    @Test
    public void testSerialize() throws Exception {
        PoiDTO poiDTO = new PoiDTO(1L, "Padaria", 10, 20);
        
        assertThat(jacksonTester.write(poiDTO)).hasJsonPathNumberValue("@.id");
        assertThat(jacksonTester.write(poiDTO)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        
        assertThat(jacksonTester.write(poiDTO)).hasJsonPathStringValue("@.nome");
        assertThat(jacksonTester.write(poiDTO)).extractingJsonPathStringValue("@.nome").isEqualTo("Padaria");
        
        assertThat(jacksonTester.write(poiDTO)).hasJsonPathNumberValue("@.x");
        assertThat(jacksonTester.write(poiDTO)).extractingJsonPathNumberValue("@.x").isEqualTo(10);
        
        assertThat(jacksonTester.write(poiDTO)).hasJsonPathNumberValue("@.y");
        assertThat(jacksonTester.write(poiDTO)).extractingJsonPathNumberValue("@.y").isEqualTo(20);
    }
    
    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\":1,\"nome\":\"Padaria\",\"x\":10,\"y\":20}";
        assertThat(jacksonTester.parse(content)).isEqualTo(new PoiDTO(1L, "Padaria", 10, 20));
    }
    
}
