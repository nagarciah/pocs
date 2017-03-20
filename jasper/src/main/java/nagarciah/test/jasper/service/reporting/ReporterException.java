package nagarciah.test.jasper.service.reporting;

public class ReporterException extends RuntimeException {

	private static final long serialVersionUID = 4738040351517006654L;

	public ReporterException() {
		super();
	}

	public ReporterException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ReporterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ReporterException(String arg0) {
		super(arg0);
	}

	public ReporterException(Throwable arg0) {
		super(arg0);
	}
}
