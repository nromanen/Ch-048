package pages.admin;


import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Evgen on 25.04.2017.
 */
public class Main {

        public static void main(String[] args) throws Exception
        {
            /*// database connection
            Class driverClass = Class.forName("org.postgresql.Driver");
            Connection jdbcConnection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/hospital", "postgres", "postgres");
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

           *//* // partial database export
            QueryDataSet partialDataSet = new QueryDataSet(connection);
            partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
            partialDataSet.addTable("BAR");
            FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));*//*

            // full database export
            IDataSet fullDataSet = connection.createDataSet();
            FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

            // dependent tables database export: export table X and all tables that
            // have a PK which is a FK on X, in the right order for insertion
            String[] depTableNames =
                    TablesDependencyHelper.getAllDependentTables( connection, "users" );
            IDataSet depDataset = connection.createDataSet( depTableNames );
            FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));*/

        }

}
