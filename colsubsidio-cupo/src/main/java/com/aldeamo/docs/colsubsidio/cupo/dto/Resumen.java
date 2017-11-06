package com.aldeamo.docs.colsubsidio.cupo.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Resumen extends CupoFragmento implements Serializable {
	
	private static final long serialVersionUID = 5537956472758422728L;

	double tasaInteresNominalImpuestos;
	double tasaInteresNominalAvances;
	double tasaInteresNominalMora;
	double tasaInteresEfectivaImpuestos;
	double tasaInteresEfectivaAvances;
	double tasaInteresEfectivaMora;
	double saldoMesAnterior;
	double consumosMes;
	double interesMora;
	double interesCorriente;
	double consumoCupoEfectivoMes1;
	double cuotaManejoSeguros;
	double abonosCredito;
	double ajusteDebito1;
	double ajusteCredito;
	double saldoMora;
	double cuotaComprasMes;
	double cuotaDiferidosAnteriores;
	double consumoCupoEfectivoMes2;  //FIXME Duplicado?
	double interesesMora;
	double interesesCorrientes;
	double cuotaManejo;
	double ajusteDebito2;  //FIXME Duplicado?
	double pagoTotal;
	double pagoMinimo;

	public double getTasaInteresNominalImpuestos() {
		return tasaInteresNominalImpuestos;
	}
	public void setTasaInteresNominalImpuestos(double tasaInteresNominalImpuestos) {
		this.tasaInteresNominalImpuestos = tasaInteresNominalImpuestos;
	}
	public double getTasaInteresNominalAvances() {
		return tasaInteresNominalAvances;
	}
	public void setTasaInteresNominalAvances(double tasaInteresNominalAvances) {
		this.tasaInteresNominalAvances = tasaInteresNominalAvances;
	}
	public double getTasaInteresNominalMora() {
		return tasaInteresNominalMora;
	}
	public void setTasaInteresNominalMora(double tasaInteresNominalMora) {
		this.tasaInteresNominalMora = tasaInteresNominalMora;
	}
	public double getTasaInteresEfectivaImpuestos() {
		return tasaInteresEfectivaImpuestos;
	}
	public void setTasaInteresEfectivaImpuestos(double tasaInteresEfectivaImpuestos) {
		this.tasaInteresEfectivaImpuestos = tasaInteresEfectivaImpuestos;
	}
	public double getTasaInteresEfectivaAvances() {
		return tasaInteresEfectivaAvances;
	}
	public void setTasaInteresEfectivaAvances(double tasaInteresEfectivaAvances) {
		this.tasaInteresEfectivaAvances = tasaInteresEfectivaAvances;
	}
	public double getTasaInteresEfectivaMora() {
		return tasaInteresEfectivaMora;
	}
	public void setTasaInteresEfectivaMora(double tasaInteresEfectivaMora) {
		this.tasaInteresEfectivaMora = tasaInteresEfectivaMora;
	}
	public double getSaldoMesAnterior() {
		return saldoMesAnterior;
	}
	public void setSaldoMesAnterior(double saldoMesAnterior) {
		this.saldoMesAnterior = saldoMesAnterior;
	}
	public double getConsumosMes() {
		return consumosMes;
	}
	public void setConsumosMes(double consumosMes) {
		this.consumosMes = consumosMes;
	}
	public double getInteresMora() {
		return interesMora;
	}
	public void setInteresMora(double interesMora) {
		this.interesMora = interesMora;
	}
	public double getInteresCorriente() {
		return interesCorriente;
	}
	public void setInteresCorriente(double interesCorriente) {
		this.interesCorriente = interesCorriente;
	}
	public double getConsumoCupoEfectivoMes1() {
		return consumoCupoEfectivoMes1;
	}
	public void setConsumoCupoEfectivoMes1(double consumoCupoEfectivoMes1) {
		this.consumoCupoEfectivoMes1 = consumoCupoEfectivoMes1;
	}
	public double getCuotaManejoSeguros() {
		return cuotaManejoSeguros;
	}
	public void setCuotaManejoSeguros(double cuotaManejoSeguros) {
		this.cuotaManejoSeguros = cuotaManejoSeguros;
	}
	public double getAbonosCredito() {
		return abonosCredito;
	}
	public void setAbonosCredito(double abonosCredito) {
		this.abonosCredito = abonosCredito;
	}
	public double getAjusteDebito1() {
		return ajusteDebito1;
	}
	public void setAjusteDebito1(double ajusteDebito1) {
		this.ajusteDebito1 = ajusteDebito1;
	}
	public double getAjusteCredito() {
		return ajusteCredito;
	}
	public void setAjusteCredito(double ajusteCredito) {
		this.ajusteCredito = ajusteCredito;
	}
	public double getSaldoMora() {
		return saldoMora;
	}
	public void setSaldoMora(double saldoMora) {
		this.saldoMora = saldoMora;
	}
	public double getCuotaComprasMes() {
		return cuotaComprasMes;
	}
	public void setCuotaComprasMes(double cuotaComprasMes) {
		this.cuotaComprasMes = cuotaComprasMes;
	}
	public double getCuotaDiferidosAnteriores() {
		return cuotaDiferidosAnteriores;
	}
	public void setCuotaDiferidosAnteriores(double cuotaDiferidosAnteriores) {
		this.cuotaDiferidosAnteriores = cuotaDiferidosAnteriores;
	}
	public double getConsumoCupoEfectivoMes2() {
		return consumoCupoEfectivoMes2;
	}
	public void setConsumoCupoEfectivoMes2(double consumoCupoEfectivoMes2) {
		this.consumoCupoEfectivoMes2 = consumoCupoEfectivoMes2;
	}
	public double getInteresesMora() {
		return interesesMora;
	}
	public void setInteresesMora(double interesesMora) {
		this.interesesMora = interesesMora;
	}
	public double getInteresesCorrientes() {
		return interesesCorrientes;
	}
	public void setInteresesCorrientes(double interesesCorrientes) {
		this.interesesCorrientes = interesesCorrientes;
	}
	public double getCuotaManejo() {
		return cuotaManejo;
	}
	public void setCuotaManejo(double cuotaManejo) {
		this.cuotaManejo = cuotaManejo;
	}
	public double getAjusteDebito2() {
		return ajusteDebito2;
	}
	public void setAjusteDebito2(double ajusteDebito2) {
		this.ajusteDebito2 = ajusteDebito2;
	}
	public double getPagoTotal() {
		return pagoTotal;
	}
	public void setPagoTotal(double pagoTotal) {
		this.pagoTotal = pagoTotal;
	}
	public double getPagoMinimo() {
		return pagoMinimo;
	}
	public void setPagoMinimo(double pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resumen [tasaInteresNominalImpuestos=");
		builder.append(tasaInteresNominalImpuestos);
		builder.append(", tasaInteresNominalAvances=");
		builder.append(tasaInteresNominalAvances);
		builder.append(", tasaInteresNominalMora=");
		builder.append(tasaInteresNominalMora);
		builder.append(", tasaInteresEfectivaImpuestos=");
		builder.append(tasaInteresEfectivaImpuestos);
		builder.append(", tasaInteresEfectivaAvances=");
		builder.append(tasaInteresEfectivaAvances);
		builder.append(", tasaInteresEfectivaMora=");
		builder.append(tasaInteresEfectivaMora);
		builder.append(", saldoMesAnterior=");
		builder.append(saldoMesAnterior);
		builder.append(", consumosMes=");
		builder.append(consumosMes);
		builder.append(", interesMora=");
		builder.append(interesMora);
		builder.append(", interesCorriente=");
		builder.append(interesCorriente);
		builder.append(", consumoCupoEfectivoMes1=");
		builder.append(consumoCupoEfectivoMes1);
		builder.append(", cuotaManejoSeguros=");
		builder.append(cuotaManejoSeguros);
		builder.append(", abonosCredito=");
		builder.append(abonosCredito);
		builder.append(", ajusteDebito1=");
		builder.append(ajusteDebito1);
		builder.append(", ajusteCredito=");
		builder.append(ajusteCredito);
		builder.append(", saldoMora=");
		builder.append(saldoMora);
		builder.append(", cuotaComprasMes=");
		builder.append(cuotaComprasMes);
		builder.append(", cuotaDiferidosAnteriores=");
		builder.append(cuotaDiferidosAnteriores);
		builder.append(", consumoCupoEfectivoMes2=");
		builder.append(consumoCupoEfectivoMes2);
		builder.append(", interesesMora=");
		builder.append(interesesMora);
		builder.append(", interesesCorrientes=");
		builder.append(interesesCorrientes);
		builder.append(", cuotaManejo=");
		builder.append(cuotaManejo);
		builder.append(", ajusteDebito2=");
		builder.append(ajusteDebito2);
		builder.append(", pagoTotal=");
		builder.append(pagoTotal);
		builder.append(", pagoMinimo=");
		builder.append(pagoMinimo);
		builder.append(", codRegistro=");
		builder.append(codRegistro);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}
}
