package test;

import main.dao.ConfiguracionJdbc;
import main.dao.Idao;
import main.dao.impl.OdontologoDaoH2;
import main.entity.Odontologo;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.util.List;

public class Test {
    private static Idao<Odontologo> odontologoIdao = new OdontologoDaoH2(new ConfiguracionJdbc());

    @BeforeClass
    public static void agregarOdontologos() {
        odontologoIdao.agregar(new Odontologo("1","Laura", "Taylor", "111111"));
        odontologoIdao.agregar(new Odontologo("2","Ana", "Gomez", "222222"));
        odontologoIdao.agregar(new Odontologo("3","María", "Paredes", "233222"));
    }

       @org.junit.Test
       public void agregarOdontologo1(){
           Odontologo odontologo = new Odontologo();
           odontologo.setId("1");
           odontologo.setNombre("Juana");
           odontologo.setApellido("Dominguez");
           odontologo.setMatricula("12345");

           Odontologo resultado = odontologoIdao.agregar(odontologo);

           Assert.assertEquals(odontologo, resultado);
       }

    @org.junit.Test
    public void listarTodosOdont(){
        List<Odontologo> odontologos = odontologoIdao.listarTodos();
        Assert.assertNotNull(odontologos);
        Assert.assertNotEquals(11, odontologos.size());
        System.out.println(odontologos);
    }

    @org.junit.Test
    public void listarTodos(){
       Assert.assertFalse(odontologoIdao.listarTodos().isEmpty());}

    @org.junit.Test
    public void buscarPorIdOdontolo(){
        Odontologo odontologo = odontologoIdao.buscarPorId("2");
        Assert.assertNotNull(odontologo);
        System.out.println("El odontólogo buscado es: " + odontologo.toString());
    }
}
