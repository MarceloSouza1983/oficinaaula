package br.com.digitalhouse.oficina.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.oficina.exception.DataIntegrityException;
import br.com.digitalhouse.oficina.exception.ObjectNotFoundException;
import br.com.digitalhouse.oficina.model.Servico;
import br.com.digitalhouse.oficina.repository.ServicoRepository;

@Service
public class ServicoService {

	private final ServicoRepository servicoRepository;

	@Autowired
	public ServicoService(ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}

	public Servico create(Servico servico) {
		servico.setId(null);
		return this.servicoRepository.save(servico);
	}

	public Servico update(Servico servico) {

		Servico servicoAntigo = this.findById(servico.getId());
		servicoAntigo.setDataInicio(servico.getDataInicio());
		servicoAntigo.setDataFim(servico.getDataFim());
		servicoAntigo.setValor(servico.getValor());
		servicoAntigo.setMetodoPagamento(servico.getMetodoPagamento());

		return this.servicoRepository.save(servicoAntigo);
	}

	public Servico findById(Long id) {
		Optional
		.ofNullable(id)
		.orElseThrow( () -> new DataIntegrityException("O id não pode ser nulo"));

		return this.servicoRepository.findById(id)
				.orElseThrow( () -> new ObjectNotFoundException("Não foi possivel encontrar um objeto com id " + id));
	}

	public List<Servico> findAll(){
		return this.servicoRepository.findAll();
	}

	public void deleteById(Long id) {
		this.findById(id);

		this.servicoRepository.deleteById(id);
	}

}