package com.globalr.DemoUnitTests.Transaccion;

public interface TransaccionService {

    public TransaccionResposeDTO crearDeposito(TransaccionRequestDTO transaccionRequestDTO);

    public TransaccionResposeDTO crearRetiro(TransaccionRequestDTO transaccionRequestDTO);

}
