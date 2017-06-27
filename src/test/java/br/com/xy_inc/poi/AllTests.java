/* 
 * Copyright (c) 2017 XY Inc. http://www.xyinc.com.br
 * Este arquivo é de propriedade de XY Inc e não pode ser utilizado, distribuído ou comercializado sem autorização 
 * prévia. 
 */
package br.com.xy_inc.poi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.xy_inc.poi.persistence.repository.AllRepositoryIntegrationTests;
import br.com.xy_inc.poi.presentation.controller.AllControllerUnitTests;
import br.com.xy_inc.poi.presentation.dto.AllDTOJsonUnitTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({AllDTOJsonUnitTests.class,
    AllControllerUnitTests.class, 
    AllRepositoryIntegrationTests.class})
public class AllTests {}
