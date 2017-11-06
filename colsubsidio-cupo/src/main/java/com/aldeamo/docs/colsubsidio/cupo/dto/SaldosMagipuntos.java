package com.aldeamo.docs.colsubsidio.cupo.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SaldosMagipuntos extends CupoFragmento implements Serializable {

	private static final long serialVersionUID = -5238960668131749578L;

	double saldoInicial;
	double abonosMes;
	double utilizadosMes;
	double devoluciones;
	double vencidos;
	double totalBacs;
	String filer1;
	String filer2;
	
	public double getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public double getAbonosMes() {
		return abonosMes;
	}
	public void setAbonosMes(double abonosMes) {
		this.abonosMes = abonosMes;
	}
	public double getUtilizadosMes() {
		return utilizadosMes;
	}
	public void setUtilizadosMes(double utilizadosMes) {
		this.utilizadosMes = utilizadosMes;
	}
	public double getDevoluciones() {
		return devoluciones;
	}
	public void setDevoluciones(double devoluciones) {
		this.devoluciones = devoluciones;
	}
	public double getVencidos() {
		return vencidos;
	}
	public void setVencidos(double vencidos) {
		this.vencidos = vencidos;
	}
	public double getTotalBacs() {
		return totalBacs;
	}
	public void setTotalBacs(double totalBacs) {
		this.totalBacs = totalBacs;
	}
	public String getFiler1() {
		return filer1;
	}
	public void setFiler1(String filer1) {
		this.filer1 = filer1;
	}
	public String getFiler2() {
		return filer2;
	}
	public void setFiler2(String filer2) {
		this.filer2 = filer2;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaldosMagipuntos [saldoInicial=");
		builder.append(saldoInicial);
		builder.append(", abonosMes=");
		builder.append(abonosMes);
		builder.append(", utilizadosMes=");
		builder.append(utilizadosMes);
		builder.append(", devoluciones=");
		builder.append(devoluciones);
		builder.append(", vencidos=");
		builder.append(vencidos);
		builder.append(", totalBacs=");
		builder.append(totalBacs);
		builder.append(", filer1=");
		builder.append(filer1);
		builder.append(", filer2=");
		builder.append(filer2);
		builder.append(", codRegistro=");
		builder.append(codRegistro);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}
}
