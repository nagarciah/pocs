package com.aldeamo.docs.colsubsidio.cupo.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Encabezado extends CupoFragmento implements Serializable {

	private static final long serialVersionUID = 6723004988952608462L;
	private String nombre;
	private String direccion1;
	private String direccion2;
	private String ciudad;
	private String departamento;
	private String telefono;
	private String oficinaRadicacion;
	private String codigoOficina;
	private String numeroTarjeta;
	private String fechaFacturacion;
	private String fechaLimitePago;
	private long cupoTotal;
	private long cupoDisponible;
	private long cupoDisponibleEfectivo;
	private String numeroExtracto;
	private String sistemaFinanciacion;
	private int tipoTarjeta;
	private long disponibleAvances;
	private String tipoId;
	private String numeroId;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion1() {
		return direccion1;
	}
	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}
	public String getDireccion2() {
		return direccion2;
	}
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getOficinaRadicacion() {
		return oficinaRadicacion;
	}
	public void setOficinaRadicacion(String oficinaRadicacion) {
		this.oficinaRadicacion = oficinaRadicacion;
	}
	public String getCodigoOficina() {
		return codigoOficina;
	}
	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
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
	public long getCupoTotal() {
		return cupoTotal;
	}
	public void setCupoTotal(long cupoTotal) {
		this.cupoTotal = cupoTotal;
	}
	public long getCupoDisponible() {
		return cupoDisponible;
	}
	public void setCupoDisponible(long cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
	}
	public long getCupoDisponibleEfectivo() {
		return cupoDisponibleEfectivo;
	}
	public void setCupoDisponibleEfectivo(long cupoDisponibleEfectivo) {
		this.cupoDisponibleEfectivo = cupoDisponibleEfectivo;
	}
	public String getNumeroExtracto() {
		return numeroExtracto;
	}
	public void setNumeroExtracto(String numeroExtracto) {
		this.numeroExtracto = numeroExtracto;
	}
	public String getSistemaFinanciacion() {
		return sistemaFinanciacion;
	}
	public void setSistemaFinanciacion(String sistemaFinanciacion) {
		this.sistemaFinanciacion = sistemaFinanciacion;
	}
	public int getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(int tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public long getDisponibleAvances() {
		return disponibleAvances;
	}
	public void setDisponibleAvances(long disponibleAvances) {
		this.disponibleAvances = disponibleAvances;
	}
	public String getTipoId() {
		return tipoId;
	}
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	public String getNumeroId() {
		return numeroId;
	}
	public void setNumeroId(String numeroId) {
		this.numeroId = numeroId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Encabezado [nombre=");
		builder.append(nombre);
		builder.append(", direccion1=");
		builder.append(direccion1);
		builder.append(", direccion2=");
		builder.append(direccion2);
		builder.append(", ciudad=");
		builder.append(ciudad);
		builder.append(", departamento=");
		builder.append(departamento);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", oficinaRadicacion=");
		builder.append(oficinaRadicacion);
		builder.append(", codigoOficina=");
		builder.append(codigoOficina);
		builder.append(", numeroTarjeta=");
		builder.append(numeroTarjeta);
		builder.append(", fechaFacturacion=");
		builder.append(fechaFacturacion);
		builder.append(", fechaLimitePago=");
		builder.append(fechaLimitePago);
		builder.append(", cupoTotal=");
		builder.append(cupoTotal);
		builder.append(", cupoDisponible=");
		builder.append(cupoDisponible);
		builder.append(", cupoDisponibleEfectivo=");
		builder.append(cupoDisponibleEfectivo);
		builder.append(", numeroExtracto=");
		builder.append(numeroExtracto);
		builder.append(", sistemaFinanciacion=");
		builder.append(sistemaFinanciacion);
		builder.append(", tipoTarjeta=");
		builder.append(tipoTarjeta);
		builder.append(", disponibleAvances=");
		builder.append(disponibleAvances);
		builder.append(", tipoId=");
		builder.append(tipoId);
		builder.append(", numeroId=");
		builder.append(numeroId);
		builder.append(", codRegistro=");
		builder.append(codRegistro);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}

}
