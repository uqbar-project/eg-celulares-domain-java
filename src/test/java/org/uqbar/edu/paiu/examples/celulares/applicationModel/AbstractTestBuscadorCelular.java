package org.uqbar.edu.paiu.examples.celulares.applicationModel;

import org.junit.Before;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.edu.paiu.examples.celulares.domain.ModeloCelular;
import org.uqbar.edu.paiu.examples.celulares.home.RepositorioModelos;

public class AbstractTestBuscadorCelular {
	protected BuscadorCelular searcher;

	@Before
	public void init() {
		searcher = new BuscadorCelular();
		searcher.setNombre("Dodi");
		ApplicationContext.getInstance().configureSingleton(ModeloCelular.class, new RepositorioModelos());
	}
}
