/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.domain.entity;

import java.io.Serializable;

/**
 * Interface implementada por todas as classes que representam uma entidade do sistema.  
 * @author Humberto Corrêa da Silva
 *
 * @param <T> Tipo de dado do identificador da entidade.
 */
public interface IEntity<T extends Serializable> extends Serializable {

    /**
     * Método getter que retorna o identificador da entidade.
     * 
     * @return Identificador da entidade.
     */
    T getId();
    
    /**
     * Método setter que define o identificador da entidade.
     * 
     * @param id Novo identificador da entidade.
     */
    void setId(T id);
    
}
