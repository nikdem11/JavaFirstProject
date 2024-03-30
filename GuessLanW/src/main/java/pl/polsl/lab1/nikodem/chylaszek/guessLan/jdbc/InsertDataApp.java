package pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc;

import java.sql.*;

/**
 * Inserts data into tables.
 * 
 * @author Nikodem
 * @version 1.0
 */
public class InsertDataApp {

    /**
     *
     */
    public void insertData() {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab5", "app5", "app5")) {
            Statement statement = con.createStatement();
            
            // Languages table
            statement.executeUpdate("INSERT INTO Languages VALUES (1, 'Polish')");
            statement.executeUpdate("INSERT INTO Languages VALUES (2, 'English')");
            statement.executeUpdate("INSERT INTO Languages VALUES (3, 'German')");
            statement.executeUpdate("INSERT INTO Languages VALUES (4, 'Spanish')");
            statement.executeUpdate("INSERT INTO Languages VALUES (5, 'Bulgarian')");
            statement.executeUpdate("INSERT INTO Languages VALUES (6, 'Turkish')");
            statement.executeUpdate("INSERT INTO Languages VALUES (7, 'Slovak')");
            statement.executeUpdate("INSERT INTO Languages VALUES (8, 'French')");
            statement.executeUpdate("INSERT INTO Languages VALUES (9, 'Greek')");
            statement.executeUpdate("INSERT INTO Languages VALUES (10, 'Portuguese')");
            statement.executeUpdate("INSERT INTO Languages VALUES (11, 'Danish')");
            statement.executeUpdate("INSERT INTO Languages VALUES (12, 'Russian')");
            statement.executeUpdate("INSERT INTO Languages VALUES (13, 'Swedish')");
            statement.executeUpdate("INSERT INTO Languages VALUES (14, 'Italian')");
            statement.executeUpdate("INSERT INTO Languages VALUES (15, 'Norwegian')");
            statement.executeUpdate("INSERT INTO Languages VALUES (16, 'Romanian')");
            statement.executeUpdate("INSERT INTO Languages VALUES (17, 'Finnish')");
            statement.executeUpdate("INSERT INTO Languages VALUES (18, 'Hungarian')");
            statement.executeUpdate("INSERT INTO Languages VALUES (19, 'Dutch')");
            statement.executeUpdate("INSERT INTO Languages VALUES (20, 'Czech')");
            
            // Questions table
            statement.executeUpdate("INSERT INTO Questions VALUES (1, 'Cze\u015b\u0107, jak się masz?', 'Language spoken in the best country in the world.', 1)");
            statement.executeUpdate("INSERT INTO Questions VALUES (2, 'Hello, how are you?', 'Widely spoken global language.', 2)");
            statement.executeUpdate("INSERT INTO Questions VALUES (3, 'Hallo, wie geht es dir?', 'Language associated with Oktoberfest.', 3)");
            statement.executeUpdate("INSERT INTO Questions VALUES (4, 'Hola, \u00bfc\u00f3mo est\u00e1s?', 'Language spoken in much of Latin America.', 4)");
            statement.executeUpdate("INSERT INTO Questions VALUES (5, 'Sanatorium pod klepsydrą.', 'Language spoken in the best country in the world.', 1)");
            statement.executeUpdate("INSERT INTO Questions VALUES (6, 'Hi, what is your name?', 'Widely spoken global language.', 2)");
            statement.executeUpdate("INSERT INTO Questions VALUES (7, '\u0417\u0434\u0440\u0430\u0432\u0435\u0439, \u043a\u0430\u043a \u0441\u0438?', 'Slavic language written in Cyrillic script.', 5)");
            statement.executeUpdate("INSERT INTO Questions VALUES (8, 'Merhaba, nas\u0131ls\u0131n\u0131z?', 'Language spoken in Cyprus.',6)");
            statement.executeUpdate("INSERT INTO Questions VALUES (9, 'Ahoj, ako sa m\u00e1\u0161?', 'Capital of this country is close to Vienna.', 7)");
            statement.executeUpdate("INSERT INTO Questions VALUES (10, 'Bonjour, comment \u00e7a va?','Romance language, famous for the Eiffel Tower.', 8)");
            statement.executeUpdate("INSERT INTO Questions VALUES (11, '\u0393\u03b5\u03b9\u03ac \u03c3\u03bf\u03c5, \u03c0\u03ce\u03c2 \u03b5\u03af\u03c3\u03b1\u03b9;','Language with an ancient alphabet.', 9)");
            statement.executeUpdate("INSERT INTO Questions VALUES (12, 'Ol\u00e1, como vai voc\u00ea?', 'Language spoken in Brazil.', 10)");
            statement.executeUpdate("INSERT INTO Questions VALUES (13, 'Hej, hvordan g\\u00e5r det?', 'Hans Christian Andersen.', 11)");
            statement.executeUpdate("INSERT INTO Questions VALUES (14, '\u041f\u0440\u0438\u0432\u0435\u0442, \u043a\u0430\u043a \u0434\u0435\u043b\u0430?', 'Not the greatest country', 12)");
            statement.executeUpdate("INSERT INTO Questions VALUES (15, 'Hej, hur m\u00e5r du?', 'Language of the Nobel Prize.', 13)");
            statement.executeUpdate("INSERT INTO Questions VALUES (16, 'Ciao, come stai?', 'Language of pizza and pasta.', 14)");
            statement.executeUpdate("INSERT INTO Questions VALUES (17, 'Hei, hvordan har du det?', 'Roald Amundsen.', 15)");
            statement.executeUpdate("INSERT INTO Questions VALUES (18, 'Bun\u0103, ce faci?', 'Vampire empire.', 16)");
            statement.executeUpdate("INSERT INTO Questions VALUES (19, 'Hei, mit\u00e4 kuuluu?', 'Uralic language. Aki Kaurismaki.', 17)");
            statement.executeUpdate("INSERT INTO Questions VALUES (20, 'Szia, hogy vagy?', 'Non-Indo-European language spoken by Magyars.', 18)");
            statement.executeUpdate("INSERT INTO Questions VALUES (21, 'Hallo, hoe gaat het?', 'Language spoken by best footbal coach in the world.', 19)");
            statement.executeUpdate("INSERT INTO Questions VALUES (22, 'Dzie\u0144 dobry, jak si\u0119 masz?', 'Language spoken in the best country in the world.', 1)");
            statement.executeUpdate("INSERT INTO Questions VALUES (23, 'Good morning, how do you do?', 'Widely spoken global language.', 2)");
            statement.executeUpdate("INSERT INTO Questions VALUES (24, 'Guten Tag, wie geht es Ihnen?', 'Language associated with Oktoberfest.', 3)");
            statement.executeUpdate("INSERT INTO Questions VALUES (25, 'Buenos d\u00edas, \u00bfc\u00f3mo est\u00e1s?', 'Language spoken in much of Latin America.', 4)");
            statement.executeUpdate("INSERT INTO Questions VALUES (26, '\u0417\u0434\u0440\u0430\u0441\u0442\u0438, \u043a\u0430\u043a \u0441\u0438?', 'Slavic language written in Cyrillic script.', 5)");
            statement.executeUpdate("INSERT INTO Questions VALUES (27, 'Selam, nas\u0131l gidiyor?', 'Language spoken in Cyprus.', 6)");
            statement.executeUpdate("INSERT INTO Questions VALUES (28, 'Dobr\u00fd de\u0148, ako sa m\u00e1te?', 'Capital of this country is close to Vienna.', 7)");
            statement.executeUpdate("INSERT INTO Questions VALUES (29, 'Salut, comment \u00e7a va ?', 'Romance language, known for its fashion and cuisine.', 8)");
            statement.executeUpdate("INSERT INTO Questions VALUES (30, '\u0393\u03b5\u03b9\u03b1 \u03c3\u03b1\u03c2, \u03c0\u03ce\u03c2 \u03b5\u03af\u03c3\u03c4\u03b5;', 'Language with an ancient history and rich culture.', 9)");
            statement.executeUpdate("INSERT INTO Questions VALUES (31, 'Ol\u00e1, como est\u00e1?', 'Language spoken in Brazil.', 10)");
            statement.executeUpdate("INSERT INTO Questions VALUES (32, 'Hej med dig, hvordan g\u00e5r det?', 'Mads Mikkelsen.', 11)");
            statement.executeUpdate("INSERT INTO Questions VALUES (33, '\u041f\u0440\u0438\u0432\u0435\u0442, \u043a\u0430\u043a \u0442\u044b?', 'Not the greatest country', 12)");
            statement.executeUpdate("INSERT INTO Questions VALUES (34, 'Hall\u00e5, hur m\u00e5r du?', 'Ingmar Bergman.', 13)");
            statement.executeUpdate("INSERT INTO Questions VALUES (35, 'Ciao, come va?', 'Language of art and history.', 14)");
            statement.executeUpdate("INSERT INTO Questions VALUES (36, 'Hei, hvordan g\u00e5r det med deg?', 'Joachim Trier.', 15)");
            statement.executeUpdate("INSERT INTO Questions VALUES (37, 'Salut, ce faci?', 'Vampire empire.', 16)");
            statement.executeUpdate("INSERT INTO Questions VALUES (38, 'Hei, kuinka voit?', 'Sakkijarven polkka.', 17)");
            statement.executeUpdate("INSERT INTO Questions VALUES (39, 'Szia, hogy vagy?', 'Non-Indo-European language spoken by Magyars.', 18)");
            statement.executeUpdate("INSERT INTO Questions VALUES (40, 'Hallo, hoe maakt u het?', 'Language spoken in  Belgium.', 19)");
            

            System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        
        

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        InsertDataApp insertDataApp = new InsertDataApp();
        insertDataApp.insertData();
    }
}
