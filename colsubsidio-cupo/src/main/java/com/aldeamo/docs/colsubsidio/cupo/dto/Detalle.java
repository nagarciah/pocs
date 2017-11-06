package com.aldeamo.docs.colsubsidio.cupo.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Detalle extends CupoFragmento implements Serializable {
	
	private static final long serialVersionUID = -1930695932899075031L;

	long numeroComprobante;
	int diaTransaccion;
	int mesTransaccion;
	int annioTransaccion;
	String conceptoTransaccion;
	long valorInicialCompraAvance;
	long cargosAbonos;
	long saldoDiferir;
	int plazoOriginal;
	String cuotasPendientes; // TODO Se pasa de int a String porque algunas líneas lo omiten
	double tasaInteres;

	public long getNumeroComprobante() {
		return numeroComprobante;
	}
	public void setNumeroComprobante(long numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	public int getDiaTransaccion() {
		return diaTransaccion;
	}
	public void setDiaTransaccion(int diaTransaccion) {
		this.diaTransaccion = diaTransaccion;
	}
	public int getMesTransaccion() {
		return mesTransaccion;
	}
	public void setMesTransaccion(int mesTransaccion) {
		this.mesTransaccion = mesTransaccion;
	}
	public int getAnnioTransaccion() {
		return annioTransaccion;
	}
	public void setAnnioTransaccion(int annioTransaccion) {
		this.annioTransaccion = annioTransaccion;
	}
	public String getConceptoTransaccion() {
		return conceptoTransaccion;
	}
	public void setConceptoTransaccion(String conceptoTransaccion) {
		this.conceptoTransaccion = conceptoTransaccion;
	}
	public long getValorInicialCompraAvance() {
		return valorInicialCompraAvance;
	}
	public void setValorInicialCompraAvance(long valorInicialCompraAvance) {
		this.valorInicialCompraAvance = valorInicialCompraAvance;
	}
	public long getCargosAbonos() {
		return cargosAbonos;
	}
	public void setCargosAbonos(long cargosAbonos) {
		this.cargosAbonos = cargosAbonos;
	}
	public long getSaldoDiferir() {
		return saldoDiferir;
	}
	public void setSaldoDiferir(long saldoDiferir) {
		this.saldoDiferir = saldoDiferir;
	}
	public int getPlazoOriginal() {
		return plazoOriginal;
	}
	public void setPlazoOriginal(int plazoOriginal) {
		this.plazoOriginal = plazoOriginal;
	}
	public String getCuotasPendientes() {
		return cuotasPendientes;
	}
	public void setCuotasPendientes(String cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}
	public double getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Detalle [numeroComprobante=");
		builder.append(numeroComprobante);
		builder.append(", diaTransaccion=");
		builder.append(diaTransaccion);
		builder.append(", mesTransaccion=");
		builder.append(mesTransaccion);
		builder.append(", annioTransaccion=");
		builder.append(annioTransaccion);
		builder.append(", conceptoTransaccion=");
		builder.append(conceptoTransaccion);
		builder.append(", valorInicialCompraAvance=");
		builder.append(valorInicialCompraAvance);
		builder.append(", cargosAbonos=");
		builder.append(cargosAbonos);
		builder.append(", saldoDiferir=");
		builder.append(saldoDiferir);
		builder.append(", plazoOriginal=");
		builder.append(plazoOriginal);
		builder.append(", cuotasPendientes=");
		builder.append(cuotasPendientes);
		builder.append(", tasaInteres=");
		builder.append(tasaInteres);
		builder.append(", codRegistro=");
		builder.append(codRegistro);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}
}
