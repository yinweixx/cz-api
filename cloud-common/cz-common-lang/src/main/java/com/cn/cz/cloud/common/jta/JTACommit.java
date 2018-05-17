package com.cn.cz.cloud.common.jta;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.*;
import java.sql.*;
import java.util.Properties;

/**
 * @author ywaz
 * @date 5/16/18 10:02
 */
public class JTACommit {

    public static void main (String[] args) throws NamingException, SQLException, HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException, NotSupportedException {
        Context ctx = new InitialContext();
        // Change these two lines to suit your environment.
        DataSource ds = (DataSource)ctx.lookup("jdbc/ExampleDS");
        Connection conn = ds.getConnection("testuser", "testpwd");
        Statement stmt = null; // Non-transactional statement
        Statement stmtx = null; // Transactional statement
        Properties dbProperties = new Properties();

        // Get a UserTransaction
        UserTransaction txn = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
        txn.begin();
        Connection mySqlConnection = DriverManager.getConnection("localhost:3306","root","123456");

        Statement mySqlStat = mySqlConnection.createStatement();
        mySqlStat.execute("update t_account set amount = amount - 500 where account_id = 'A'");

        Connection mySqlConnection2 = DriverManager.getConnection("192.168.1.2","root","123456");
        Statement mySqlStat2 = mySqlConnection2.createStatement();
        mySqlStat2.execute("update t_account set amount = amount - 500 where account_id = 'A'");

        txn.commit();

//        try {
//            stmt = conn.createStatement();  // non-tx statement
//
//            // Check the database connection.
//            try {
//                stmt.executeUpdate("DROP TABLE test_table");
//                stmt.executeUpdate("DROP TABLE test_table2");
//            }
//            catch (Exception e) {
//                // assume not in database.
//            }
//
//            try {
//                stmt.executeUpdate("CREATE TABLE test_table (a INTEGER,b INTEGER)");
//                stmt.executeUpdate("CREATE TABLE test_table2 (a INTEGER,b INTEGER)");
//            }
//            catch (Exception e) {
//            }
//
//            try {
//                System.out.println("Starting top-level transaction.");
//
//                txn.begin();
//
//                stmtx = conn.createStatement(); // will be a tx-statement
//
//                // First, we try to roll back changes
//
//                System.out.println("\nAdding entries to table 1.");
//
//                stmtx.executeUpdate("INSERT INTO test_table (a, b) VALUES (1,2)");
//
//                ResultSet res1 = null;
//
//                System.out.println("\nInspecting table 1.");
//
//                res1 = stmtx.executeQuery("SELECT * FROM test_table");
//
//                while (res1.next()) {
//                    System.out.println("Column 1: "+res1.getInt(1));
//                    System.out.println("Column 2: "+res1.getInt(2));
//                }
//                System.out.println("\nAdding entries to table 2.");
//
//                stmtx.executeUpdate("INSERT INTO test_table2 (a, b) VALUES (3,4)");
//                res1 = stmtx.executeQuery("SELECT * FROM test_table2");
//
//                System.out.println("\nInspecting table 2.");
//
//                while (res1.next()) {
//                    System.out.println("Column 1: "+res1.getInt(1));
//                    System.out.println("Column 2: "+res1.getInt(2));
//                }
//
//                System.out.print("\nNow attempting to rollback changes.");
//
//                txn.rollback();
//
//                // Next, we try to commit changes
//                txn.begin();
//                stmtx = conn.createStatement();
//                ResultSet res2 = null;
//
//                System.out.println("\nNow checking state of table 1.");
//
//                res2 = stmtx.executeQuery("SELECT * FROM test_table");
//
//                while (res2.next()) {
//                    System.out.println("Column 1: "+res2.getInt(1));
//                    System.out.println("Column 2: "+res2.getInt(2));
//                }
//
//                System.out.println("\nNow checking state of table 2.");
//
//                stmtx = conn.createStatement();
//
//                res2 = stmtx.executeQuery("SELECT * FROM test_table2");
//
//                while (res2.next()) {
//                    System.out.println("Column 1: "+res2.getInt(1));
//                    System.out.println("Column 2: "+res2.getInt(2));
//                }
//
//                txn.commit();
//            }
//            catch (Exception ex) {
//                ex.printStackTrace();
//                System.exit(0);
//            }
//        }
//        catch (Exception sysEx) {
//            sysEx.printStackTrace();
//            System.exit(0);
//        }
    }
}
