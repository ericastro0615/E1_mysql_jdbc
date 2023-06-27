package tienda.persistencia;

import java.sql.*;

public abstract class DAO {
   //administra conexion
       protected Connection conexion;
       //instrucción de consulta
       protected ResultSet resultado;
       //manipula resultados
       protected Statement sentencia;

       private static final String driver ="com.mysql.cj.jdbc.Driver";
       private final String URL = "jdbc:mysql://localhost:3306/";
       //PERSONALIZAR!!!!!!!!!!
       private static final String db = "tienda";
       private static String user = "root";
       private static String password = "root";

       private static Connection connection;


       protected void conectarBase() throws Exception  {
               try {
                   //CARGA CONTROLADOR
                   Class.forName(driver);
                   connection = DriverManager.getConnection(driver + db + "&user=" + user + "&password=" + password);
               } catch (ClassNotFoundException | SQLException ex) {
                       throw ex;
                   }
               }


           protected void desconectarBase() throws Exception {
               try {
                   if (resultado != null) {
                       resultado.close();
                   }
                   if (sentencia != null) {
                       sentencia.close();
                   }
                   if (conexion != null) {
                       conexion.close();
                   }
               } catch (Exception ex) {
                   throw ex;
               }
           }

       protected void insertarModificarEliminar(String querySql) throws Exception {
           try {
               conectarBase();
               sentencia = conexion.createStatement();
               sentencia.executeUpdate(querySql);
           } catch (SQLException ex) {
               throw ex;
           } finally {
               desconectarBase();
           }
           }

       protected void consultarBase(String queryDAO) throws Exception {
           try {
               conectarBase();
               sentencia = conexion.createStatement();
                   resultado = sentencia.executeQuery( queryDAO);
           } catch (Exception ex) {
               throw ex;
           }
       }

    }

