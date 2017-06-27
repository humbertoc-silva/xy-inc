/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.xy_inc.poi.domain.entity.Poi;

/**
 * Repositório para entidades do tipo {@link Poi}.
 * 
 * @author Humberto Corrêa da Silva
 */
public interface IPoiRepository extends JpaRepository<Poi, Long> {
    
    /**
     * Método que retorna uma lista de pontos de interesse baseados em um ponto de refência e uma distância máxima.
     * 
     * @param x Coordenada X do ponto de referência.
     * @param y Coordenada Y do ponto de referência.
     * @param dMax Distância máxima que um ponto de interesse deve possuir em relação ao ponto de referência.
     * @return Lista de pontos de interesse.
     */
    @Query("select p from Poi p where (power((p.x - :x), 2) + power((p.y - :y), 2)) <= power(:dMax, 2)")
    List<Poi> findByPontoReferenciaAndDistanciaMaxima(@Param("x") int x, @Param("y") int y, @Param("dMax") int dMax);

}
