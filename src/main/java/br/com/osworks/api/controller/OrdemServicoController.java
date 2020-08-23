package br.com.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.osworks.api.model.OrdemServicoInputModel;
import br.com.osworks.api.model.OrdemServicoModel;
import br.com.osworks.domain.model.OrdemServico;
import br.com.osworks.domain.repository.OrdemServicoRepository;
import br.com.osworks.domain.service.GestaoOrdemServicoService;


@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService; 
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInputModel ordemServicoInput) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		
		return toModel(gestaoOrdemServicoService.criar(ordemServico));
		
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar(){
		
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping ("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordem = ordemServicoRepository.findById(ordemServicoId);
		
		if(ordem.isPresent()) {
			OrdemServicoModel OrdemServicoModel = modelMapper.map(ordem.get(), OrdemServicoModel.class);
			return ResponseEntity.ok(OrdemServicoModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void finalizar(@PathVariable Long ordemServicoId) {
		gestaoOrdemServicoService.finalizar(ordemServicoId);
	}
	
	
	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}
	
	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico){
		
		return ordensServico.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
				
	}
	
	private OrdemServico toEntity(OrdemServicoInputModel ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
	

}
