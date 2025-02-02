package gestion_diplome_srt;

import javax.swing.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.DefaultCaret;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class principal extends JFrame implements ActionListener {
    private JPanel zoneHaut, zoneMilieu, zoneBas;
    private JButton SAISIE, MODIFICATION_SUIVI, AFFICHAGE, RECHERCHE, VERIFICATION, EXPLOITATION;
    private JTextArea messageArea;
    private JPanel activePanel; 
    
    private JLabel label;
    private JLabel imageLabel;
    private JTextArea SRT;
    public principal() {
    	
    	
        setTitle("Gestion des Diplômés");
        setSize(800, 665);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        // Création des zones
        zoneHaut = new JPanel();
        zoneMilieu = new JPanel();
        zoneBas = new JPanel();
       
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\Gestion_diplome_srt\\doc\\image figuant dans l'interface/satic.jpg");
        Image image = imageIcon.getImage().getScaledInstance(700, 330, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(image));
       
        zoneMilieu.add(imageLabel, BorderLayout.NORTH);

        imageLabel.setVisible(true);
     
        
         SRT = new JTextArea(
                "Bienvenue dans l'interface de suivi des anciennes promotions de la licence en Systèmes, Réseaux et Télécoms (SRT) de l'université Alioune Diop de Bambey.\n\n" +
                "Cette plateforme a été créée dans le but de permettre un suivi complet et efficace des parcours académiques et professionnels de nos anciens étudiants en SRT."
                + " Vous pourrez explorer les profils, les réalisations et les trajectoires de carrière de nos diplômés, offrant ainsi une source d'inspiration et de référence pour nos étudiants actuels.\n\n" +
                "Nous sommes heureux de vous offrir cette plateforme de suivi qui, nous l'espérons, sera un outil précieux pour vous, que vous soyez un ancien étudiant cherchant à rester en contact avec votre université ou un étudiant actuel cherchant inspiration et opportunités dans le domaine des Systèmes, Réseaux et Télécoms.\n\n" +
                "Bienvenue à vous, et que cette interface soit une source de découverte et de succès pour chacun d'entre vous !"
            );
        SRT.setEditable(false);
        SRT.setWrapStyleWord(true);
        SRT.setLineWrap(true);
        SRT.setPreferredSize(new Dimension(700, 200));

           SRT.setFont(new Font("cambria Math", Font.BOLD, 12));
           zoneMilieu.add(SRT, BorderLayout.CENTER);
    
        // Ajout des zones à la fenêtre principale
        add(zoneHaut, BorderLayout.NORTH);
        add(zoneMilieu, BorderLayout.CENTER);
        add(zoneBas, BorderLayout.SOUTH);

        // Création des boutons dans la ZoneHaut
        SAISIE = new JButton("SAISIE");
        MODIFICATION_SUIVI = new JButton("MODIFICATION_SUIVI");
        AFFICHAGE = new JButton("AFFICHAGE");
        RECHERCHE = new JButton(" RECHERCHE");
        VERIFICATION = new JButton("VERIFICATION");
        EXPLOITATION = new JButton("EXPLOITATION");

        // Ajout des boutons à la ZoneHaut
        zoneHaut.add(SAISIE);
        zoneHaut.add(MODIFICATION_SUIVI);
        zoneHaut.add(AFFICHAGE);
        zoneHaut.add(RECHERCHE);
        zoneHaut.add(VERIFICATION);
        zoneHaut.add(EXPLOITATION);

        // Ajout d'un JTextArea à la ZoneBas
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false);
        zoneBas.add(new JScrollPane(messageArea));

        // Ajout d'un JLabel au ZoneMilieu
        label = new JLabel("espace de travail");
        zoneMilieu.add(label);

        // Ajout des ActionListeners aux boutons
        SAISIE.addActionListener(this);
        MODIFICATION_SUIVI.addActionListener(this);
        AFFICHAGE.addActionListener(this);
        RECHERCHE.addActionListener(this);
        VERIFICATION.addActionListener(this);
        EXPLOITATION.addActionListener(this);
        
        
        zoneMilieu.setBackground(new Color(210, 180, 140)); // Brun claire en RVB
        zoneHaut.setBackground(new Color(210, 180, 140)); // Brun Terre Cuite en RVB
        zoneBas.setBackground(new Color(210, 180, 140)); // Brun Terre Cuite en RVB
        setVisible(true);
    }

 
    public void actionPerformed(ActionEvent e) {
        if (activePanel != null) {
            zoneMilieu.remove(activePanel);
        }

        Object source = e.getSource();
        
        if (source == SAISIE) {
        	
        	  imageLabel.setVisible(false);
        	  SRT.setVisible(false);
            activePanel = new SaisiePanel();
            zoneMilieu.add(activePanel);
            zoneMilieu.setBackground(new Color(173, 216, 230)); // Bleu clair (LightBlue) en RVB
            zoneBas.setBackground(new Color(173, 216, 230));
            zoneHaut.setBackground(new Color(173, 216, 230));
            label.setText("Interface de saisie");
            List<String> motsCles = chercherMotCle(SRT.getText());
            System.out.println("Mots-clés trouvés : " + motsCles);
            messageArea.setText("Vous avez choisi le module de saisie ,\n"
            		+ " ce module est uniquement reserve aux etudiants  .");
        } else if (source == MODIFICATION_SUIVI) {
        	zoneMilieu.setBackground(new Color(255, 229, 180)); 
        	zoneBas.setBackground(new Color(255, 229, 180)); 
        	zoneHaut.setBackground(new Color(255, 229, 180)); 
        	  imageLabel.setVisible(false);
        	 SRT.setVisible(false);
            activePanel = new ModificationSuiviPanel();
            zoneMilieu.add(activePanel);
            label.setText("Interface de modification et suivi");
            messageArea.setText("Vous avez choisi le module de modification et suivi.\n"
            		+ "ce module est uniquement reserve au etudiants");
        } else if (source == AFFICHAGE) {
        	zoneMilieu.setBackground(new Color(230, 230, 250));
        	zoneBas.setBackground(new Color(230, 230, 250)); 
        	zoneHaut.setBackground(new Color(230, 230, 250)); 
        	  imageLabel.setVisible(false);
        	 SRT.setVisible(false);
            activePanel = new AffichagePanel();
            zoneMilieu.add(activePanel);
            label.setText("Interface d'affichage");
            messageArea.setText("Vous avez choisi le module d'affichage.\n"
            		+ "ce module est uniquement reserve au personnel de l'UADB");
        } else if (source == RECHERCHE) {
        	zoneBas.setBackground(new Color(255, 192, 203)); // Bleu Ciel en RVB
        	zoneMilieu.setBackground(new Color(255, 192, 203)); 

        	zoneHaut.setBackground(new Color(255, 192, 203)); // Lavande pâle en RVB


        	  imageLabel.setVisible(false);
        	 SRT.setVisible(false);
            activePanel = new RecherchePanel();
            zoneMilieu.add(activePanel);
            label.setText("Interface de recherche");
            messageArea.setText("Vous avez choisi le module de recherche.\n ce module est uniquement reserve au personnel de l'UADB");
        } else if (source == VERIFICATION) {
        	zoneMilieu.setBackground(new Color(175, 238, 238)); // Turquoise pâle en RVB
        	zoneBas.setBackground(new Color(175, 238, 238)); 
        	zoneHaut.setBackground(new Color(175, 238, 238)); 

        	  imageLabel.setVisible(false);
        	 SRT.setVisible(false);
            activePanel = new VerificationPanel();
            zoneMilieu.add(activePanel);
            label.setText("Interface de vérification");
            messageArea.setText("Vous avez choisi le module de vérification.\n"
            		+ "ce module est uniquement reserve  a la directeur de la scolarite.");

        } else if (source == EXPLOITATION) {
        	zoneMilieu.setBackground(new Color(135, 206, 250)); // Rose clair en RVB
        	zoneBas.setBackground(new Color(135, 206, 250)); // Rose clair en RVB
        	zoneHaut.setBackground(new Color(135, 206, 250)); // Rose clair en RVB

        	  imageLabel.setVisible(false);
        	 SRT.setVisible(false);
            activePanel = new ExploitationPanel();
            zoneMilieu.add(activePanel);
            label.setText("Interface d'exploitation");
            messageArea.setText("Vous avez choisi le module d'exploitation.\n"
            		+ "ce module est uniquement reserve  a la directeur de la ciaq.");
        }

        revalidate();
        repaint();
    }
    class AuthentificationPanel extends JPanel {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton;
        private JLabel imageLabel;

        public AuthentificationPanel() {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Créer le label d'authentification avec une bordure personnalisée
            JLabel titleLabel = new JLabel("Authentification");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBackground(Color.DARK_GRAY);
            titleLabel.setOpaque(true);
            titleLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(5, 5, 5, 5, Color.LIGHT_GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(titleLabel, gbc);

            usernameField = new JTextField(10);
            passwordField = new JPasswordField(10);
            loginButton = new JButton("Connexion");

            ImageIcon imageIcon = new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\Gestion_diplome_srt\\doc\\image figuant dans l'interface/R3.jpg");
            Image img = imageIcon.getImage();
            Image newImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImg);
            imageLabel = new JLabel(imageIcon);

            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Nom d'utilisateur:"), gbc);

            gbc.gridx = 1;
            add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("Mot de passe:"), gbc);

            gbc.gridx = 1;
            add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            add(loginButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            add(imageLabel, gbc);
        }

        public String getUsername() {
            return usernameField.getText();
        }

        public String getPassword() {
            return new String(passwordField.getPassword());
        }

        public void setLoginButtonActionListener(ActionListener listener) {
            loginButton.addActionListener(listener);
        }
    }

    class Saisie_info_Diplome_De_LicencePanel extends JPanel implements ActionListener {
        private JTextField idEtudiantField;  
        private JTextField nomField;
        private JTextField prenomField;
        private JTextField adresseField;
        private JTextField emailField;
        private JTextField telephoneField;
        private JButton enregistrerButton;

        public Saisie_info_Diplome_De_LicencePanel() {
            setLayout(new GridLayout(7, 2, 5, 5)); 

            idEtudiantField = new JTextField(10);  
            nomField = new JTextField(10);
            prenomField = new JTextField(10);
            adresseField = new JTextField(10);
            emailField = new JTextField(10);
            telephoneField = new JTextField(10);

            enregistrerButton = new JButton("Enregistrer");
            enregistrerButton.addActionListener(this);

            add(new JLabel("ID de l'étudiant: "));
            add(idEtudiantField);
            add(new JLabel("Nom: "));
            add(nomField);
            add(new JLabel("Prénom: "));
            add(prenomField);
            add(new JLabel("Adresse: "));
            add(adresseField);
            add(new JLabel("Email: "));
            add(emailField);
            add(new JLabel("Téléphone: "));
            add(telephoneField);
            add(new JLabel("")); 
            add(enregistrerButton);
        }

       
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enregistrerButton) {
                enregistrerDiplomeDansBaseDeDonnees();
            }
        }

        private void enregistrerDiplomeDansBaseDeDonnees() {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "INSERT INTO diplome_de_licence (IDETUDIANT, NOM, PRENOM, adresse, email, telephone) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setString(1, idEtudiantField.getText());  
                statement.setString(2, nomField.getText());
                statement.setString(3, prenomField.getText());
                statement.setString(4, adresseField.getText());
                statement.setString(5, emailField.getText());
                statement.setString(6, telephoneField.getText());

                statement.executeUpdate();

                connection.close();

                idEtudiantField.setText(""); 
                nomField.setText("");
                prenomField.setText("");
                adresseField.setText("");
                emailField.setText("");
                telephoneField.setText("");

                messageArea.setText("Enregistrement réussi !");
            } catch (SQLException ex) {
                ex.printStackTrace();
                messageArea.setText("Erreur lors de l'enregistrement : " + ex.getMessage());
            }
        }
    }

    class SaisieListeAmisPanel extends JPanel implements ActionListener {
        private JTextField nomAmiField;
        private JTextField prenomAmiField;
        private JTextField idAmisField;  

        private JButton enregistrerButton;

        public SaisieListeAmisPanel() {
            setLayout(new GridLayout(4, 2, 5, 5)); 

            nomAmiField = new JTextField(10);
            prenomAmiField = new JTextField(10);
            idAmisField = new JTextField(10);  

            enregistrerButton = new JButton("Enregistrer");
            enregistrerButton.addActionListener(this);

            add(new JLabel("ID de l'ami: "));
            add(idAmisField);  
            add(new JLabel("Nom de l'ami: "));
            add(nomAmiField);
            add(new JLabel("Prénom de l'ami: "));
            add(prenomAmiField);
            add(new JLabel(""));
            add(enregistrerButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enregistrerButton) {
                enregistrerListeAmisDansBaseDeDonnees();
            }
        }

        private void enregistrerListeAmisDansBaseDeDonnees() {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "INSERT INTO liste_amis (IDAMIS, NOM, PRENOM) VALUES (?, ?, ?)";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setString(1, idAmisField.getText());
                statement.setString(2, nomAmiField.getText());
                statement.setString(3, prenomAmiField.getText());

                statement.executeUpdate();

                connection.close();

                idAmisField.setText("");
                nomAmiField.setText("");
                prenomAmiField.setText("");

                messageArea.setText("Enregistrement réussi !");
            } catch (SQLException ex) {
                ex.printStackTrace();
                messageArea.setText("Erreur lors de l'enregistrement : " + ex.getMessage());
            }
        }
    }

   
    class SaisieInscriptionmasterPanel extends JPanel implements ActionListener {
        private JTextField IDINSCRIPTION_MASTERField;
        private JTextField niveau_masterField;
        private JTextField universite_acceuilField;  

        private JButton enregistrerButton;

        public SaisieInscriptionmasterPanel() {
            setLayout(new GridLayout(4, 2, 5, 5)); 

            IDINSCRIPTION_MASTERField = new JTextField(10);
            niveau_masterField = new JTextField(10);
            universite_acceuilField = new JTextField(10);  

            enregistrerButton = new JButton("Enregistrer");
            enregistrerButton.addActionListener(this);

            add(new JLabel(" universite d'acceuil: "));
            add(universite_acceuilField);  
            add(new JLabel("Id master: "));
            add(IDINSCRIPTION_MASTERField);
            add(new JLabel("niveau master: "));
            add(niveau_masterField);
            add(new JLabel(""));
            add(enregistrerButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enregistrerButton) {
                enregistrerinscriptionmasterDansBaseDeDonnees();
            }
        }

        private void enregistrerinscriptionmasterDansBaseDeDonnees() {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "INSERT INTO inscription_master (universite_acceuil, IDINSCRIPTION_MASTER,niveau_master ) VALUES (?, ?, ?)";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setString(1, universite_acceuilField.getText());
                statement.setString(2, IDINSCRIPTION_MASTERField.getText());
                statement.setString(3, niveau_masterField.getText());

                statement.executeUpdate();

                connection.close();

                universite_acceuilField.setText("");
                IDINSCRIPTION_MASTERField.setText("");
                niveau_masterField.setText("");

                messageArea.setText("Enregistrement réussi !");
            } catch (SQLException ex) {
                ex.printStackTrace();
                messageArea.setText("Erreur lors de l'enregistrement : " + ex.getMessage());
            }
        }
    } 
  

    class SaisieEmploiPanel extends JPanel implements ActionListener {
        private JTextField dateDebutField;
        private JTextField dateFinField;
        private JTextField entrepriseField;
        private JTextField posteOccupeField;
        private JTextField idEmploiField;
        private JButton enregistrerButton;
       

        public SaisieEmploiPanel(JTextArea messageArea) {
           
            setLayout(new GridLayout(6, 2, 5, 5));

            dateDebutField = new JTextField(10);
            
            dateFinField = new JTextField(10);
            entrepriseField = new JTextField(10);
            posteOccupeField = new JTextField(10);
            idEmploiField = new JTextField(10);

            enregistrerButton = new JButton("Enregistrer");
            enregistrerButton.addActionListener(this);

            add(new JLabel("Date de début : "));
            add(dateDebutField);
            add(new JLabel("Date de fin : "));
            add(dateFinField);
            add(new JLabel("Entreprise: "));
            add(entrepriseField);
            add(new JLabel("Poste occupé: "));
            add(posteOccupeField);
            add(new JLabel("ID Emploi: "));
            add(idEmploiField);
            add(new JLabel(""));
            add(enregistrerButton);
        }

		@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enregistrerButton) {
                enregistrerEmploiDansBaseDeDonnees();
            }
        }

        private void enregistrerEmploiDansBaseDeDonnees() {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "INSERT INTO emploi (IDEMPLOI, DATE_DEBUT, DATE_FIN, ENTREPRISE, POSTE_OCCUPE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setInt(1, Integer.parseInt(idEmploiField.getText()));
                statement.setString(2, dateDebutField.getText());
                statement.setString(3, dateFinField.getText());
                statement.setString(4, entrepriseField.getText());
                statement.setString(5, posteOccupeField.getText());

                statement.executeUpdate();

                connection.close();

                dateDebutField.setText("");
                dateFinField.setText("");
                entrepriseField.setText("");
                posteOccupeField.setText("");
                idEmploiField.setText("");

                messageArea.setText("Enregistrement réussi !");
            } catch (SQLException ex) {
                ex.printStackTrace();
                messageArea.setText("Erreur lors de l'enregistrement : " + ex.getMessage());
            }
        }
    }

    

    public class SaisieTypeDeContratPanel extends JPanel implements ActionListener {
        private JTextField IDTYPE_DE_CONTRATField;
        private JButton enregistrerButton;
        private JTextField nomContratField;

        public SaisieTypeDeContratPanel(JTextArea messageArea) {
            setLayout(new GridLayout(3, 2, 5, 5));

            IDTYPE_DE_CONTRATField = new JTextField(10); // Ajout de cette ligne
            nomContratField = new JTextField(10);

            enregistrerButton = new JButton("Enregistrer");
            enregistrerButton.addActionListener(this);

            add(new JLabel("ID Contrat: ")); 
            add(IDTYPE_DE_CONTRATField); // Utilisation de la référence IDTYPE_DE_CONTRATField
            add(new JLabel("Nom du contrat: "));
            add(nomContratField);
            add(new JLabel(""));
            add(enregistrerButton);
        }

		@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enregistrerButton) {
                enregistrerTypeDeContratDansBaseDeDonnees();
            }
        }

        private void enregistrerTypeDeContratDansBaseDeDonnees() {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "INSERT INTO type_de_contrat (IDTYPE_DE_CONTRAT, nom) VALUES (?, ?)";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, IDTYPE_DE_CONTRATField.getText());
                statement.setString(2, nomContratField.getText());
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 1) {
                    messageArea.setText("Enregistrement réussi !");
                } else {
                    messageArea.setText("Erreur lors de l'enregistrement. Aucune ligne affectée.");
                }

                connection.close();
                IDTYPE_DE_CONTRATField.setText("");
                nomContratField.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
                messageArea.setText("Erreur lors de l'enregistrement : " + ex.getMessage());
            }
        }
    }


    public class SaisieAnneeUniversitairePanel extends JPanel implements ActionListener {
       private JTextField IDANNEE_UNIVERSITAIREField;
    	private JTextField dateDebutField;
        private JTextField dateFinField;
        private JTextField nomDiplomeField;
        private JTextField etablissementAcceuilField;
        private JButton enregistrerButton;

        public SaisieAnneeUniversitairePanel() {
            setLayout(new GridLayout(6, 2, 5, 5)); 

            dateDebutField = new JTextField(10);
            dateFinField = new JTextField(10);
            nomDiplomeField = new JTextField(10);
            etablissementAcceuilField = new JTextField(10);
            IDANNEE_UNIVERSITAIREField= new JTextField(10);
            enregistrerButton = new JButton("Enregistrer");
            enregistrerButton.addActionListener(this);
            
            
            
            add(new JLabel("ID annee universitaire: "));
            add( IDANNEE_UNIVERSITAIREField);
            add(new JLabel("Date de début: "));
            add(dateDebutField);
            add(new JLabel("Date de fin: "));
            add(dateFinField);
            add(new JLabel("Nom du diplôme: "));
            add(nomDiplomeField);
            add(new JLabel("Etablissement d'accueil: "));
            add(etablissementAcceuilField);
            add(new JLabel(""));
            add(enregistrerButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enregistrerButton) {
                enregistrerAnneeUniversitaireDansBaseDeDonnees();
            }
        }

        private void enregistrerAnneeUniversitaireDansBaseDeDonnees() {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "INSERT INTO annee_universitaire ( IDANNEE_UNIVERSITAIRE , DATE_DE_DEBUT, DATE_DE_FIN, nom_diplome, etablissement_acceuil) VALUES (?,?, ?, ?, ?)";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setString(1, IDANNEE_UNIVERSITAIREField.getText());
                statement.setDate(2, Date.valueOf(dateDebutField.getText()));
                statement.setDate(3, Date.valueOf(dateFinField.getText()));
                statement.setString(4, nomDiplomeField.getText());
                statement.setString(5, etablissementAcceuilField.getText());

                statement.executeUpdate();

                connection.close();
                IDANNEE_UNIVERSITAIREField.setText("");
                dateDebutField.setText("");
                dateFinField.setText("");
                nomDiplomeField.setText("");
                etablissementAcceuilField.setText("");

               
                 messageArea.setText("Enregistrement réussi !");
            } catch (SQLException ex) {
                ex.printStackTrace();
                
                 messageArea.setText("Erreur lors de l'enregistrement : " + ex.getMessage());
            }
        }
    }

             


    class SaisiePanel extends JPanel {
        private JTabbedPane onglets;
        private AuthentificationPanel authPanel;
        private Saisie_info_Diplome_De_LicencePanel diplomePanel;
        private SaisieListeAmisPanel listeAmisPanel;
        private SaisieEmploiPanel emploiPanel;
        private SaisieTypeDeContratPanel typeContratPanel;
        private SaisieAnneeUniversitairePanel anneeUniversitairePanel;
        private SaisieInscriptionmasterPanel masterpanel;
        

            public SaisiePanel() {
                setLayout(new BorderLayout());

                authPanel = new AuthentificationPanel();
                add(authPanel, BorderLayout.CENTER);

                authPanel.setLoginButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = authPanel.getUsername();
                        String password = authPanel.getPassword();

                        if (isValidCredentials(username, password)) {
                            remove(authPanel); // Retire le panneau d'authentification
                            add(createSaisieContent(), BorderLayout.CENTER); // Ajoute le contenu de saisie
                            validate(); // Valide le composant parent pour mise à jour de l'affichage
                        } else {
                        	messageArea.setText( "Identifiants incorrects. Veuillez réessayer.");
                        }
                    }
                });
            }

            private JPanel createSaisieContent() {
                JTabbedPane onglets = new JTabbedPane();
                Saisie_info_Diplome_De_LicencePanel diplomePanel = new Saisie_info_Diplome_De_LicencePanel();
                SaisieListeAmisPanel listeAmisPanel = new SaisieListeAmisPanel();
                SaisieEmploiPanel emploiPanel = new SaisieEmploiPanel(messageArea);
                SaisieTypeDeContratPanel typeContratPanel = new SaisieTypeDeContratPanel(messageArea);
                SaisieAnneeUniversitairePanel anneeUniversitairePanel = new SaisieAnneeUniversitairePanel();
                SaisieInscriptionmasterPanel masterpanel = new SaisieInscriptionmasterPanel();

                onglets.addTab("Diplôme de licence", diplomePanel);
                onglets.addTab("Liste d'amis", listeAmisPanel);
                onglets.addTab("Emploi", emploiPanel);
                onglets.addTab("Type de contrat", typeContratPanel);
                onglets.addTab("Année universitaire", anneeUniversitairePanel);
                onglets.addTab("Inscription master", masterpanel);

                JPanel saisieContent = new JPanel(new BorderLayout());
                saisieContent.add(onglets, BorderLayout.CENTER);

               
                return saisieContent;
                }
           
            private boolean isValidCredentials(String username, String password) {
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                // Vérifie si le username ne dépasse pas 15 caractères
                if (username.length() > 15) {
                    System.out.println("Nom d'utilisateur trop long");
                    return false;
                }

                // Vérifie si le username contient uniquement des lettres alphabétiques et des espaces
                if (!username.matches("[a-zA-Z ]+")) {
                    System.out.println("Nom d'utilisateur invalide");
                    return false;
                }

                // Vérifie si le password est composé uniquement de chiffres et s'il a entre 1 et 7 chiffres
                if (!password.matches("\\d{1,7}")) {
                    System.out.println("Mot de passe invalide");
                    return false;
                }

                // Vérifie si le premier chiffre du password est compris entre 2 et 16
                String firstTwoDigits = password.substring(0, 2);
                int firstTwoDigitsValue = Integer.parseInt(firstTwoDigits);
               
                if (firstTwoDigitsValue <= 10 || firstTwoDigitsValue >= 16) {
                    System.out.println("Premier chiffre du mot de passe invalide");
                    return false;
                }

                // Si toutes les conditions sont remplies, l'authentification est réussie
                return true;
            }



            public static void main(String[] args) {
                JFrame frame = new JFrame("Application de Saisie");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                principal saisiePanel = new principal();
                frame.add(saisiePanel);
                frame.setVisible(true);
            }
        }

    



    public class ModificationSuiviPanel extends JPanel implements ActionListener {
        private JComboBox<String> selectionElementComboBox;
        private JTextField idEtudiantField;
        private JButton modifierButton;
        private JComboBox<String> champAModifierComboBox;
        private JLabel imageLabel = new JLabel();
        private JTextField nomUtilisateurField;
        private JPasswordField motDePasseField;
        private JButton seConnecterButton;
        private JButton motDePasseOublieButton;
        private boolean authentificationReussie = false;

        public ModificationSuiviPanel() {
        	

        	setLayout(new GridLayout(4, 2, 10, 10));

        	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Alignement vertical
        	
            setForeground(new Color(47, 79, 79)); // Gris foncé pour le texte
           
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            selectionElementComboBox = new JComboBox<>();
            idEtudiantField = new JTextField(10);
            modifierButton = new JButton("Modifier");
            champAModifierComboBox = new JComboBox<>();

            modifierButton.setPreferredSize(new Dimension(150, 40));
            modifierButton.setFont(new Font("Arial", Font.PLAIN, 14));

            modifierButton.addActionListener(this);

            add(createLabel("Sélectionner une table: "));
            add(selectionElementComboBox);
            add(createLabel("ID de la table: "));
            add(idEtudiantField);
            add(createLabel("Champ à modifier: "));
            add(champAModifierComboBox);
            add(new JLabel(""));
            add(modifierButton);

            ImageIcon imageIcon = new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\Gestion_diplome_srt\\doc\\image figuant dans l'interface/R2.jpg");
            Image image = imageIcon.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(image));
            add(imageLabel,BorderLayout.NORTH);
            add(Box.createVerticalGlue());
            add(imageLabel);
            add(Box.createVerticalGlue());

            String[] tables = {"diplome_de_licence", "liste_amis", "emploi", "type_de_contrat", "annee_universitaire", "inscription_master"};
            for (String table : tables) {
                selectionElementComboBox.addItem(table);
            }

            String[] elementsSaisiePanel = {"IDETUDIANT", "Nom", "Prenom", "Adresse", "Telephone", "Email",
                    "IDAMIS", "Nom ", "Prenom ","niveau_master" ,"IDINSCRIPTION_MASTER",
                    "universite_acceuil","IDANNEE_UNIVERSITAIRE","DATE_DE_DEBUT","DATE_DE_FIN",	"DATE_DEBUT" ,"nom_diplome",
                    "etablissement_acceuil","IDTYPE_DE_CONTRAT","nom","IDEMPLOI", "ENTREPRISE","POSTE_OCCUPE","DATE_FIN"};
            for (String element : elementsSaisiePanel) {
                champAModifierComboBox.addItem(element);
            }

            // Ajout des éléments d'authentification
            add(createLabel("Nom d'utilisateur:"));
            nomUtilisateurField = new JTextField(10);
            add(nomUtilisateurField);

            add(createLabel("Mot de passe:"));
            motDePasseField = new JPasswordField(10);
            add(motDePasseField);

            seConnecterButton = new JButton("Se connecter");
            seConnecterButton.addActionListener(this);
            add(seConnecterButton);
        
     // Ajout du bouton "Mot de passe oublié"
        motDePasseOublieButton = new JButton("Mot de passe oublié");
        motDePasseOublieButton.addActionListener(this);
        add(motDePasseOublieButton);
    }
        @Override
        public void actionPerformed(ActionEvent e) {
        	 if (e.getSource() == motDePasseOublieButton) {
        	        actionMotDePasseOublie();
        	    }
        	 else if (e.getSource() == modifierButton && authentificationReussie) {
                String tableSelectionnee = (String) selectionElementComboBox.getSelectedItem();
                String idPrimaire = "";

                switch (tableSelectionnee) {
                    case "diplome_de_licence":
                        idPrimaire = "IDETUDIANT";
                        break;
                    case "emploi":
                        idPrimaire = "IDEMPLOI";
                        break;
                    case "type_de_contrat":
                        idPrimaire = "IDTYPE_DE_CONTRAT";
                        break;
                    case "liste_amis":
                        idPrimaire = "IDAMIS";
                        break;
                    case "annee_universitaire":
                        idPrimaire = "IDANNEE_UNIVERSITAIRE";
                        break;
                    case "inscription_master":
                        idPrimaire = "IDINSCRIPTION_MASTER";
                        break;
                }

                String ideTudiant = idEtudiantField.getText();
                if (ideTudiant.isEmpty()) {
                    showMessage("Veuillez entrer l'ID de la table.");
                    return;
                }

                String champAModifier = (String) champAModifierComboBox.getSelectedItem();

                String nouvelleValeur = JOptionPane.showInputDialog(this, "Entrez la nouvelle valeur:");

                if (nouvelleValeur != null) {
                    boolean modificationReussie = effectuerModificationDansBaseDeDonnees(tableSelectionnee, idPrimaire, ideTudiant, champAModifier, nouvelleValeur);
                    if (modificationReussie) {
                        messageArea.setText("Modification réussie dans la table " + tableSelectionnee + " !");
                    } else {
                    	 messageArea.setText("Erreur lors de la modification.");
                    }
                }
            } else if (e.getSource() == seConnecterButton) {
                String nomUtilisateur = nomUtilisateurField.getText();
                char[] motDePasse = motDePasseField.getPassword();

                // Vérification des identifiants
                if (verifierAuthentification(nomUtilisateur, new String(motDePasse))) {
                    authentificationReussie = true;
                    messageArea.setText("Authentification réussie !");
                } else {
                	messageArea.setText("Identifiants incorrects. Veuillez réessayer.");
                }

                // Effacer le mot de passe après la vérification
                Arrays.fill(motDePasse, '0');
                motDePasseField.setText("");
            
        }
    
}

