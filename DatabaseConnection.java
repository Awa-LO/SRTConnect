package gestion_diplome_srt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() throws SQLException {
       
    }

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            

            String url = "jdbc:mysql://localhost:3306/mabase";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion à la base de données réussie.");

            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erreur : Pilote JDBC non trouvé.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la connexion à la base de données.");
            return null;
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public String closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Déconnexion de la base de données réussie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la déconnexion de la base de données.");
        }
        return "Diplômé non trouvé.";
    }

public static void main(String[] args) {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.connect();

        if (connection != null) {
            System.out.println("La connexion à la base de données a réussi.");
            
        } else {
            System.out.println("La connexion à la base de données a échoué.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Une erreur SQL s'est produite.");
    }
}
}
