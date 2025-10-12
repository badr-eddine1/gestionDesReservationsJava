package entities;

import java.util.List;

public class Chambre {
	private int id;
	private String tele;
	private Categorie c;
	private static int count=0;
	public Chambre( String tele, Categorie c) {
		this.id = ++count;
		this.tele = tele;
		this.c = c;
	}

	public int getId() {
		return id;
	}

	public Categorie getC() {
		return c;
	}

	public String getTele() {
		return tele;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setC(Categorie c) {
		this.c = c;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@Override
	public String toString() {
		return "Chambre{" +
				"id=" + id +
				", tele='" + tele + '\'' +
				", c=" + c +
				'}'+'\n';
	}
}
