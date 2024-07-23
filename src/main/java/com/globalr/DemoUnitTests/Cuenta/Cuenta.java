package com.globalr.DemoUnitTests.Cuenta;

import com.globalr.DemoUnitTests.Cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Cuenta {

    @Id
    private Integer idCuenta;
    private String nroCuenta;
    private Double saldoApertura;
    private Double saldo;
    private String status;
    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public Double getSaldoApertura() {
        return saldoApertura;
    }

    public void setSaldoApertura(Double saldoApertura) {
        this.saldoApertura = saldoApertura;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
