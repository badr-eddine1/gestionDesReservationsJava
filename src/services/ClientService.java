package services;

import connexion.MyConnexion;
import dao.Idao;
import entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements Idao<Client> {

    @Override
    public boolean create(Client o) {
        String req = "INSERT INTO client (nom, prenom, telephone, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getTelephone());
            ps.setString(4, o.getEmail());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(" Erreur lors de l'insertion du client : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Client o) {
        String req = "UPDATE client SET nom = ?, prenom = ?, telephone = ?, email = ? WHERE id = ?";

        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getTelephone());
            ps.setString(4, o.getEmail());
            ps.setInt(5, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(" Erreur lors de la mise à jour du client : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Client o) {
        String req = "DELETE FROM client WHERE id = ?";

        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du client : " + e.getMessage());
        }
        return false;
    }

    @Override
    public Client findByid(int id) {
        String req = "SELECT * FROM client WHERE id = ?";

        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println(" Erreur lors de la recherche du client : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String req = "SELECT * FROM client";

        try (Statement st = MyConnexion.getCnx().createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                Client c = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone"),
                        rs.getString("email")
                );
                clients.add(c);
            }

        } catch (SQLException e) {
            System.out.println(" Erreur lors du chargement de la liste des clients : " + e.getMessage());
        }

        return clients;
    }
}
