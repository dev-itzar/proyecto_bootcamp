package com.globalr.DemoUnitTests.Transaccion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<TransaccionRequestDTO, String> {
}
