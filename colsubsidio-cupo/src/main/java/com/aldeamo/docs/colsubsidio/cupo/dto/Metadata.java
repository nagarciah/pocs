package com.aldeamo.docs.colsubsidio.cupo.dto;

public class Metadata {
	
	String idEjecucion;
	String nombreArchivo;
	int numeroLinea;
	String lineaOriginal;
	int numeroRegistro;

	public String getIdEjecucion() {
		return idEjecucion;
	}
	public void setIdEjecucion(String idEjecucion) {
		this.idEjecucion = idEjecucion;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public int getNumeroLinea() {
		return numeroLinea;
	}
	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
	public String getLineaOriginal() {
		return lineaOriginal;
	}
	public void setLineaOriginal(String lineaOriginal) {
		this.lineaOriginal = lineaOriginal;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Metadata [idEjecucion=");
		builder.append(idEjecucion);
		builder.append(", nombreArchivo=");
		builder.append(nombreArchivo);
		builder.append(", numeroLinea=");
		builder.append(numeroLinea);
		builder.append(", lineaOriginal=");
		builder.append(lineaOriginal);
		builder.append(", numeroRegistro=");
		builder.append(numeroRegistro);
		builder.append("]");
		return builder.toString();
	}
	public int getNumeroRegistro() {
		return numeroRegistro;
	}
	public void setNumeroRegistro(int numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
}
