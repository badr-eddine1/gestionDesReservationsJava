package view;

import entities.Categorie;
import entities.Chambre;
import entities.Client;
import entities.Reservation;
import services.CategorieService;
import services.ChambreService;
import services.ClientService;
import dao.Idao;
import services.ReservationService;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClientService cs = new ClientService();
        CategorieService cat = new CategorieService();
        ChambreService chm = new ChambreService();
        ReservationService res= new ReservationService();
        for (int i=0;i<8;i++){
            cs.create(new Client("badr"+i, "ada"+i, "066663814"+i, "abadr@gmail.com"+i));
        }

        System.out.print(cs.findAll());
        for (int i=0;i<6;i++){
            cat.create(new Categorie("11"+i,"111"+i));
        }

        System.out.println(cat.findAll());
        for (int i=0;i<6;i++){
            chm.create(new Chambre("066666666"+i,cat.findByid(i+1)));
        }
        System.out.println(chm.findAll());
        for (int i=0;i<6;i++){
            res.create(new Reservation(new Date("2022/01/01"),new Date("2022/01/01"),chm.findByid(i+1),cs.findByid(i+1)));
        }

    }
}