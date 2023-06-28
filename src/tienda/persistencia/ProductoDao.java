package tienda.persistencia;

import tienda.entidades.Fabricante;
import tienda.entidades.Producto;

import java.sql.SQLException;

public class ProductoDao extends DAO {

    public void guardarProducto  (Producto aux) throws Exception {
        try {
            if (aux == null) {
                throw new Exception("Objeto de fabricante no existente");
            }
            String query = "INSERT INTO fabricante (codigo, nombre, precio, codigo_fabricante) VALUES ('"
                    //con codigo, no hace falta llamarlo ya que es autoincremental!!
                   + aux.getCodigo()
                   + "',"+ aux.getNombre()
                    + "'," + aux.getPrecio()
                    // es el fabricante del producto, es necesario traer el codigo del fabricante
                    + "'," + aux.getCodigoFabricante().getCodigo()
                    + "');";
            //se llama al metodo de DAO
                    insertarModificarEliminar(query);

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

    public void modificarProducto (Producto aux) throws Exception {
        try {
            if ( aux == null) {
                throw new Exception("Obj, producto nulo");
            }
            String query = "UPDATE producto " +
                    "SET nombre = '" + aux.getNombre() +
                    "', precio = " + aux.getPrecio() +
                    " ', codigo_fabricante = " + aux.getCodigoFabricante() +
                    " ' WHERE (codigo = " + aux.getCodigoFabricante() + "');";

  //UPDATE `tienda`.`producto`
            // SET `nombre` = 'Disco duro SATA3 1TBnj',
            // `precio` = '86.91',
            // `codigo_fabricante` = '6'
            // WHERE (`codigo` = '1');

            insertarModificarEliminar(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar el producto");
        }
    }

    public Producto buscarPorPrimaryKey (Integer codigo) throws Exception {
        //CONSULTA JOIN
        try {
            String sql = "SELECT * FROM producto p" +
                    "JOIN fabricante f " +
                    "ON f.codigo = p.codigo:fabricante " +
                    "WHERE codigo : '" + codigo + "';";
            consultarBase(sql);

            Producto prodObj = null;
            Fabricante fabObj = null;

            while (resultado.next()) {
                prodObj = new Producto();
                //primeros 3 es de tabla productos, ult 3 de tabla fabricante
                prodObj.setCodigo(resultado.getInt(1));
                prodObj.setNombre(resultado.getString(2));
                prodObj.setPrecio(resultado.getDouble(3));
                //necesario primero llenar datos con el producto de resulset, y luego se debe asignarle al producot
                fabObj.setCodigo(resultado.getInt(5));
                fabObj.setNombre(resultado.getString(6));
                prodObj.setCodigoFabricante(fabObj);
            }
            return prodObj;
        }  catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al buscar el producto a tráves del código");
        }
    }


}
