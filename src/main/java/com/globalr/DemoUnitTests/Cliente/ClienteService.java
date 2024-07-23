package com.globalr.DemoUnitTests.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    public List<Cliente> findAll();

    public Optional<Cliente> findById(Integer id);

    public Cliente save(Cliente cliente);

    public Cliente update(Cliente cliente);
}
