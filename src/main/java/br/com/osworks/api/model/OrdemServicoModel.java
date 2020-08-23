package br.com.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.osworks.domain.model.StatusOrdem;

public class OrdemServicoModel {
	
	private Long id;
	
	private ClienteResumoModel cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdem status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOrdem getStatus() {
		return status;
	}
	public void setStatus(StatusOrdem status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	public ClienteResumoModel getCliente() {
		return cliente;
	}
	public void setCliente(ClienteResumoModel cliente) {
		this.cliente = cliente;
	}

	
	
	

}
