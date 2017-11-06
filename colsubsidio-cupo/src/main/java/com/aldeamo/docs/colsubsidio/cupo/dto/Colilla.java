package com.aldeamo.docs.colsubsidio.cupo.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Colilla extends CupoFragmento implements Serializable {

	private static final long serialVersionUID = 7088719001950191727L;
	
	String fechaFacturacion;
	String fechaLimitePago; 
	String numeroTarjeta;
	String nombre;       
	long numeroExtracto;
	String 	filer1;
	String 	numeroTarjetaLargo;
	
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	public String getFechaLimitePago() {
		return fechaLimitePago;
	}
	public void setFechaLimitePago(String fechaLimitePago) {
		this.fechaLimitePago = fechaLimitePago;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getNumeroExtracto() {
		return numeroExtracto;
	}
	public void setNumeroExtracto(long numeroExtracto) {
		this.numeroExtracto = numeroExtracto;
	}
	public String getFiler1() {
		return filer1;
	}
	public void setFiler1(String filer1) {
		this.filer1 = filer1;
	}
	public String getNumeroTarjetaLargo() {
		return numeroTarjetaLargo;
	}
	public void setNumeroTarjetaLargo(String numeroTarjetaLargo) {
		this.numeroTarjetaLargo = numeroTarjetaLargo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Colilla [fechaFacturacion=");
		builder.append(fechaFacturacion);
		builder.append(", fechaLimitePago=");
		builder.append(fechaLimitePago);
		builder.append(", numeroTarjeta=");
		builder.append(numeroTarjeta);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", numeroExtracto=");
		builder.append(numeroExtracto);
		builder.append(", filer1=");
		builder.append(filer1);
		builder.append(", numeroTarjetaLargo=");
		builder.append(numeroTarjetaLargo);
		builder.append(", codRegistro=");
		builder.append(codRegistro);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}
}
