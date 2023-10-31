package main;

import main.dao.ConfiguracionJdbc;
import main.dao.Idao;
import main.dao.impl.OdontologoDaoH2;
import main.entity.Odontologo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    Idao<Odontologo> odontologoIdao = new OdontologoDaoH2(new ConfiguracionJdbc());

       odontologoIdao.eliminar("1");
       odontologoIdao.eliminar("2");
       odontologoIdao.eliminar("3");
       odontologoIdao.eliminar("4");
       odontologoIdao.eliminar("5");

       odontologoIdao.agregar(new Odontologo("1","Laura", "Taylor", "111111"));
       odontologoIdao.agregar(new Odontologo("2","Ana", "Gomez", "222222"));
       odontologoIdao.agregar(new Odontologo("3","Ignacio", "Ruiz", "333333"));
       odontologoIdao.agregar(new Odontologo("4","Sara", "Rodriguez", "444444"));
       odontologoIdao.agregar(new Odontologo("5","Pedro", "Fontana", "55555"));

       System.out.println(odontologoIdao.listarTodos());

      }
}
