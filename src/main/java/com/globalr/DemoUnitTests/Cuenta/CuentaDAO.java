package com.globalr.DemoUnitTests.Cuenta;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CuentaDAO {

    @Id
    private Integer idCuenta;

    private String nroCuenta;

    private Double saldo;

}