private void actionMotDePasseOublie() {
	
    String NOM = JOptionPane.showInputDialog(this, "Entrez votre nom d'utilisateur:");
    String email = JOptionPane.showInputDialog(this, "Entrez votre adresse e-mail:");

    if (NOM != null && email != null) {
        String IDETUDIANT = recupererIDEtudiant(NOM, email);

        if (IDETUDIANT != null) {
        	messageArea.setText("Votre IDETUDIANT est : " + IDETUDIANT);
        } else {
        	messageArea.setText("Nom d'utilisateur ou adresse e-mail incorrects.");
        }
    }
}

private String recupererIDEtudiant(String NOM, String email) {
    try {
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

        String sql = "SELECT IDETUDIANT FROM diplome_de_licence WHERE NOM = ? AND Email = ?";
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        statement.setString(1, NOM);
        statement.setString(2, email);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("IDETUDIANT");
        }

        connection.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}
        private boolean effectuerModificationDansBaseDeDonnees(String table, String idPrimaire, String ideTudiant, String champAModifier, String nouvelleValeur) {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String verifIdEtudiant = "SELECT * FROM " + table + " WHERE " + idPrimaire + " = ?";
                PreparedStatement verifStatement = (PreparedStatement) connection.prepareStatement(verifIdEtudiant);
                verifStatement.setString(1, ideTudiant);

                ResultSet resultSet = verifStatement.executeQuery();
                if (!resultSet.next()) {
                    return false;
                }

                String sql = "UPDATE " + table + " SET " + champAModifier + " = ? WHERE " + idPrimaire + " = ?";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setString(1, nouvelleValeur);
                statement.setString(2, ideTudiant);

                statement.executeUpdate();

                connection.close();

                idEtudiantField.setText("");
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        private boolean verifierAuthentification(String nomUtilisateur, String motDePasse) {
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "SELECT * FROM diplome_de_licence WHERE PRENOM = ? AND IDETUDIANT = ?";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setString(1, nomUtilisateur);
                statement.setString(2, motDePasse);

                ResultSet resultSet = statement.executeQuery();
                boolean authentificationReussie = resultSet.next();

                connection.close();

                return authentificationReussie;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(new Color(30, 144, 255));
            return label;
        }

        private void showMessage(String message) {
            // Utilisation de la zone de message existante
            // messageArea.setText(message);
            JOptionPane.showMessageDialog(this, message);
        }

        public static void main(String[] args) {
            JFrame principal = new JFrame();
            principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            principal.add(new principal());
            principal.pack();
           principal.setVisible(true);
        }
    }


    public class AffichagePanel extends JPanel {
        private JTextArea textArea;
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JLabel imageLabel;
        private JPanel authPanel;

        public AffichagePanel() {
            setLayout(new CardLayout());
            authPanel = createAuthPanel();
            add(authPanel, "Authentification");
            add(createDisplayPanel(), "Affichage");

            // Afficher le panneau d'authentification au début
            CardLayout cardLayout = (CardLayout) getLayout();
            cardLayout.show(this, "Authentification");
        }

        private JPanel createAuthPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

          

            // Vos composants d'authentification ici
            usernameField = new JTextField(10);
            passwordField = new JPasswordField(10);
            JButton loginButton = new JButton("connecter");
           
            // Ajout des composants au panel d'authentification
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Nom d'utilisateur:"), gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 3;
            panel.add(usernameField, gbc);

            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            panel.add(new JLabel("mot de passe:"), gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 2;
            panel.add(passwordField, gbc);

            gbc.gridx = 3;
            panel.add(loginButton, gbc);

            gbc.gridx = 4;
           
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    if (!checkAuthentication(username, password)) {
                    	messageArea.setText( "Authentification échouée. Veuillez vérifier vos identifiants.");
                        return;
                    }
                    // Afficher le panel d'affichage
                    CardLayout cardLayout = (CardLayout) getLayout();
                    cardLayout.show(AffichagePanel.this, "Affichage");
                    afficherInfos();
                }
            });
            return panel;
        }
        private JPanel createDisplayPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            // : textArea
            textArea = new JTextArea(20, 50);
            textArea.setEditable(false);
            textArea.setForeground(Color.BLACK);
            textArea.setFont(new Font( "Cambria Math", Font.ITALIC, 14));
            textArea.setBackground(Color.BLACK);
            textArea.setBackground(new Color(173, 216, 250));

            JScrollPane scrollPane = new JScrollPane(textArea);
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            panel.add(scrollPane, gbc);

            return panel;
        }

        private boolean checkAuthentication(String username, String password) {
            
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                String sql = "SELECT * FROM personnel_uadb WHERE PRENOM = ? AND IDPERSONNEL = ?";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();
                boolean authentificationReussie = resultSet.next();

                connection.close();

                return authentificationReussie;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        public void afficherInfos() {
            Connection conn = null;
            Statement stmt = null;

            try {
                String DB_URL = "jdbc:mysql://localhost:3306/pro_java";
                String USER = "root";
                String PASS = "";

                conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                String queryDiplome = "SELECT * FROM diplome_de_licence";
                ResultSet rsDiplome = stmt.executeQuery(queryDiplome);
                textArea.append("=== Informations Diplome de Licence ===\n");
                while (rsDiplome.next()) {
                    textArea.append("Nom: " + rsDiplome.getString("NOM") + "\n");
                    textArea.append("Prénom: " + rsDiplome.getString("PRENOM") + "\n");
                    textArea.append("Adresse: " + rsDiplome.getString("adresse") + "\n");
                    textArea.append("Email: " + rsDiplome.getString("email") + "\n");
                    textArea.append("Téléphone: " + rsDiplome.getString("telephone") + "\n");
                    textArea.append("ID ETUDIANT: " + rsDiplome.getInt("IDETUDIANT") + "\n");
                    textArea.append("\n");
                }
                textArea.append("\n");
                // Informations Annee Universitaire
                String queryAnnee = "SELECT * FROM annee_universitaire";
                ResultSet rsAnnee = stmt.executeQuery(queryAnnee);
                textArea.append("=== Informations Annee Universitaire ===\n");
                while (rsAnnee.next()) {
                    textArea.append("ID Annee Universitaire: " + rsAnnee.getInt("IDANNEE_UNIVERSITAIRE") + "\n");
                    textArea.append("Date de début: " + rsAnnee.getString("DATE_DE_DEBUT") + "\n");
                    textArea.append("Date de fin: " + rsAnnee.getString("DATE_DE_FIN") + "\n");
                    textArea.append("Nom de diplome: " + rsAnnee.getString("nom_diplome") + "\n");
                    textArea.append("Etablissement d'accueil: " + rsAnnee.getString("etablissement_acceuil") + "\n");
                    textArea.append("\n");
                }
                textArea.append("\n");
                String queryEmploi = "SELECT * FROM emploi";
                ResultSet rsEmploi = stmt.executeQuery(queryEmploi);
                textArea.append("=== Informations Emploi ===\n");
                while (rsEmploi.next()) {
                    textArea.append("ID Emploi: " + rsEmploi.getInt("IDEMPLOI") + "\n");
                    textArea.append("Date de début: " + rsEmploi.getString("DATE_DEBUT") + "\n");
                    textArea.append("Date de fin: " + rsEmploi.getString("DATE_FIN") + "\n");
                    textArea.append("Entreprise: " + rsEmploi.getString("ENTREPRISE") + "\n");
                    textArea.append("Poste occupé: " + rsEmploi.getString("POSTE_OCCUPE") + "\n");
                    textArea.append("\n");
                }
                textArea.append("\n");
                // Informations Liste Amis
                String queryListeAmis = "SELECT * FROM liste_amis";
                ResultSet rsListeAmis = stmt.executeQuery(queryListeAmis);

                textArea.append("=== Informations Liste Amis ===\n");
                while (rsListeAmis.next()) {
                    textArea.append("ID Amis: " + rsListeAmis.getInt("IDAMIS") + "\n");
                    textArea.append("Nom: " + rsListeAmis.getString("NOM") + "\n");
                    textArea.append("Prénom: " + rsListeAmis.getString("PRENOM") + "\n");
                    textArea.append("ID Etudiant: " + rsListeAmis.getInt("IDETUDIANT") + "\n");
                    textArea.append("\n");
                }
                textArea.append("\n");

                // Informations Inscription Master
                String queryInscriptionMaster = "SELECT * FROM inscription_master";
                ResultSet rsInscriptionMaster = stmt.executeQuery(queryInscriptionMaster);

                textArea.append("=== Informations Inscription Master ===\n");
                while (rsInscriptionMaster.next()) {
                    textArea.append("ID Inscription Master: " + rsInscriptionMaster.getInt("IDINSCRIPTION_MASTER") + "\n");
                    textArea.append("Niveau Master: " + rsInscriptionMaster.getString("niveau_master") + "\n");
                    textArea.append("Université d'accueil: " + rsInscriptionMaster.getString("universite_acceuil") + "\n");
                    textArea.append("\n");
                }
                textArea.append("\n");
                String queryTypeContrat = "SELECT * FROM type_de_contrat";
                ResultSet rsTypeContrat = stmt.executeQuery(queryTypeContrat);
                textArea.append("=== Informations Type de Contrat ===\n");
                while (rsTypeContrat.next()) {
                    textArea.append("ID Type de Contrat: " + rsTypeContrat.getInt("IDTYPE_DE_CONTRAT") + "\n");
                    textArea.append("Nom: " + rsTypeContrat.getString("nom") + "\n");
                    textArea.append("ID Emploi: " + rsTypeContrat.getInt("IDEMPLOI") + "\n");
                    textArea.append("\n");
                }
                textArea.append("\n");
                rsDiplome.close();
                rsAnnee.close();
                rsEmploi.close();
                rsListeAmis.close();
                rsInscriptionMaster.close();
                rsTypeContrat.close();

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
           }}
        public static void main(String[] args) {
            JFrame frame = new JFrame("Affichage Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new principal());
            frame.pack();
            frame.setVisible(true);
        }
    }
    public class RecherchePanel extends JPanel {
        private JTextField idField;
        private JButton searchButton;
        private JTextArea resultArea;
        private JLabel imageLabel;
        private JTextField usernameField; 
        private JPasswordField passwordField; 
        private JButton loginButton; 
        public RecherchePanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            // Créez et configurez les composants
            idField = new JTextField(10);
            searchButton = new JButton("Rechercher");
            resultArea = new JTextArea(10,20);
            imageLabel = new JLabel();
            ImageIcon image = new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\Gestion_diplome_srt\\doc\\image figuant dans l'interface/OIP1.jpg");
            imageLabel.setIcon(image);
            // Ajoutez un gestionnaire d'événements pour le bouton de recherche
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Vérification de l'authentification
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword()); // Récupérer le mot de passe saisi

                    if (!checkAuthentication(username, password)) {
                    	 messageArea.setText( "Authentification échouée. Veuillez vérifier vos identifiants.");
                        return; // Ne pas effectuer la recherche si l'authentification a échoué
                    }
                    String searchID = idField.getText();
                    String elements = rechercherElements(searchID);
                    resultArea.setText(elements);
                }
            });
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            add(new JLabel("Nom d'utilisateur:"), gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 3;
            usernameField = new JTextField(10);
            add(usernameField, gbc);

            gbc.gridy = 3;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            add(new JLabel("Mot de passe:"), gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 3;
            passwordField = new JPasswordField(10);
            add(passwordField, gbc);

            gbc.gridy = 3;
            gbc.gridx = 4;
            gbc.gridwidth = 4;
            loginButton = new JButton("Connecter");
            add(loginButton, gbc);

            // Ajoutez un label et un bouton pour "Mot de passe oublié"
            gbc.gridx = 5;
            gbc.gridy = 2;
            // Ajoutez un gestionnaire d'événements pour le bouton de connexion
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    if (!checkAuthentication(username, password)) {
                    	messageArea.setText( "Authentification échouée. Veuillez vérifier vos identifiants.");
                        return;
                    }
                    // Si l'authentification réussit, effectuez la recherche
                    String searchID = idField.getText();
                    String elements = rechercherElements(searchID);
                    resultArea.setText(elements);
                }
            });
            // Ajoutez les composants restants avec les contraintes GridBagLayout
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(5, 5, 5, 5);
            add(new JLabel("ID de recherche:"), gbc);
            gbc.gridx = 2;
            gbc.gridwidth = 1;
            add(idField, gbc);
            gbc.gridx = 3;
            add(searchButton, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 4;
            gbc.fill = GridBagConstraints.BOTH;
            add(new JScrollPane(resultArea), gbc);
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.NONE;
            add(imageLabel, gbc);
        }

        private boolean checkAuthentication(String username, String password) {
           
           // return username.equals("awa") && password.equals("admin");
        	 try {
        	        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

        	        String sql = "SELECT * FROM personnel_uadb WHERE PRENOM = ? AND IDPERSONNEL = ?";
        	        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        	        statement.setString(1,  username);
        	        statement.setString(2, password);

        	        ResultSet resultSet = statement.executeQuery();
        	        boolean authentificationReussie = resultSet.next();

        	        connection.close();

        	        return authentificationReussie;
        	    } catch (SQLException ex) {
        	        ex.printStackTrace();
        	        return false;
        	    }
        	}

            private String rechercherElements(String searchID) {
            	

                StringBuilder result = new StringBuilder();

                try {
                    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");

                    // Vérifier si l'ID existe dans diplome_de_licence
                    PreparedStatement checkIDStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM diplome_de_licence WHERE IDETUDIANT = ?");
                    checkIDStmt.setString(1, searchID);

                    ResultSet checkIDRs = checkIDStmt.executeQuery();

                    ResultSetMetaData diplomeMetaData = (ResultSetMetaData) checkIDRs.getMetaData();
                    int diplomeColumnCount = diplomeMetaData.getColumnCount();

                    while (checkIDRs.next()) {
                        result.append("\nTable: diplome_de_licence\n");

                        for (int i = 1; i <= diplomeColumnCount; i++) {
                            String columnName = diplomeMetaData.getColumnName(i);
                            String value = checkIDRs.getString(i);
                            result.append(columnName).append(": ").append(value).append("\n");
                        }
                    }
                    // Vérifier si l'ID correspond dans annee_universitaire
                    PreparedStatement anneeStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM annee_universitaire WHERE IDANNEE_UNIVERSITAIRE = ?");
                    anneeStmt.setString(1, searchID);

                    ResultSet anneeRs = anneeStmt.executeQuery();

                    ResultSetMetaData anneeMetaData = (ResultSetMetaData) anneeRs.getMetaData();
                    int anneeColumnCount = anneeMetaData.getColumnCount();

                    while (anneeRs.next()) {
                        result.append("\nTable: annee_universitaire\n");

                        for (int i = 1; i <= anneeColumnCount; i++) {
                            String columnName = anneeMetaData.getColumnName(i);
                            String value = anneeRs.getString(i);
                            result.append(columnName).append(": ").append(value).append("\n");
                        }
                    }

                    // Vérifier si l'ID correspond dans inscription_master
                    PreparedStatement inscriptionStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM inscription_master WHERE IDINSCRIPTION_MASTER = ?");
                    inscriptionStmt.setString(1, searchID);

                    ResultSet inscriptionRs = inscriptionStmt.executeQuery();

                    ResultSetMetaData inscriptionMetaData = (ResultSetMetaData) inscriptionRs.getMetaData();
                    int inscriptionColumnCount = inscriptionMetaData.getColumnCount();

                    while (inscriptionRs.next()) {
                        result.append("\nTable: inscription_master\n");

                        for (int i = 1; i <= inscriptionColumnCount; i++) {
                            String columnName = inscriptionMetaData.getColumnName(i);
                            String value = inscriptionRs.getString(i);
                            result.append(columnName).append(": ").append(value).append("\n");
                        }
                    }

                    // Vérifier si l'ID correspond dans emploi
                    PreparedStatement emploiStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM emploi WHERE IDEMPLOI = ?");
                    emploiStmt.setString(1, searchID);

                    ResultSet emploiRs = emploiStmt.executeQuery();

                    ResultSetMetaData emploiMetaData = (ResultSetMetaData) emploiRs.getMetaData();
                    int emploiColumnCount = emploiMetaData.getColumnCount();

                    while (emploiRs.next()) {
                        result.append("\nTable: emploi\n");

                        for (int i = 1; i <= emploiColumnCount; i++) {
                            String columnName = emploiMetaData.getColumnName(i);
                            String value = emploiRs.getString(i);
                            result.append(columnName).append(": ").append(value).append("\n");
                        }
                    }

                    // Vérifier si l'ID correspond dans liste_amis
                    PreparedStatement listeAmisStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM liste_amis WHERE IDAMIS = ?");
                    listeAmisStmt.setString(1, searchID);

                    ResultSet listeAmisRs = listeAmisStmt.executeQuery();

                    ResultSetMetaData listeAmisMetaData = (ResultSetMetaData) listeAmisRs.getMetaData();
                    int listeAmisColumnCount = listeAmisMetaData.getColumnCount();

                    while (listeAmisRs.next()) {
                        result.append("\nTable: liste_amis\n");

                        for (int i = 1; i <= listeAmisColumnCount; i++) {
                            String columnName = listeAmisMetaData.getColumnName(i);
                            String value = listeAmisRs.getString(i);
                            result.append(columnName).append(": ").append(value).append("\n");
                        }
                    }

                    // Si ID étudiant est égal à ID inscription master alors afficher les infos de annee universitaire, inscription master et diplome de licence
                    if (result.toString().contains("IDETUDIANT") && result.toString().contains("IDINSCRIPTION_MASTER")) {
                        PreparedStatement joinStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM diplome_de_licence, annee_universitaire, inscription_master WHERE diplome_de_licence.IDETUDIANT = ? AND diplome_de_licence.IDETUDIANT = annee_universitaire.IDANNEE_UNIVERSITAIRE AND diplome_de_licence.IDETUDIANT = inscription_master.IDINSCRIPTION_MASTER");
                        joinStmt.setString(1, searchID);

                        ResultSet joinRs = joinStmt.executeQuery();

                        ResultSetMetaData joinMetaData = (ResultSetMetaData) joinRs.getMetaData();
                        int joinColumnCount = joinMetaData.getColumnCount();

                        while (joinRs.next()) {
                            result.append("\nTables: diplome_de_licence, annee_universitaire, inscription_master, emploi\n");
                           
    	                        for (int i = 1; i <= joinColumnCount; i++) {
                                String columnName = joinMetaData.getColumnName(i);
                                String value = joinRs.getString(i);
                                result.append(columnName).append(": ").append(value).append("\n");
                            }
                        }
                    }

                    // Si ID étudiant est égal à ID emploi alors afficher les infos de annee universitaire, inscription master, emploi, liste amis et diplome de licence
                 // Requête avec jointure complète
                    PreparedStatement fullJoinStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM diplome_de_licence, annee_universitaire, inscription_master, emploi WHERE diplome_de_licence.IDETUDIANT = ? AND diplome_de_licence.IDETUDIANT = annee_universitaire.IDANNEE_UNIVERSITAIRE AND diplome_de_licence.IDETUDIANT = inscription_master.IDINSCRIPTION_MASTER AND diplome_de_licence.IDETUDIANT = emploi.IDEMPLOI");
                    fullJoinStmt.setString(1, searchID);

                    ResultSet fullJoinRs = fullJoinStmt.executeQuery();

                    ResultSetMetaData fullJoinMetaData = (ResultSetMetaData) fullJoinRs.getMetaData();
                    int fullJoinColumnCount = fullJoinMetaData.getColumnCount();

                    while (fullJoinRs.next()) {
                        result.append("\nTables: diplome_de_licence, annee_universitaire, inscription_master, emploi\n");
                        for (int i = 1; i <= fullJoinColumnCount; i++) {
                            String columnName = fullJoinMetaData.getColumnName(i);
                            String value = fullJoinRs.getString(i);
                            result.append(columnName).append(": ").append(value).append("\n");
                        }
                    }

                    // Requête avec jointure partielle
                    PreparedStatement partialJoinStmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM diplome_de_licence, inscription_master, emploi WHERE diplome_de_licence.IDETUDIANT = ? AND diplome_de_licence.IDETUDIANT = inscription_master.IDINSCRIPTION_MASTER AND diplome_de_licence.IDETUDIANT = emploi.IDEMPLOI");
                    partialJoinStmt.setString(1, searchID);

                    ResultSet partialJoinRs = partialJoinStmt.executeQuery();

                    ResultSetMetaData partialJoinMetaData = (ResultSetMetaData) partialJoinRs.getMetaData();
                    int partialJoinColumnCount = partialJoinMetaData.getColumnCount();

                    while (partialJoinRs.next()) {
                        result.append("\nTables: diplome_de_licence, inscription_master, emploi\n");
                        for (int i = 1; i <= partialJoinColumnCount; i++) {
                            String columnName = partialJoinMetaData.getColumnName(i);
                            String value = partialJoinRs.getString(i);
                            result.append(columnName).append(": ").append(value).append("\n");
                        }
                    }
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return "Erreur lors de la recherche.";
                }

                if (result.length() == 0) {
                    return "Aucune information trouvée pour l'ID " + searchID;
                }

                return result.toString();
            }
            public static void main(String[] args) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new principal());
                frame.pack();
                frame.setVisible(true);
            }
        }
