package main.dao.impl;

import main.dao.ConfiguracionJdbc;
import main.dao.Idao;
import main.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements Idao<Odontologo> {
private ConfiguracionJdbc configuracion;

    public OdontologoDaoH2() {
    }
    private static final Logger logger = Logger.getLogger(String.valueOf(OdontologoDaoH2.class));
    public OdontologoDaoH2(ConfiguracionJdbc configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        Connection connection = configuracion.conectarConBaseDeDatos();
        Statement statement= null;
        String query = String.format("INSERT INTO odontologos1 VALUES('%s','%s','%s','%s')", odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
        try {
            statement= connection.createStatement();
            statement.executeUpdate(query);
            statement.close();

        } catch (SQLException e) {
            logger.error("No se ha guardado el Odontologo " + odontologo.getId());
            throw new RuntimeException(e);
        }
        logger.info("Se ha guardado el odontologo " + odontologo.getId());
        return odontologo;
    }

    @Override
    public void eliminar(String id) {
        Connection connection = configuracion.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("DELETE FROM odontologos1 WHERE ID = %s", id);

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       }
    @Override
    public Odontologo buscarPorId(String id) {
        Connection connection = configuracion.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("SELECT id, nombre, apellido, matricula FROM odontologos1 where id = %s", id);
        Odontologo odontologo = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String idOdontologo = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String matricula = resultSet.getString("matricula");
                odontologo = new Odontologo(idOdontologo,nombre, apellido, matricula);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = configuracion.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("SELECT * FROM odontologos1");
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String idOdontologo = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String matricula = resultSet.getString("matricula");

                odontologos.add(new Odontologo(idOdontologo,nombre, apellido,matricula));
            }
        }catch (SQLException e) {
            logger.error("No se pueden listar los odontólogos", e);
            throw new RuntimeException(e);
        }
        logger.info("Se están listando los odontólogos. Total: " + odontologos.size() + " odontologos ingresados.");
        return odontologos;
    }
   @Override
   public Odontologo modificar(Odontologo odontologo) {
       Connection connection = configuracion.conectarConBaseDeDatos();
       Statement statement = null;
       String query = String.format("UPDATE odontologos1 SET nombre = '%s', apellido = '%s', matricula = '%s' WHERE id = '%s'",
               odontologo.getNombre(), odontologo.getApellido(), odontologo.getId(), odontologo.getMatricula());
       try {
           statement = connection.createStatement();
           statement.executeUpdate(query);
           statement.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return odontologo;
   }

}