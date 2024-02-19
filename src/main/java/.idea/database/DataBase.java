package ventasR.database;

import ventasR.model.ClienteJuridico;
import ventasR.model.ClienteNatural;
import ventasR.model.Ventas;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter

public class DataBase {

private ArrayList<ClienteNatural> listaClientesNaturales;
private ArrayList<ClienteJuridico> listaClientesJuridicos;
private ArrayList<Ventas> listaVentas;

    private static DataBase instance;

    private DataBase(){

        listaVentas = new ArrayList<>();
        listaClientesJuridicos = new ArrayList<>();
        listaClientesNaturales = new ArrayList<>();

    }

    public static DataBase getInstance(){

        if(instance==null){

            instance = new DataBase();

        }

        return instance;

    }

    public boolean crearVenta(Ventas venta){

        this.listaVentas.add(venta);

        return true;
    
    }

    public boolean eliminarVentana(Ventas venta){

        this.listaVentas.remove(ventaPorCodigo(venta.getCodigo()));

        return true;
    }

    public boolean crearClienteNatural(ClienteNatural cliente){

        this.listaClientesNaturales.add(cliente);
        return true;
    }

    public boolean actualizarClienteNatural(ClienteNatural clienteViejo, ClienteNatural clienteNuevo){
        this.listaClientesNaturales.remove(naturalPorId(clienteViejo.getIdentificacion()));
        this.listaClientesNaturales.add(clienteNuevo);
        return true;
    }

    public boolean eliminarClienteNatural(ClienteNatural cliente){

        this.listaClientesNaturales.remove(naturalPorId(cliente.getIdentificacion()));
        return true;
    }

    public boolean crearClienteJuridico(ClienteJuridico cliente){

        this.listaClientesJuridicos.add(cliente);
        return true;
    }

    public boolean actualizarClienteJuridico(ClienteJuridico clienteViejo, ClienteJuridico clienteNuevo){

        this.listaClientesJuridicos.remove(juridicoPorId(clienteViejo.getIdentificacion()));
        this.listaClientesJuridicos.add(clienteNuevo);

        return true;
    }

    public boolean eliminarClienteJuridico(ClienteJuridico cliente){
        this.listaClientesJuridicos.remove(juridicoPorId(cliente.getIdentificacion()));

        return true;
    }

    private ClienteJuridico juridicoPorId(String id){

        for(ClienteJuridico cliente : listaClientesJuridicos){

            if(cliente.getIdentificacion().equalsIgnoreCase(id)) return cliente;

        }

        return null;

    }

    private ClienteNatural naturalPorId(String id){

        for(ClienteNatural cliente : listaClientesNaturales){

            if(cliente.getIdentificacion().equalsIgnoreCase(id)) return cliente;

        }

        return null;

    }

    private Ventas ventaPorCodigo(String codigo){

        for(Ventas venta: listaVentas){
            if(venta.getCodigo().equalsIgnoreCase(codigo)) return venta;
        }
        return null;
    }







}
