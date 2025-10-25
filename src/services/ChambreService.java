package services;

import connexion.MyConnexion;
import dao.Idao;
import entities.Categorie;
import entities.Chambre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreService implements Idao<Chambre> {

    private CategorieService categorieService = new CategorieService();

    @Override
    public boolean create(Chambre o) {
        String req = "INSERT INTO chambre (telephone, categorie_id) VALUES (?, ?)";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setString(1, o.getTele());
            ps.setInt(2, o.getC().getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la chambre : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Chambre o) {
        String req = "UPDATE chambre SET telephone = ?, categorie_id = ? WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setString(1, o.getTele());
            ps.setInt(2, o.getC().getId());
            ps.setInt(3, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la chambre : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Chambre o) {
        String req = "DELETE FROM chambre WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la chambre : " + e.getMessage());
        }
        return false;
    }

    @Override
    public Chambre findByid(int id) {
        String req = "SELECT * FROM chambre WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Récupérer la catégorie associée
                Categorie cat = categorieService.findByid(rs.getInt("categorie_id"));
                Chambre ch = new Chambre(
                        rs.getString("telephone"),
                        cat
                );
                ch.setId(rs.getInt("id"));
                return ch;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la chambre : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Chambre> findAll() {
        List<Chambre> chambres = new ArrayList<>();
        String req = "SELECT * FROM chambre";
        try (Statement st = MyConnexion.getCnx().createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Categorie cat = categorieService.findByid(rs.getInt("categorie_id"));
                Chambre ch = new Chambre(
                        rs.getString("telephone"),
                        cat
                );
                ch.setId(rs.getInt("id"));
                chambres.add(ch);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des chambres : " + e.getMessage());
        }
        return chambres;
    }
}