public class VerificationPanel extends JPanel {
	 private boolean checkIfUserExists(String name, String email) {
	        try {
	            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
	            String sql = "SELECT * FROM directeur_scolarite WHERE NOM = ? AND EMAIL = ?";
	            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
	            statement.setString(1, name);
	            statement.setString(2, email);

	            ResultSet resultSet = statement.executeQuery();
	            boolean userExists = resultSet.next();

	            resultSet.close();
	            statement.close();
	            connection.close();

	            return userExists;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }

	    private String getIDDIRECTEUR_SCOLARITE(String NOM, String email) {
	        try {
	            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
	            String sql = "SELECT IDDIRECTEUR_SCOLARITE FROM directeur_scolarite WHERE NOM = ? AND EMAIL = ?";
	            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
	            statement.setString(1, NOM);
	            statement.setString(2, email);

	            ResultSet resultSet = statement.executeQuery();

	            String IDDIRECTEUR_SCOLARITE = null;
	            if (resultSet.next()) {
	            	IDDIRECTEUR_SCOLARITE= resultSet.getString("IDDIRECTEUR_SCOLARITE");
	            }

	            resultSet.close();
	            statement.close();
	            connection.close();

	            return IDDIRECTEUR_SCOLARITE;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }

	
	private JTextField anneeIDField;
    private JTextArea TextArea;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel authPanel;
    private boolean isAuthentificated = false; // Nouvelle variable pour suivre l'état d'authentification
    private JLabel imageLabel;
    public VerificationPanel() {
    	setLayout(new GridLayout(4, 2, 10, 10));
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        authPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        JLabel passwordLabel = new JLabel("Mot de passe:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");
        authPanel.add(usernameLabel);
        authPanel.add(usernameField);
        authPanel.add(passwordLabel);
        authPanel.add(passwordField);
        authPanel.add(new JLabel());
        authPanel.add(loginButton);
        JButton forgotPasswordButton = new JButton("Mot de passe oublié");
        authPanel.add(usernameLabel);
        authPanel.add(usernameField);
        authPanel.add(passwordLabel);
        authPanel.add(passwordField);
        authPanel.add(loginButton);
        add(authPanel, BorderLayout.CENTER);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (checkCredentials(username, password)) {
                    showMainContent();
                } 
                else {
                	messageArea.setText( "Identifiants incorrects. Veuillez réessayer.");
                }}  }); }
    private boolean checkCredentials(String username, String password) {
    	 try { Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
    	        String sql = "SELECT * FROM directeur_scolarite WHERE PRENOM = ? AND IDDIRECTEUR_SCOLARITE = ?";
    	        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
    	        statement.setString(1, username);statement.setString(2, password); ResultSet resultSet = statement.executeQuery();
    	        boolean authentificationReussie = resultSet.next(); connection.close(); return authentificationReussie;
    	    } catch (SQLException ex) { ex.printStackTrace(); return false; }}
    private void showMainContent() {
        remove(imageLabel); remove(authPanel);
        JLabel label = new JLabel("Vérification des informations"); label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER); add(label, BorderLayout.NORTH);
        JPanel inputPanel = new JPanel(); inputPanel.setLayout(new FlowLayout());
        anneeIDField = new JTextField(10);JButton verifyButton = new JButton("Vérifier");
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(new JLabel("ID de l'année universitaire : ")); inputPanel.add(anneeIDField);
        inputPanel.add(verifyButton); add(inputPanel, BorderLayout.CENTER);
        TextArea = new JTextArea(); TextArea.setEditable(false);
        TextArea.setFont(new Font("Arial", Font.PLAIN, 14)); TextArea.setRows(8);
        add(new JScrollPane(TextArea), BorderLayout.SOUTH);
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int yearID = Integer.parseInt(anneeIDField.getText());
                try { Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
                    PreparedStatement statement = (PreparedStatement) connection.prepareStatement("SELECT * FROM annee_universitaire WHERE IDANNEE_UNIVERSITAIRE = ?");
                    statement.setInt(1, yearID);ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {String diplomaType = resultSet.getString("nom_diplome");
                        if (!diplomaType.equals("licence") && !diplomaType.equals("master")) {TextArea.append("L'étudiant avec l'ID d'année universitaire " + yearID + " n'a pas encore obtenu sa licence.\n");
                        } else if (diplomaType.equals("licence")) {TextArea.append("L'étudiant avec l'ID d'année universitaire " + yearID + " a obtenu sa licence !\n");
                        } else if (diplomaType.equals("master")) { PreparedStatement masterStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM inscription_master WHERE IDINSCRIPTION_MASTER = ?");
                            masterStatement.setInt(1, yearID);ResultSet masterResult = masterStatement.executeQuery();
                            if (masterResult.next()) {TextArea.append("L'étudiant avec l'ID d'année universitaire " + yearID + " a obtenu sa licence !\n");
                            } else {TextArea.append("L'étudiant avec l'ID d'année universitaire " + yearID + " n'a pas encore obtenu sa licence.\n"); }
                            masterResult.close(); masterStatement.close();
                        }} else {TextArea.append("Année universitaire non trouvée.\n"); }
                    resultSet.close(); statement.close(); connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                } }});revalidate();repaint();}}
