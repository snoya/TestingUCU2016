import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaProducto extends AbstractPageObject{

	By _tituloProducto = By.xpath("//*[@id=\"content\"]/div[1]/div[2]/h1");
	
	public PaginaProducto(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Devuelve el titulo del producto mostrado
	 * @return
	 */
	public String titleOfProduct() {
		String titulo = driver.findElement(_tituloProducto).getText();
		return titulo;
	}
}
