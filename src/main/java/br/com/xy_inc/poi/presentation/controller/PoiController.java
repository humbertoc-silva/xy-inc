/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.presentation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xy_inc.poi.domain.entity.Poi;
import br.com.xy_inc.poi.persistence.repository.IPoiRepository;
import br.com.xy_inc.poi.presentation.dto.PoiDTO;
import br.com.xy_inc.poi.util.mapper.IEntityDTOMapper;

/**
 * Controlador para entidades do tipo {@link Poi}.
 * 
 * @author Humberto Corrêa da Silva
 */
@RestController
@RequestMapping("/api/poi")
public class PoiController {

    @Autowired
    private IPoiRepository poiRepository;
    
    @Autowired
    private IEntityDTOMapper<Poi, PoiDTO> entityDTOMapper;
    
    /**
     * Ação responsável por atender as requisições de todos os pontos de interesse.
     * 
     * @return Lista com todos os pontos de interesse.
     */
    @GetMapping
    public List<PoiDTO> list() {
        return entityDTOMapper.convertToDTOList(poiRepository.findAll(), PoiDTO.class);
    }
    
    /**
     * Ação responsável por atender as requisições de pontos de interesse próximos a um ponto de referência.
     * 
     * @param x Coordenada X do ponto de referência.
     * @param y Coordenada Y do ponto de referência.
     * @param dMax Distância máxima que um ponto de interesse deve possuir em relação ao ponto de referência.
     * @return Lista de pontos de interesse próximos ao ponto de referência informado.
     */
    @GetMapping("/proximidade")
    public List<PoiDTO> listByProximidade(@RequestParam(name = "x", required = true) int x, 
            @RequestParam(name = "y", required = true) int y, 
            @RequestParam(name = "dMax", required = true) int dMax) {
        return entityDTOMapper.convertToDTOList(poiRepository.findByPontoReferenciaAndDistanciaMaxima(x, y, dMax),
                PoiDTO.class);
    }
    
    /**
     * Ação responsável por atender as requisições de cadastro de um novo ponto de interesse.
     * 
     * @param poiDTO {@code DTO} contendo os dados do ponto de interesse.
     * @return {@code DTO} contendo os dados do novo ponto de interesse cadastrado já com o identificador preenchido.
     */
    @PostMapping
    public ResponseEntity<PoiDTO> create(@Valid @RequestBody PoiDTO poiDTO) {
        Poi poi = poiRepository.save(entityDTOMapper.convertToEntity(poiDTO, Poi.class));
        return new ResponseEntity<PoiDTO>(entityDTOMapper.convertToDTO(poi, PoiDTO.class), HttpStatus.CREATED);
    }
}
