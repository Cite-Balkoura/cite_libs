package fr.milekat.cite_libs.utils_tools;

import fr.milekat.cite_libs.MainLibs;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaManage {
    private Connection connection;
    private final String driver,url,host,database,user,pass;

    public MariaManage(String url,String host,String database,String user,String pass){
        this.driver = "com.mysql.jdbc.Driver";
        this.url = url;
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;
    }

    /**
     *      Init de la connection SQL
     */
    public void connection(){
        try {
            Class.forName(this.driver);
            connection = DriverManager.getConnection(url + host + "/" + database +
                    "?autoReconnect=true&allowMultiQueries=true&characterEncoding=UTF-8", user, pass);
            Bukkit.getLogger().info(MainLibs.prefixConsole + "SQL connecté !");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning(MainLibs.prefixConsole + "Erreur SQL.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning(MainLibs.prefixConsole + "Erreur Class SQL.");
        }
    }

    /**
     *      LogOut du SQL
     */
    public void disconnect(){
        try {
            connection.close();
            Bukkit.getLogger().info(MainLibs.prefixConsole + "SQL déconnecté !");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning(MainLibs.prefixConsole + "Erreur SQL.");
        }
    }

    /**
     *      Retourne la connection pour initialiser un PreparedStatement
     * @return connection
     */
    public Connection getConnection(){
        return connection;
    }
}
