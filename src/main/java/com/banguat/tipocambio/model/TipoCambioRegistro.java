package com.banguat.tipocambio.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tipo_cambio_registros")
public class TipoCambioRegistro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_solicitud")
    private String numeroSolicitud;
    
    @Column(name = "tipo_cambio")
    private String tipoCambio;
    
    @Column(name = "fecha_consulta")
    private LocalDateTime fechaConsulta;
    
    @PrePersist
    protected void onCreate() {
        fechaConsulta = LocalDateTime.now();
    }
}