import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoArchivo {

	public void escribirArchivo(String path, List<String> elementos) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(path);
			pw = new PrintWriter(fichero);
			for (String e : elementos) {
				pw.println(e);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public List<String> leerArchivo(String path) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> lista = new ArrayList<String>();

		try {
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null)
				lista.add(linea);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

}
