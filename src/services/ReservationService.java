package services;

import java.util.ArrayList;
import java.util.List;

import dao.Idao;
import entities.Reservation;

public class ReservationService implements Idao<Reservation> {
	private List<Reservation> reservations = new ArrayList<>();
	@Override
	public boolean create(Reservation o) {
		return reservations.add(o);

	}

	@Override
	public boolean update(Reservation o) {
		for(Reservation r:reservations){
			if(r.getId()==o.getId()){
				r.setDatedebut(o.getDatedebut());
				r.setDatefing(o.getDatefing());
				r.setClient(o.getClient());
				r.setChambre(o.getChambre());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Reservation o) {
		return reservations.remove(o);

	}

	@Override
	public Reservation findByid(int id) {
		for(Reservation r:reservations){
			if(r.getId()==id){
				return r;
			}
		}
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		for(Reservation r:reservations){
			return reservations;
		}
		return null;
	}

}
