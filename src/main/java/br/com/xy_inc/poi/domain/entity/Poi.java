/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A classe {@code Poi} representa o mapeamento objeto-relacional da entidade de negócio armazenada na tabela 
 * {@code poi}. Esta entidade representa um ponto de interesse.
 * 
 * @author Humberto Corrêa da Silva
 *
 */
@Entity
@Table(name = Poi.TABLE_NAME)
public class Poi implements IEntity<Long> {

    public static final String TABLE_NAME = "poi";
    public static final String ID_COLUMN = "id";
    public static final String NOME_COLUMN = "nome";
    public static final String COORDENADA_X_COLUMN = "coordenada_x";
    public static final String COORDENADA_Y_COLUMN = "coordenada_y";
    
    private static final long serialVersionUID = -4221856589355702390L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long id;
    
    @Column(name = NOME_COLUMN)
    private String nome;
    
    @Column(name = COORDENADA_X_COLUMN)
    private int x;
    
    @Column(name = COORDENADA_Y_COLUMN)
    private int y;
    
    /**
     * Construtor padrão.
     */
    public Poi() {}
    
    /**
     * Construtor com parâmetros.
     * 
     * @param id Identificador do POI.
     * @param nome Nome do POI.
     * @param x Coordenada X do POI.
     * @param y Coordenada Y do POI.
     */
    public Poi(Long id, String nome, int x, int y) {
        this.id = id;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Poi)) {
            return false;
        }

        final Poi other = (Poi) obj;
        return Objects.equals(id, other.id);
    }
    
}
