import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaPrincipal extends AbstractPageObject {

	By _searchButton = By.id("search");
	String URL = "http://demo.opencart.com/";
	String URL_ALL_PRODUCTS = "http://demo.opencart.com/index.php?route=product/search&search=";

	public PaginaPrincipal(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Busca un producto en la pagina principal
	 * 
	 * @param producto
	 * @return
	 */
	public Buscador searchProduct(String producto) {
		driver.findElement(By.name("search")).click();
		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys(producto);
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
		return new Buscador(driver);

	}

	public void listAllProducts() {
		driver.get(URL_ALL_PRODUCTS);

	}

	/**
	 * Retorna todos los productos del sitio recorriendo todos los menus
	 * 
	 * @return
	 */
	public List<String> titleOfProducts() {
		List<String> listaProductos = new ArrayList<String>();
		int cantidadPaginado = driver.findElements(By.xpath("//ul[@class='pagination']//a")).size();

		for (int i = 1; i < cantidadPaginado; i++) {
			if (i != 1)
				driver.findElement(By.xpath(String.format("//ul[@class='pagination']//a[.='%1$d']", i))).click();

			List<WebElement> listaElementos = driver.findElements(By.className("product-thumb"));
			for (WebElement webElement1 : listaElementos) {
				try {
					String nombre_producto = webElement1.findElement(By.tagName("img")).getAttribute("title");
					if (!listaProductos.contains(nombre_producto) && !nombre_producto.isEmpty()
							&& nombre_producto != null) {
						listaProductos.add(nombre_producto);
					}

				} catch (Exception ex) {
				}
			}

		}
		return listaProductos;
	}

	/**
	 * Accede a la pagina principal
	 */
	public void obtenerPagina() {
		driver.get(URL);
	}

}
