package nagarciah.spring.rest.test.model;

public class Greeting {

	private long id;
	private String content;

	// Requerido para conversion JSON->Obj
	public Greeting() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setContent(String content) {
		this.content = content;
	}
}