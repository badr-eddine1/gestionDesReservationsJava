package services;

import connexion.MyConnexion;
import dao.Idao;
import entities.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements Idao<Categorie> {

    @Override
    public boolean create(Categorie o) {
        String req = "INSERT INTO categorie (code, libelle) VALUES (?, ?)";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setString(1, o.getCode());
            ps.setString(2, o.getLibelle());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la catégorie : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Categorie o) {
        String req = "UPDATE categorie SET code = ?, libelle = ? WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setString(1, o.getCode());
            ps.setString(2, o.getLibelle());
            ps.setInt(3, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la catégorie : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Categorie o) {
        String req = "DELETE FROM categorie WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, o.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la catégorie : " + e.getMessage());
        }
        return false;
    }

    @Override
    public Categorie findByid(int id) {
        String req = "SELECT * FROM categorie WHERE id = ?";
        try (PreparedStatement ps = MyConnexion.getCnx().prepareStatement(req)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categorie c = new Categorie(
                        rs.getString("code"),
                        rs.getString("libelle")
                );
                c.setId(rs.getInt("id"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la catégorie : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Categorie> findAll() {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM categorie";
        try (Statement st = MyConnexion.getCnx().createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Categorie c = new Categorie(
                        rs.getString("code"),
                        rs.getString("libelle")
                );
                c.setId(rs.getInt("id"));
                categories.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des catégories : " + e.getMessage());
        }
        return categories;
    }
}
