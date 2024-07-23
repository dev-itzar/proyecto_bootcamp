package com.globalr.DemoUnitTests.Transaccion;

import lombok.Data;

@Data
public class TransaccionResposeDTO {

    private Integer idCuenta;
    private String nroCuenta;
    private Double saldoAnterior;
    private Double saldoNuevo;

}
