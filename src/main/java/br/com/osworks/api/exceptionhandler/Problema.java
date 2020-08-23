package br.com.osworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problema {
	
	private Integer status;
	private OffsetDateTime datHora;
	private String titulo;
	private List<Campo> campos;

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public OffsetDateTime getDatHora() {
		return datHora;
	}
	public void setDatHora(OffsetDateTime localDate) {
		this.datHora = localDate;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Campo> getCampos() {
		return campos;
	}
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
	
	
	

}
