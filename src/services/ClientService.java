package services;

import java.util.ArrayList;
import java.util.List;

import dao.Idao;
import entities.Client;

public class ClientService implements Idao<Client> {
	private List<Client> clients = new ArrayList<>();

	@Override
	public boolean create(Client o) {
		return clients.add(o);
	}

	@Override
	public boolean update(Client o) {
		for (Client c : clients) {
			if (c.getId() == o.getId()) {
				c.setNom(o.getNom());
				c.setPrenom(o.getPrenom());
				c.setEmail(o.getEmail());
				c.setTelephone(o.getTelephone());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Client o) {
		return clients.remove(o);
	}

	@Override
	public Client findByid(int id) {
		for (Client c : clients) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public List<Client> findAll() {
		return new ArrayList<>(clients);
	}
}
