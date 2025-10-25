package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class MyConnexion {
    private static final String URL="jdbc:mysql://localhost:3306/GestionDesReservation";
    private static final String user="root";
    private static final String password="";
    private static Connection cnx;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("erreur lors du chargement ");
        }
        try {
            cnx= DriverManager.getConnection(URL,user,password);
        } catch (SQLException e) {
            System.out.println("erreur lors de la connexion");
        }
    }

    public static Connection getCnx() {
        return cnx;
    }
}
