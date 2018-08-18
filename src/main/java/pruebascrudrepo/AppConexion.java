package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConexion {

	public static void main(String[] args) 
	{
	
	/*
	 * Existen varias implementaciones de la interfaz ApplicationContext de Spring, en los ejemplos vamos a trabajar con la clase ClassPathXmlApplicationContext,
	 * esta clase es la que va a crear una instancia del ApplicationContext de Spring leyendo la configuración de los Beans desde un archivo XML, 
	 * el cual ya tenenmos creado "root-context.xml"
	 */
		//Se crea una instancia del ApplicationContext con todos los Beans que estén declarados en el archivo xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		
		//Cerramos en context
		context.close();
	}

}
