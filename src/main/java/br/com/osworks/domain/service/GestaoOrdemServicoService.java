package br.com.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.osworks.domain.exception.EntidadeNaoEncontradaExcpetion;
import br.com.osworks.domain.exception.NegocioException;
import br.com.osworks.domain.model.Cliente;
import br.com.osworks.domain.model.Comentario;
import br.com.osworks.domain.model.OrdemServico;
import br.com.osworks.domain.model.StatusOrdem;
import br.com.osworks.domain.repository.ClienteRepository;
import br.com.osworks.domain.repository.ComentarioRepository;
import br.com.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId()).
				orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdem.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
		
	}
	
	public void finalizar(Long ordemServicoId) {
		
		OrdemServico ordemServico = buscar(ordemServicoId);
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		
		OrdemServico ordemServico = buscar(ordemServicoId);
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
		
	}
	
	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcpetion("Ordem de Serviço não encontrada"));
	}

}
