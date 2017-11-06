package com.aldeamo.docs.colsubsidio.cupo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cupo extends CupoFragmento implements Serializable {

	private static final long serialVersionUID = -2078545699545166245L;
	protected Encabezado encabezado;
	protected List<Detalle> detalle;
	protected List<Mensajes> mensajes;
	protected Resumen resumen;
	protected Colilla colilla;
	protected SaldosMagipuntos saldosMagipuntos;
	
	public Cupo(){
		this.setDetalle(new ArrayList<>());
		this.setMensajes(new ArrayList<>());
	}

	public Encabezado getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(Encabezado encabezado) {
		this.encabezado = encabezado;
	}
	public List<Detalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<Detalle> detalle) {
		this.detalle = detalle;
	}
	public List<Mensajes> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensajes> mensajes) {
		this.mensajes = mensajes;
	}
	public Resumen getResumen() {
		return resumen;
	}
	public void setResumen(Resumen resumen) {
		this.resumen = resumen;
	}
	public Colilla getColilla() {
		return colilla;
	}
	public void setColilla(Colilla colilla) {
		this.colilla = colilla;
	}
	public SaldosMagipuntos getSaldosMagipuntos() {
		return saldosMagipuntos;
	}
	public void setSaldosMagipuntos(SaldosMagipuntos saldosMagipuntos) {
		this.saldosMagipuntos = saldosMagipuntos;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cupo [encabezado=");
		builder.append(encabezado);
		builder.append(", detalle=");
		builder.append(detalle);
		builder.append(", mensajes=");
		builder.append(mensajes);
		builder.append(", resumen=");
		builder.append(resumen);
		builder.append(", colilla=");
		builder.append(colilla);
		builder.append(", saldosMagipuntos=");
		builder.append(saldosMagipuntos);
		builder.append(", codRegistro=");
		builder.append(codRegistro);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}

}
