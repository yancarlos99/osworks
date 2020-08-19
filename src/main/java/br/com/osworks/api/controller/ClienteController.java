package br.com.osworks.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping ("/clientes")
	public List<Cliente> listar() {
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		lista.add(cliente1);
		lista.add(cliente2);
		return Arrays.asList(cliente1, cliente2);
	}
}
