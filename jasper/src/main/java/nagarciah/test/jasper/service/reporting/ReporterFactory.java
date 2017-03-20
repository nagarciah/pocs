package nagarciah.test.jasper.service.reporting;

import nagarciah.test.jasper.service.reporting.impl.JasperReporterFactory;

/**
 * Clase que permite obtener una referencia a un proveedor de reportes. Implementa
 * el patr�n <a href="https://en.wikipedia.org/wiki/Abstract_factory_pattern">Abstract Factory</a> 
 * en conjunto con la clase {@link Reporter}.
 * 
 * Para implementar un nuevo proveedor de reportes, se debe crear una clase que lo represente
 * y que implemente la interfaz {@link Reporter}. Tambi�n se debe crear una subclase de {@link ReporterFactory}
 * que sobreescriba el m�todo abstracto {@link createReporter} para obtener una instancia 
 * del {@link Reporter} correspondiente al proveedor de reportes.
 * 
 * @author Nelson Garc�a
 */
public abstract class ReporterFactory {
	
	/** Enumeraci�n que lista los proveedores de reportes soportados */
	public static enum ReporterType {
		JASPER_REPORTS,
		APACHE_POI,
		JXLS
	}
	
	
	/** Proveedor de reporets por defecto */
	public final static ReporterType DEFAULT_REPORTER_TYPE = ReporterType.JASPER_REPORTS;
	
	private static ReporterType reporterType = DEFAULT_REPORTER_TYPE;

	
	/** 
	 * M�todo abstracto que debe implementar cualquier Factory para un nuevo proveedor de 
	 * reportes, siguiendo el patr�n Abstract Factory
	 */
	public abstract Reporter createReporter();

	
	/** 
	 * Cambia el proveedor de reportes por defecto. Cualquier llamado posterior a 
	 * {@link createDefaultReporter} devolver� una instancia de este tipo de proveedor 
	 * de reportes.
	 */
	public static synchronized void setDefaultReporterType(ReporterType type) {
		if(type == null) {
			throw new ReporterException("Tipo de reporteador no v�lido: null");
		}
		reporterType = type;
	}
	
	
	/** Retorna una instancia del proveedor de reportes por defecto */
	public static synchronized Reporter createDefaultReporter() {
		return createReporter(reporterType);
	}
	
	/** 
	 * Retorna una instancia del proveedor de reportes indicado por el par�metro {@code type}
	 * @param type Tipo de proveedor de reportes del cual se quiere obtener una instancia
	 */
	public static synchronized Reporter createReporter(ReporterType type) {
		switch(type) {
		case JASPER_REPORTS:
			return new JasperReporterFactory().createReporter();
		default:
			throw new ReporterException("Tipo de reporteador por defecto, no v�lido: " + reporterType);
		}
	}

}
