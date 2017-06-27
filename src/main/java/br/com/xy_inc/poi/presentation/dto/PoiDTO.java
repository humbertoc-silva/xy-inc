/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.presentation.dto;

import java.util.Objects;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Classe {@code DTO} para a entidade {@link br.com.xy_inc.poi.domain.entity.Poi}.
 * 
 * @author Humberto Corrêa da Silva
 *
 */
public class PoiDTO {

    private Long id;
    
    @NotBlank(message = "O atributo nome deve ser informado.")
    private String nome;
    
    @Min(value = 0, message = "A coordenada X deve ser um número inteiro positivo.")
    private int x;
    
    @Min(value = 0, message = "A coordenada Y deve ser um número inteiro positivo.")
    private int y;
    
    /**
     * Construtor padrão.
     */
    public PoiDTO() {}
    
    /**
     * Construtor com parâmetros.
     * 
     * @param id Identificador do POI.
     * @param nome Nome do POI.
     * @param x Coordenada X do POI.
     * @param y Coordenada Y do POI.
     */
    public PoiDTO(Long id, String nome, int x, int y) {
        this.id = id;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Método getter que retorna o identificador do POI.
     * 
     * @return Identificador do POI.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método setter que define o identificador do POI.
     * 
     * @param id Novo identificador do POI.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método getter que retorna o nome do POI.
     * 
     * @return Nome do POI.
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Método setter que define o nome do POI.
     * 
     * @param nome Novo nome do POI.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Método getter que retorna a coordenada X do POI.
     * 
     * @return Coordenada X do POI.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Método setter que define a coordenada X do POI.
     * 
     * @param x Nova coordenada X do POI.
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Método getter que retorna a coordenada Y do POI.
     * 
     * @return Coordenada Y do POI.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Método setter que define a coordenada Y do POI.
     * 
     * @param y Nova coordenada Y do POI.
     */
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof PoiDTO)) {
            return false;
        }

        final PoiDTO other = (PoiDTO) obj;
        return Objects.equals(id, other.id) 
                && Objects.equals(nome, other.nome) 
                && Objects.equals(x, other.x) 
                && Objects.equals(y, other.y);
    }
    
}
