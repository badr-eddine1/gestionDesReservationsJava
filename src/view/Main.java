package view;

import entities.Categorie;
import entities.Chambre;
import entities.Client;
import entities.Reservation;

import services.CategorieService;
import services.ChambreService;
import services.ClientService;
import services.ReservationService;


import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClientService cs=new ClientService();
        CategorieService cat= new CategorieService();
        ChambreService ch=new ChambreService();
        ReservationService res = new ReservationService();
        Client c=new Client("badr","badr","09999999999","badr@gmail.com");
        cs.create(c);
        System.out.println(cs.findByid(1));
        System.out.println("categorie");
        System.out.println(cat.findByid(1));
        cat.create(new Categorie("aa11","111111"));
        ch.create(new Chambre("066666666666",new Categorie("aa11","111111")));
        Reservation R =new Reservation("01/01/01","01/01/01",new Chambre("098765123456",c),);

    }
}