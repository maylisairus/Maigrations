package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Database instance;
    private Connection connection;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    private Database(){

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);

      } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public static Database getInstance(){
        if(instance==null){
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
