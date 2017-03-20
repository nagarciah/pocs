package nagarciah.test.jasper.service.reporting;

import java.util.List;
import java.util.Map;

/**
 * Interfaz que representa a un proveedor de reportes. Implementa el patr�n 
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">Fluent Interface o Fluent API</a>
 * propuesto por Martin Fowler
 * 
 * @author Nelson Garc�a
 */
public interface Reporter {
	
	/**
	 * Listado de posibles formatos para exportar el reporte. Si un proveedore de reportes no soporta el formato indicado,
	 * arrojar� una excepci�n de tipo {@link ReporterException}
	 */
	public static enum ExportFormat {
		XLS,
		PDF,
		HTML,
		CVS,
		XML
	};
	
	/** 
	 * Agrega un par�metro al mapa de par�mteros del proveedor de reportes 
	 */
	public Reporter addParam(String key, Object value);
	
	/** 
	 * Devuelve una referencia al mapa de par�metros del proveedor de reportes 
	 */
	public Map<String, Object> getParamsMap();
	
	/** 
	 * Establece los datos a presentar en el reporte. Es una lista de objetos que cumplan el est�ndard JavaBeans (POJO)
	 */
	public Reporter setData(List<? extends Object> data);
	
	/** 
	 * Establece la ruta o nombre (depende del proveedor de reportes) de la plantilla del reporte, si el proveedor de reportes utiliza plantillas 
	 */
	public Reporter setTemplateFileName(String templateFileName);
	
	
	/** 
	 * Establece la ruta o nombre (depende del proveedor de reportes) del archivo que genere el m�todo {@code export}. 
	 * Algunos proveedores de reportes podr�an no ofrecer la funcioanlidad de exportaci�n de archivos.
	 * 
	 * @see export
	 */
	public Reporter setOutputFileName(String outputFileName);
	
	
	/** 
	 * Establece el formato del reporte que se exportar�. Un proveedor de reportes podr�a soportar s�lo algunos
	 * formatos. Si el m�todo {@code export} se invoca con un formato de reporte no soportado, lanzar� una {@link ReporterException}
	 * 
	 * @see export
	 */	
	public Reporter setExportFormat(ExportFormat format);
	
	
	/**
	 * Exporta los datos del reporte, previamente establecidos con {@link setData}, al formato indicado con {@link setExportFormat}, 
	 * volcando el resultado en el archivo se�alado por {@link setOutputFileName}. Si el proveedor usa plantillas, la plantilla se puede
	 * establecer con {@link setTemplateFileName}.
	 * 
	 * @see setData
	 * @see setExportFormat
	 * @see setOutputFileName
	 * @see setTemplateFileName
	 */
	public void export();
	
	
	/** 
	 * Exporta los datos del reporte, utilizando los valores suministrados, en lugar de usar los valores establecidos con los m�todos {@code set...} 
	 */
	public void export(ExportFormat format, String outputFileName);
	
	
	/** 
	 * Exporta los datos del reporte, utilizando los valores suministrados, en lugar de usar los valores establecidos con los m�todos {@code set...} *
	 */
	public void export(List<? extends Object> data, ExportFormat format, String outputFileName);
	
	
	/** 
	 * Exporta los datos del reporte, utilizando los valores suministrados, en lugar de usar los valores establecidos con los m�todos {@code set...} 
	 */
	public void export(List<? extends Object> data, ExportFormat format, String outputFileName, String templateFileName);
}
