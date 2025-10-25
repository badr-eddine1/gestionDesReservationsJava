package services;

import connexion.MyConnexion;
import dao.Idao;
import entities.Chambre;
import entities.Client;
import entities.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService implements Idao<Reservation> {

    private ChambreService chambreService = new ChambreService();
    private ClientService clientService = new ClientService();

    @Override
    public boolean create(Reservation o) {
        String req = "INSERT INTO reservation (datedebut, datefin, chambre_id, client_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setDate(1, new java.sql.Date(o.getDatedebut().getTime()));
            ps.setDate(2, new java.sql.Date(o.getDatefing().getTime()));
            ps.setInt(3, o.getChambre().getId());
            ps.setInt(4, o.getClient().getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la réservation : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Reservation o) {
        String req = "UPDATE reservation SET datedebut = ?, datefin = ?, chambre_id = ?, client_id = ? WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setDate(1, new java.sql.Date(o.getDatedebut().getTime()));
            ps.setDate(2, new java.sql.Date(o.getDatefing().getTime()));
            ps.setInt(3, o.getChambre().getId());
            ps.setInt(4, o.getClient().getId());
            ps.setInt(5, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la réservation : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Reservation o) {
        String req = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la réservation : " + e.getMessage());
        }
        return false;
    }

    @Override
    public Reservation findByid(int id) {
        String req = "SELECT * FROM reservation WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Chambre chambre = chambreService.findByid(rs.getInt("chambre_id"));
                Client client = clientService.findByid(rs.getInt("client_id"));
                Reservation r = new Reservation(
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        chambre,
                        client
                );
                r.setId(rs.getInt("id"));
                return r;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la réservation : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        String req = "SELECT * FROM reservation";
        try (Statement st = MyConnexion.getCnx().createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Chambre chambre = chambreService.findByid(rs.getInt("chambre_id"));
                Client client = clientService.findByid(rs.getInt("client_id"));
                Reservation r = new Reservation(
                        rs.getDate("datedebut"),
                        rs.getDate("datefin"),
                        chambre,
                        client
                );
                r.setId(rs.getInt("id"));
                reservations.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
        return reservations;
    }
}
