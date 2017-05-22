/**
 * @author Alexander Kononenko
 * @version 1.0.0
 */
abstract class MySQLConnect{
    protected final String TAG = this.getClass().getSimpleName();
    private static final String URL = "jdbc:mysql://host:port/db";
    private static final String USER = "user";
    private static final String PSWD = "password";


    // JDBC variables for opening and managing connection
    protected static Connection con;
    protected static Statement stmt;

    /**
     * Constructor
     */
    public MySQLConnect() {
    }


    /**
     * Open connection
     */
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(URL, USER, PSWD);
            stmt = con.createStatement();
        } catch (Exception e) {
            Log.e(TAG, "MySQLConnection doInBackground: ", e);
        }
    }

    /**
     * Close connection and stmt
     */
    public void closeAllConnection() {
        try {
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }
    }

    /**
     * Close connection and stmt
     */
    public void closeAllConnection(ResultSet rs) {
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            //System.out.print("SQL-CLOSE-RESULTSET-Exception: " + ex.getMessage());
            Log.e(TAG, "Получено исключение", ex);
        }
    }

    /**
     * Close resultset here
     */
    public void closeResultSetOfConnection(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }
    }

    /**
     * Created sql query only for create table
     *
     * @param query string
     * @return boolean
     */
    public Boolean createTable(String query) {
        Boolean res = null;
        try {
            res = stmt.execute(query);

        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);

        } finally {
            return res;
        }
    }

    /**
     * Created statement sql query only for select
     *
     * @param query string
     * @return resultset object
     */
    public ResultSet createSelect(String query) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение: createSelect()", ex);
        }

        return rs;
    }

    /**
     * Created statement sql query only for insert, delete, update
     *
     * @param query string
     * @return Integer
     */
    public Integer createOtherQuery(String query) {
        int res = 0;
        try {
            res = stmt.executeUpdate(query);

        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение: createOtherQuery()", ex);
        }

        return res;
    }

    /**
     * Convert ResultSet To List
     *
     * @param resultSet object
     * @return list object
     */
    abstract public List convertResultSetToList(ResultSet resultSet);

}

