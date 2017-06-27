/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.xy_inc.poi.domain.entity.IEntity;

/**
 * Classe padrão que realiza o mapeamento entre entidades e {@code DTOs} (Data Transfer Object)
 * e vice-versa.
 * 
 * @author Humberto Corrêa da Silva
 *
 * @param <T> Classe da entidade. Esta classe deve implementar a interface {@link IEntity}.
 * @param <U> Classe do {@code DTO}.
 */
@Component
public final class DefaultEntityDTOMapper<T extends IEntity<?>, U> implements IEntityDTOMapper<T, U> {

    private final ModelMapper modelMapper;

    /**
     * Constroi uma instância com suas devidas dependências.
     *  
     * @param modelMapper Uma instância de {@link ModelMapper}.
     */
    @Autowired
    public DefaultEntityDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public U convertToDTO(T entity, Class<U> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public T convertToEntity(U dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public List<U> convertToDTOList(List<T> entityList, Class<U> dtoClass) {
        if (entityList == null) {
            throw new IllegalArgumentException("entityList cannot be null");
        }

        List<U> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(convertToDTO(entity, dtoClass)));

        return dtoList;
    }

    @Override
    public List<T> convertToEntityList(List<U> dtoList, Class<T> entityClass) {
        if (dtoList == null) {
            throw new IllegalArgumentException("dtoList cannot be null");
        }

        List<T> entityList = new ArrayList<>();
        dtoList.forEach(dto -> entityList.add(convertToEntity(dto, entityClass)));

        return entityList;
    }

}
