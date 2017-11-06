package com.aldeamo.docs.colsubsidio.cupo.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Mensajes extends CupoFragmento implements Serializable {

	private static final long serialVersionUID = 8126029358965042146L;

	private String mensaje1;
	private String mensaje2;
	private String mensaje3;
	private String mensaje4;
	

	public String getMensaje1() {
		return mensaje1;
	}
	public void setMensaje1(String mensaje1) {
		this.mensaje1 = mensaje1;
	}
	public String getMensaje2() {
		return mensaje2;
	}
	public void setMensaje2(String mensaje2) {
		this.mensaje2 = mensaje2;
	}
	public String getMensaje3() {
		return mensaje3;
	}
	public void setMensaje3(String mensaje3) {
		this.mensaje3 = mensaje3;
	}
	public String getMensaje4() {
		return mensaje4;
	}
	public void setMensaje4(String mensaje4) {
		this.mensaje4 = mensaje4;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mensajes [mensaje1=");
		builder.append(mensaje1);
		builder.append(", mensaje2=");
		builder.append(mensaje2);
		builder.append(", mensaje3=");
		builder.append(mensaje3);
		builder.append(", mensaje4=");
		builder.append(mensaje4);
		builder.append(", codRegistro=");
		builder.append(codRegistro);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}
}
