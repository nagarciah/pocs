package custom.validation.test.entity;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class PersonForm {

    @Size(min=2, max=30) // Usa mensaje en messages.properties
    private String name;
    
    @NotNull(message="{age.isnull}")	// Usa mensaje en ValidationMessages.properties
    @Min(value=18, message="{age.tooYoung}")	// Usa mensaje en ValidationMessages.properties
    @Max(value=100)	// Usa mensaje global en ValidationMessages.properties (el del otro archivo esta comentado)
    private Integer age;
    
    @NotNull // TODO Usa mensaje personalizado para todos los NotNull, en ValidationMessages
    @Size(min=10, max=30, message="{invalidMessageSize}")	// Usa mensaje en ValidationMessages.properties
    private String myMessage;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }

	public String getMyMessage() {
		return myMessage;
	}

	public void setMyMessage(String myMessage) {
		this.myMessage = myMessage;
	}
}