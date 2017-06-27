/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.xy_inc.poi.domain.entity.Poi;
import br.com.xy_inc.poi.presentation.dto.PoiDTO;
import br.com.xy_inc.poi.util.mapper.IEntityDTOMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultEntityDTOMapperUnitTest {
    
    @Autowired
    private IEntityDTOMapper<Poi, PoiDTO> entityDTOMapper;
    
    @Test
    public void testConvertToDTO() throws Exception {
        Poi poi = new Poi(1L, "Lanchonete", 10, 20);
        PoiDTO poiDTO = entityDTOMapper.convertToDTO(poi, PoiDTO.class);
        
        assertEquals(Long.valueOf("1"), poiDTO.getId());
        assertEquals("Lanchonete", poiDTO.getNome());
        assertEquals(10, poiDTO.getX());
        assertEquals(20, poiDTO.getY());
    }
    
    @Test
    public void testConvertToEntity() throws Exception {
        PoiDTO poiDTO = new PoiDTO(1L, "Lanchonete", 10, 20);
        Poi poi = entityDTOMapper.convertToEntity(poiDTO, Poi.class);
        
        assertEquals(Long.valueOf("1"), poi.getId());
        assertEquals("Lanchonete", poi.getNome());
        assertEquals(10, poi.getX());
        assertEquals(20, poi.getY());
    }
    
    @Test
    public void testConvertToDTOList() throws Exception {
        Poi poi1 = new Poi(1L, "Lanchonete", 10, 20);
        Poi poi2 = new Poi(2L, "Pub", 20, 30);
        
        List<PoiDTO> poiDTOList = entityDTOMapper.convertToDTOList(Arrays.asList(poi1, poi2), PoiDTO.class);
        assertEquals(2, poiDTOList.size());
        assertEquals("Lanchonete", poiDTOList.get(0).getNome());
        assertEquals("Pub", poiDTOList.get(1).getNome());
    }
    
    @Test
    public void testConvertToEntityList() throws Exception {
        PoiDTO poiDTO1 = new PoiDTO(1L, "Lanchonete", 10, 20);
        PoiDTO poiDTO2 = new PoiDTO(2L, "Pub", 20, 30);
        
        List<Poi> poiList = entityDTOMapper.convertToEntityList(Arrays.asList(poiDTO1, poiDTO2), Poi.class);
        assertEquals(2, poiList.size());
        assertEquals("Lanchonete", poiList.get(0).getNome());
        assertEquals("Pub", poiList.get(1).getNome());
    }
    
}
