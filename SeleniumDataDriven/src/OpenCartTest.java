import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(value = Parameterized.class)
public class OpenCartTest {

	public static RemoteWebDriver driver;
	protected static final String BROWSER = System.getProperty("BROWSER", "firefox");

	private String titulo;

	public OpenCartTest(String titulo) {
		this.titulo = titulo;
	}

	@Before
	public void setUp() {
		setupLocalDriver();
	}

	private static void crearArchivo() {
		setupLocalDriver();
		AccesoArchivo a = new AccesoArchivo();
		PaginaPrincipal pagPrincipal = new PaginaPrincipal(driver);
		pagPrincipal.listAllProducts();
		List<String> _listaDeProductos = pagPrincipal.titleOfProducts();
		a.escribirArchivo("C:\\paramemtros_productos.txt", _listaDeProductos);
		driver.quit();

	}

	private static void setupLocalDriver() {
		if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else if (BROWSER.equals("chrome")) {
			String path = "lib/chromedriver";
			if (System.getProperty("os.name").contains("Windows")) {
				path = "lib/chromedriver.exe";
			}
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		} else {
			throw new RuntimeException("Browser type unsupported");
		}
	}

	@Parameters
	public static Iterable<Object[]> data1() {
		crearArchivo();
		AccesoArchivo a = new AccesoArchivo();
		List<String> _listaTitulos = a.leerArchivo("C:\\paramemtros_productos.txt");
		Collection<String> c = _listaTitulos;
		Collection<Object[]> co = c.stream().map(ele -> new Object[] { ele }).collect(Collectors.toList());
		return co;
	}

	@Test
	public void testVerificarTitulos() {
		PaginaPrincipal pagPrincipal = new PaginaPrincipal(driver);
		pagPrincipal.obtenerPagina();
		Buscador buscador = pagPrincipal.searchProduct(titulo);
		assertEquals(titulo, buscador.titleOfFirstProduct());
		PaginaProducto pagProducto = buscador.accederAlPrimerProducto();
		assertEquals(titulo, pagProducto.titleOfProduct());	
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
