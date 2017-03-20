public class MyRow {
	
	String image;
	String text;
	String file;
	
	
	public MyRow(String image, String text) {
		super();
		this.image = image;
		this.text = text;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyRow [image=").append(image).append(", text=").append(text).append(", file=").append(file)
				.append("]");
		return builder.toString();
	}
}
