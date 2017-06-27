/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.presentation.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.xy_inc.poi.domain.entity.Poi;
import br.com.xy_inc.poi.persistence.repository.IPoiRepository;
import br.com.xy_inc.poi.presentation.dto.PoiDTO;
import br.com.xy_inc.poi.util.mapper.IEntityDTOMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(PoiController.class)
public class PoiControllerUnitTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private IPoiRepository poiRepository;
    
    @MockBean
    private IEntityDTOMapper<Poi, PoiDTO> entityDTOMapper;
    
    @Test
    public void testList() throws Exception {
        List<Poi> poiList = createPoiList();
        
        given(poiRepository.findAll()).willReturn(poiList);
        given(entityDTOMapper.convertToDTOList(poiList, PoiDTO.class)).willReturn(createPoiDTOList());
        
        mvc.perform(get("/api/poi").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nome").value("Padaria"))
            .andExpect(jsonPath("$[0].x").value(10))
            .andExpect(jsonPath("$[0].y").value(20))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].nome").value("Supermercado"))
            .andExpect(jsonPath("$[1].x").value(20))
            .andExpect(jsonPath("$[1].y").value(20));
    }

    @Test
    public void testListByProximidade() throws Exception {
        List<Poi> poiList = createPoiList();
        
        given(poiRepository.findByPontoReferenciaAndDistanciaMaxima(10, 20, 10)).willReturn(poiList);
        given(entityDTOMapper.convertToDTOList(poiList, PoiDTO.class)).willReturn(createPoiDTOList());
        
        mvc.perform(get("/api/poi/proximidade?x=10&y=20&dMax=10").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nome").value("Padaria"))
            .andExpect(jsonPath("$[0].x").value(10))
            .andExpect(jsonPath("$[0].y").value(20))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].nome").value("Supermercado"))
            .andExpect(jsonPath("$[1].x").value(20))
            .andExpect(jsonPath("$[1].y").value(20));
    }
    
    @Test
    public void testListByProximidadeFaltandoParametro() throws Exception {
        mvc.perform(get("/api/poi/proximidade?x=10&y=20").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testListByProximidadeComValorDeParametroErrado() throws Exception {
        mvc.perform(get("/api/poi/proximidade?x=a&y=20&dMax=ERRADO").accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testCreate() throws Exception {
        String content = "{\"nome\":\"Padaria\", \"x\":10, \"y\":20}";
        
        Poi poi = new Poi(null, "Padaria", 10, 20);
        Poi newPoi = new Poi(1L, "Padaria", 10, 20);
        PoiDTO newPoiDTO = new PoiDTO(1L, "Padaria", 10, 20);
        
        given(entityDTOMapper.convertToEntity(any(PoiDTO.class), eq(Poi.class))).willReturn(poi);
        given(poiRepository.save(poi)).willReturn(newPoi);
        given(entityDTOMapper.convertToDTO(newPoi, PoiDTO.class)).willReturn(newPoiDTO);
        
        mvc.perform(post("/api/poi")
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andExpect(status().is(HttpStatus.CREATED.value()))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nome").value("Padaria"))
            .andExpect(jsonPath("$.x").value(10))
            .andExpect(jsonPath("$.y").value(20));
    }
    
    @Test
    public void testCreateComCoordenadasNegativas() throws Exception {
        String content = "{\"nome\":\"Padaria\", \"x\":-10, \"y\":-20}";
        
        mvc.perform(post("/api/poi")
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andExpect(status().isBadRequest());
    }
    
    private List<Poi> createPoiList() {
        Poi poi1 = new Poi(1L, "Padaria", 10, 20);
        Poi poi2 = new Poi(2L, "Supermercado", 20, 20);
        List<Poi> poiList = Arrays.asList(poi1, poi2);
        return poiList;
    }
    
    private List<PoiDTO> createPoiDTOList() {
        PoiDTO poiDTO1 = new PoiDTO(1L, "Padaria", 10, 20);
        PoiDTO poiDTO2 = new PoiDTO(2L, "Supermercado", 20, 20);
        List<PoiDTO> poiDTOList = Arrays.asList(poiDTO1, poiDTO2);
        return poiDTOList;
    }
    
}
