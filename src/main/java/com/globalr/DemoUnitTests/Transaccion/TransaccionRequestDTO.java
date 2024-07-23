package com.globalr.DemoUnitTests.Transaccion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TransaccionRequestDTO {

    @Id
    private String idDeposito;

    private Integer idCuenta;
    private String nroCuenta;
    private Double montoTransaccion;

}
