package ventasR.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ventasR.exception.AtributoVacioException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Empresa {


    private ArrayList<Ventas> listaVentas;
    private ArrayList<ClienteJuridico> clientesJuridicos;
    private ArrayList<ClienteNatural> clientesNaturales;

    private static Empresa empresa;

    public Empresa() {
        this.clientesNaturales = new ArrayList<>();

    }

    public static Empresa getInstance() {
        if(empresa == null){
            empresa = new Empresa();
        }

        return empresa;
    }


    public ClienteJuridico registrarClienteJuridico(String nombre, String apellido, String identificacion, String direccion, String telefono, String nit) throws AtributoVacioException {

        if(identificacion == null || identificacion.isBlank()){
            throw new AtributoVacioException("La identificacion es obligatoria");
        }
        if(nombre == null || nombre.isBlank()){
            throw new AtributoVacioException("El nombre es obligatorio");
        }

        if(telefono == null || telefono.isBlank()){
            throw new AtributoVacioException("El telefono es obligatorio");
        }

        if(nit == null || nit.isBlank()){
            throw new AtributoVacioException("El nit es obligatorio");
        }

        if( obtenerClienteJuridico(identificacion) != null ){
            throw new AtributoVacioException("La cédula "+identificacion+" ya está registrada");
        }

        //Demás validaciones

        ClienteJuridico cliente = ClienteJuridico.builder()
                .identificacion(identificacion)
                .nombre(nombre)
                .apellido(apellido)
                .direccion(direccion)
                .telefono(telefono)
                .build();

        clientesJuridicos.add(cliente);

        return cliente;
    }

    public ClienteJuridico obtenerClienteJuridico(String identificacion){
        return clientesJuridicos.stream().filter(c -> c.getIdentificacion().equals(identificacion)).findFirst().orElse(null);
    }

    public ClienteNatural registrarClienteNatural(String nombre, String apellido, String identificacion, String direccion, String telefono, String email, LocalDate fechaNacimiento) throws AtributoVacioException {

        if(identificacion == null || identificacion.isBlank()){
            throw new AtributoVacioException("La identificacion es obligatoria");
        }
        if(nombre == null || nombre.isBlank()){
            throw new AtributoVacioException("El nombre es obligatorio");
        }

        if(telefono == null || telefono.isBlank()){
            throw new AtributoVacioException("El telefono es obligatorio");
        }

        if(email == null || email.isBlank()){
            throw new AtributoVacioException("El email es obligatorio");
        }

        if( obtenerClienteNatural(identificacion) != null ){
            throw new AtributoVacioException("La cédula "+identificacion+" ya está registrada");
        }

        //Demás validaciones

        ClienteNatural cliente = ClienteNatural.builder()

                .nombre(nombre)
                .apellido(apellido)
                .identificacion(identificacion)
                .direccion(direccion)
                .telefono(telefono)
                .email(email)
                .fechaNacimiento(fechaNacimiento)

                .build();

        clientesNaturales.add(cliente);

        return cliente;
    }

    public ClienteNatural obtenerClienteNatural(String identificacion){
        return clientesNaturales.stream().filter(c -> c.getIdentificacion().equals(identificacion)).findFirst().orElse(null);
    }









}
