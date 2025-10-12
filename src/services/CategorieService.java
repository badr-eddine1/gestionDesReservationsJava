package services;

import java.util.ArrayList;
import java.util.List;

import dao.Idao;
import entities.Categorie;

public class CategorieService implements Idao<Categorie>{
	private List<Categorie> categories = new ArrayList<>();
	@Override
	public boolean create(Categorie o) {
		return categories.add(o);

	}

	@Override
	public boolean update(Categorie o) {
		for(Categorie c:categories){
			if(c.getId()==o.getId()){
				c.setCode(o.getCode());
				c.setLibelle(o.getLibelle());
				return true;

			}
		}
		return false;
	}

	@Override
	public boolean delete(Categorie o) {
		categories.remove(o);
		return false;
	}

	@Override
	public Categorie findByid(int id) {
		for(Categorie c:categories){
			if(c.getId()==id){
				return c;
			}
		}
		return null;
	}

	@Override
	public List<Categorie> findAll() {
			return new ArrayList<>(categories);

	}

}
