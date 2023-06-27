package tienda.persistencia;

import tienda.entidades.Fabricante;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        } catch (SQLException | Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL OBTENER Fabricante");
        } finally {
            desconectarBase();
        }
    }

    public void eliminarauxFabricante(String correo) throws Exception {
        try {
            // SENTENCIA SQL DE ELIMINACIÓN
            String sql = "DELETE FROM auxFabricante WHERE correo = '" + correo + "';";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL ELIMINAR auxFabricante");
        }
    }

    public List<auxFabricante> obtenerauxFabricantes() throws Exception {
        try {
            // SENTENCIA SQL DE CONSULTA
            String sql = "SELECT * FROM auxFabricante"; // "SELECT correo, nombre, apellido FROM auxFabricante";

            consultarBase(sql);

            List<auxFabricante> auxFabricantes = new ArrayList<>();
            auxFabricante auxFabricante = null;

            while (resultado.next()) {
                auxFabricante = new auxFabricante();

                auxFabricante.setCorreo(resultado.getString(1));
                auxFabricante.setNombre(resultado.getString(2));
                auxFabricante.setApellido(resultado.getString(3));

                auxFabricantes.add(auxFabricante);
            }

            return auxFabricantes;
        } catch (SQLException | Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL OBTENER auxFabricanteS");
        } finally {
            desconectarBase();
        }
    }
}
