package tienda.persistencia;

import tienda.entidades.Fabricante;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FabricanteDao extends DAO{

    public void guardar(Fabricante auxFabricante) throws Exception {
        try {
            if (auxFabricante == null) {
                throw new Exception(" INVÁLIDO");
            }
            // SENTENCIA SQL DE INSERCIÓN
                String queryGuardar = "INSERT INTO fabricante(codigo, nombre) VALUES(' "
                    + auxFabricante.getCodigo()
                    + "' , '" + auxFabricante.getNombre() + "'); " ;
            // se llama al método heredado de DAO
            insertarModificarEliminar(queryGuardar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al intentar guardar al fabricante");
        }
    }

    public void modificarauxFabricante(Fabricante auxFabricante) throws Exception {
        try {
            if (auxFabricante == null) {
                throw new Exception(" INVÁLIDO");
            }

            // SENTENCIA SQL DE MODIFICACIÓN
            String sqlModificacion = "UPDATE fabricante SET nombre = '"
                    + auxFabricante.getNombre() + "' WHERE codigo = '" + auxFabricante.getCodigo() +  "');";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sqlModificacion);
            System.out.println();
            insertarModificarEliminar(sqlModificacion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MODIFICAR la tabla Fabricante");
        }
    }

    public Fabricante buscarFabricantePorCodigo(Integer codigo) throws Exception {
        try {
            // SENTENCIA SQL DE CONSULTA
            String sqlBusqueda = "SELECT * FROM fabricante WHERE codigo = '" + codigo + "';";
            consultarBase(sqlBusqueda);

            Fabricante aux= null;
           // mientras aux tenga 1, va a ser seteado
            while (resultado.next()) {
                aux = new Fabricante();
                //resultSet comienza SIEMPRE con 1.
                aux.setCodigo(resultado.getInt(1));
                aux.setNombre(resultado.getString(2));
            }
            return aux;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL OBTENER Fabricante");
        } finally {
            desconectarBase();
        }
    }

    public void eliminarPorCodFabricante(Integer codigo) throws Exception {
        try {
            // SENTENCIA SQL DE ELIMINACIÓN
            String sql = "DELETE FROM fabricante WHERE nombre = '" + codigo + "';";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL ELIMINAR Fabricante");
        }
    }

    public List<Fabricante> obtenerFabricantes() throws Exception {
        try {
            // SENTENCIA SQL DE CONSULTA, trae todo
            String sql = "SELECT * FROM fabricante"; // "SELECT correo, nombre, apellido FROM auxFabricante";

            consultarBase(sql);
            //se creaa un obj Fabricante
            List<Fabricante> auxFabricanteLista = new ArrayList<>();
            Fabricante auxFabObj = null;

            while (resultado.next()) {
                auxFabObj = new Fabricante();

                auxFabObj.setCodigo(resultado.getInt(1));
                auxFabObj.setNombre(resultado.getString(2));

                auxFabricanteLista.add(auxFabObj);
            }

            return auxFabricanteLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL cargar drivers");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL OBTENER FabricanteS");
        } finally {
            desconectarBase();
        }
    }

    public List <Fabricante> obtenerFabricanteXnombre (String fab) throws Exception {
        try {
            // SENTENCIA SQL DE CONSULTA, trae todo
            String sql = "SELECT * FROM fabricante " +
                    "WHERE nombre = '%" + fab + " ';" ; // "SELECT correo, nombre, apellido FROM auxFabricante";

            consultarBase(sql);
            //se creaa un obj Fabricante
            List<Fabricante> auxFabricanteLista = new ArrayList<>();
            Fabricante auxFabObj = null;

            while (resultado.next()) {
                auxFabObj = new Fabricante();

                auxFabObj.setCodigo(resultado.getInt(1));
                auxFabObj.setNombre(resultado.getString(2));

                auxFabricanteLista.add(auxFabObj);
            }

            return auxFabricanteLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL cargar drivers");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL OBTENER FabricanteS");
        } finally {
            desconectarBase();
        }
    }
}
