package ventasR.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteJuridico {

    private String nombre;
    private String apellido;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String nit;



}
