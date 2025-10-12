package services;

import java.util.ArrayList;
import java.util.List;

import dao.Idao;
import entities.Chambre;

public class ChambreService implements Idao<Chambre> {
	private List<Chambre> chambres = new ArrayList<>();

	@Override
	public boolean create(Chambre o) {
		return chambres.add(o);
	}

	@Override
	public boolean update(Chambre o) {
		for (Chambre c : chambres) {
			if (c.getId() == o.getId()) {
				c.setTele(o.getTele());
				c.setC(o.getC());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Chambre o) {
		return chambres.remove(o);
	}

	@Override
	public Chambre findByid(int id) {
		for (Chambre c : chambres) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public List<Chambre> findAll() {
		return new ArrayList<>(chambres);
	}
}
