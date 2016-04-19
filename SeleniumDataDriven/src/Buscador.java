import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Buscador extends AbstractPageObject {

	By _tituloProducto = By.xpath("//*[@id=\"content\"]/div[4]/div/div/div[2]/h4/a");
	public Buscador(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Obtiene el titulo del primer producto de la lista de productos encontrados segun la busqueda
	 * @return
	 */
	public String titleOfFirstProduct() {
		String titulo = driver.findElement(_tituloProducto).getText();
		return titulo;
	} 
	
	/**
	 * Accede al primer producto encontrado en la busqueda
	 * @return
	 */
	public PaginaProducto accederAlPrimerProducto(){
		driver.findElement(_tituloProducto).click();
		return new PaginaProducto(driver);
	}
	

}
