/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.util.mapper;

import java.util.List;

import br.com.xy_inc.poi.domain.entity.IEntity;

/**
 * Interface que define os métodos necessários para realizar o mapeamento entre entidades e {@code DTOs} (Data Transfer Object)
 * e vice-versa.
 * 
 * @author Humberto Corrêa da Silva
 *
 * @param <T> Classe da entidade. Esta classe deve implementar a interface {@link IEntity}.
 * @param <U> Classe do {@code DTO}.
 */
public interface IEntityDTOMapper<T extends IEntity<?>, U> {

    /**
     * Método que converte uma entidade para um {@code DTO}.
     * 
     * @param entity Entidade que será convertida.
     * @param dtoClass Classe {@code DTO} de destino.
     * @return Um {@code DTO} instanciado e inicializado com os dados da entidade.
     */
    U convertToDTO(T entity, Class<U> dtoClass);

    /**
     * Método que converte um {@code DTO} para uma entidade.
     * 
     * @param dto {@code DTO} que será convertido.
     * @param entityClass Classe da entidade de destino.
     * @return Uma entidade instanciada e inicializada com os dados do {@code DTO}.
     */
    T convertToEntity(U dto, Class<T> entityClass);

    /**
     * Método que converte uma lista de entidades para uma lista de {@code DTOs}.
     * 
     * @param entityList Lista de entidades que será convertida.
     * @param dtoClass Classe {@code DTO} de destino.
     * @return Uma lista de {@code DTOs}.
     */
    List<U> convertToDTOList(List<T> entityList, Class<U> dtoClass);

    /**
     * Método que converte uma lista de {@code DTOs} para uma lista de entidades.
     * 
     * @param dtoList Lista de {@code DTOs} que será convertida.
     * @param entityClass Classe da entidade de destino.
     * @return Uma lista de entidades.
     */
    List<T> convertToEntityList(List<U> dtoList, Class<T> entityClass);

}
