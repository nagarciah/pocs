package com.aldeamo.docs.colsubsidio.cupo.dto;

public abstract class CupoFragmento {
	
	int codRegistro;
	Metadata metadata;
	
	public int getCodRegistro() {
		return codRegistro;
	}
	public void setCodRegistro(int codRegistro) {
		this.codRegistro = codRegistro;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}
