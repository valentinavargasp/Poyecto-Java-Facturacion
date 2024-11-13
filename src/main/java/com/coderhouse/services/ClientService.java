package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Client;
import com.coderhouse.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	}

	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Transactional
	public Client updateClient(Long id, Client clientDetails) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

		client.setName(clientDetails.getName());
		client.setLastname(clientDetails.getLastname());

		if (clientDetails.getDocnumber() != null && !clientDetails.getDocnumber().isEmpty()) {
			client.setDocnumber(clientDetails.getDocnumber());
		}

		return clientRepository.save(client);
	}

	public void deleteClient(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clientRepository.deleteById(id);
	}

}
