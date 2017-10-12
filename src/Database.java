import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    //    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/market_data";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "garbageman";
    private static Connection dbConnection;

    public Database() {
        this.dbConnection = getDBConnection();
    }

    private Connection getDBConnection() {

        Connection dbConnection = null;
//        try {
//
//            Class.forName(DB_DRIVER);
//
//        } catch (ClassNotFoundException e) {
//
//            System.out.println(e.getMessage());
//
//        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

    void createDbUserTable(String symbol) {
//        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE " + symbol + "("
                + "`date` datetime NOT NULL, "
                + "`unknown` int(11) DEFAULT NULL, "
                + "`open` float DEFAULT NULL, "
                + "`high` float DEFAULT NULL,"
                + "`low` float DEFAULT NULL,"
                + "`close` float DEFAULT NULL,"
                + "`volume` float DEFAULT NULL,"
                + "PRIMARY KEY (`date`),"
                + "UNIQUE KEY `id_UNIQUE` (`date`)"
                + ")";

        try {
//            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(createTableSQL);
            // execute the SQL stetement
            statement.execute(createTableSQL);

            System.out.println("Table \"dbuser\" is created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (dbConnection != null) {
            dbConnection.close();
        }
        super.finalize();
    }
}
