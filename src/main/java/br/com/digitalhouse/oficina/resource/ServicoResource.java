package br.com.digitalhouse.oficina.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digitalhouse.oficina.model.Servico;
import br.com.digitalhouse.oficina.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {
	
	private final ServicoService servicoService;

	@Autowired
	public ServicoResource(ServicoService servicoService) {
		this.servicoService = servicoService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Servico servico){
		
		servico = this.servicoService.create(servico);
		
		URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(servico.getId())
				 .toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Servico servico){
		servico.setId(id);
		
		this.servicoService.update(servico);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servico> findById(@PathVariable Long id){
		
		Servico servico = this.servicoService.findById(id);
		
		return ResponseEntity.ok(servico);
	}
	
	@GetMapping
	public ResponseEntity<List<Servico>> findAll(){
		
		List<Servico> servico = this.servicoService.findAll();
		
		return ResponseEntity.ok(servico);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.servicoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}	

}