public class ExploitationPanel extends JPanel {
	private boolean checkPasswordResetInfo(String nom, String email) {
	    try {
	        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
	        String sql = "SELECT * FROM directeur_ciaq WHERE NOM = ? AND EMAIL = ?";
	        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
	        statement.setString(1, nom);  statement.setString(2, email);
	        ResultSet resultSet = statement.executeQuery();
	        boolean infoCorrectes = resultSet.next();
	        connection.close();return infoCorrectes;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false; }}
	private String getIDDIRECTEUR_CIAQ(String Nom, String email) {
	    try {
	        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
	        String sql  = "SELECT IDDIRECTEUR_CIAQ FROM directeur_ciaq WHERE NOM = ? AND EMAIL = ?";
	        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
	        statement.setString(1, Nom); statement.setString(2, email);
	        ResultSet resultSet = statement.executeQuery(); String IDDIRECTEUR_CIAQ = null;
	        if (resultSet.next()) {
	        	IDDIRECTEUR_CIAQ = resultSet.getString("IDDIRECTEUR_CIAQ"); }
	        connection.close();return IDDIRECTEUR_CIAQ;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return null;}}
    private JTextArea resultArea;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel authPanel;
    private JPanel mainPanel;
    private boolean isAuthenticated = false;
    private JLabel imageLabel;
    public ExploitationPanel() {
      setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        authPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    	setLayout(new GridLayout(4, 2, 10, 10));setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Alignement vertical
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");JLabel passwordLabel = new JLabel("Mot de passe:");
        usernameField = new JTextField();passwordField = new JPasswordField();
        JButton loginButton = new JButton("Se connecter");
        authPanel.add(usernameLabel); authPanel.add(usernameField); authPanel.add(passwordLabel);
        authPanel.add(passwordField); authPanel.add(new JLabel()); authPanel.add(loginButton);
        GridBagConstraints gbc = new GridBagConstraints(); mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Exploitation des résultats");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);mainPanel.add(label, BorderLayout.NORTH);
        resultArea = new JTextArea(); resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));resultArea.setRows(8);
        mainPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        JButton entrepriseButton = new JButton("Entreprise qui emploie le plus d'anciens étudiants");
        JButton tauxBambeyButton = new JButton("Taux d'étudiants en train de faire leur master à Bambey");
        JButton tauxAutreEtablissementButton = new JButton("Taux d'étudiants en train de faire leur master dans un autre établissement au Sénégal");
        JButton tauxEmploiButton = new JButton("Taux d'étudiants ayant obtenu du travail");
        JButton tauxEtrangerButton = new JButton("Taux d'étudiants en partance à l'étranger");JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));buttonPanel.add(entrepriseButton);
        buttonPanel.add(tauxBambeyButton); buttonPanel.add(tauxAutreEtablissementButton);
        buttonPanel.add(tauxEmploiButton); buttonPanel.add(tauxEtrangerButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        entrepriseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exploiterEntreprise();}});
        tauxBambeyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exploiterTauxBambey();}});
        tauxAutreEtablissementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exploiterTauxAutreEtablissement();}});
        tauxEmploiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { exploiterTauxEmploi();}});
        tauxEtrangerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { exploiterTauxEtranger();
            }});
        add(authPanel, BorderLayout.CENTER);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (checkCredentials(username, password)) {loginSuccessful();
                } else {messageArea.setText( "Identifiants incorrects. Veuillez réessayer.");
                }} });}
    private void exploiterEntreprise() {
        String query = "SELECT entreprise, COUNT(*) as total FROM emploi GROUP BY entreprise ORDER BY total DESC LIMIT 1";
        executeQuery(query);}
    private void exploiterTauxBambey() {
        String query = "SELECT COUNT(*) as total FROM inscription_master WHERE universite_acceuil = 'Bambey'";
        executeQuery(query); }
    private void exploiterTauxAutreEtablissement() {
        String query = "SELECT COUNT(*) as total FROM inscription_master WHERE universite_acceuil != 'Bambey' AND universite_acceuil LIKE '%Sénégal%'";
        executeQuery(query); }
    private void exploiterTauxEmploi() {
        String query = "SELECT COUNT(*) as total FROM emploi";
        executeQuery(query); }
    private void exploiterTauxEtranger() {
        String query = "SELECT COUNT(*) as total FROM inscription_master WHERE universite_acceuil != 'Bambey' AND universite_acceuil NOT LIKE '%Sénégal%'";
        executeQuery(query);}
    private void executeQuery(String query) {
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i); String value = resultSet.getString(i);
                    result.append(columnName).append(": ").append(value).append("\n");
                } result.append("\n");}
            resultArea.setText(result.toString());
            resultSet.close(); statement.close(); connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }}
    private boolean checkCredentials(String username, String password) {
        //return username.equals("awa") && password.equals("admin");
    	 try {
    	        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mabase", "root", "");
    	        String sql = "SELECT * FROM directeur_ciaq WHERE PRENOM = ? AND IDDIRECTEUR_CIAQ = ?";
    	        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
    	        statement.setString(1, username); statement.setString(2,password);
    	        ResultSet resultSet = statement.executeQuery();
    	        boolean authentificationReussie = resultSet.next();
    	        connection.close(); return   authentificationReussie;
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	         return false;
    	    }}
    private void loginSuccessful() {
        isAuthenticated = true;
        showMainContent();
    }

    private void showMainContent() {
        if (isAuthenticated) {
            remove(authPanel);
            remove(imageLabel);
            add(mainPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exploitation Panel");
       principal panel = new principal();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
public List<String> chercherMotCle(String input) {
    List<String> motsCles = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(input);
    while (tokenizer.hasMoreTokens()) {
        String jeton = tokenizer.nextToken();
        motsCles.add(jeton);
    }
    return motsCles;
}
    public static void main(String[] args) {
        new principal();
    }}