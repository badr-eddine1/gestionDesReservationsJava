package entities;

import java.util.Date;

public class Reservation {
	private int id;
	private Date datedebut;
	private Date datefing;
	private Chambre chambre;
	private Client client;
	private static int count=0;
	public Reservation( Date datedebut, Date datefing, Chambre chambre, Client client) {
		super();
		this.id = ++count;
		this.datedebut = datedebut;
		this.datefing = datefing;
		this.chambre = chambre;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public Date getDatefing() {
		return datefing;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public void setDatefing(Date datefing) {
		this.datefing = datefing;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", datedebut=" + datedebut +
				", datefing=" + datefing +
				", chambre=" + chambre +
				", client=" + client +
				'}' + '\n';
	}
}
