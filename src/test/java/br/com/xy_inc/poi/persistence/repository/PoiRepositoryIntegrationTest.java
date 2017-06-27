/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi.persistence.repository;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.xy_inc.poi.domain.entity.Poi;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PoiRepositoryIntegrationTest {

    @Autowired
    private IPoiRepository poiRepository;
    
    @Test
    public void testFindByPontoReferenciaAndDistanciaMaxima() throws Exception {
        
        Poi lanchonete = new Poi(null, "Lanchonete", 27, 12);
        Poi posto = new Poi(null, "Posto", 31, 18);
        Poi joalheria = new Poi(null, "Joalheria", 15, 12);
        Poi floricultura = new Poi(null, "Floricultura", 19, 21);
        Poi pub = new Poi(null, "Pub", 12, 8);
        Poi supermercado = new Poi(null, "Supermercado", 23, 6);
        Poi churrascaria = new Poi(null, "Churrascaria", 28, 2);
        poiRepository.save(Arrays.asList(lanchonete, posto, joalheria, floricultura, pub, supermercado, churrascaria));
        
        List<Poi> poiList = poiRepository.findByPontoReferenciaAndDistanciaMaxima(20, 10, 10);
        assertTrue("A lista deve conter quatro elementos", poiList.size() == 4);
        assertTrue(poiList.get(0).getNome().equals("Lanchonete"));
        assertTrue(poiList.get(1).getNome().equals("Joalheria"));
        assertTrue(poiList.get(2).getNome().equals("Pub"));
        assertTrue(poiList.get(3).getNome().equals("Supermercado"));
    }
    
}
