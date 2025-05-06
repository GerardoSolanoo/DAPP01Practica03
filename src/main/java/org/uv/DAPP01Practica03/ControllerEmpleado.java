/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package org.uv.DAPP01Practica03;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author sgerardov
 */
@RestController
@RequestMapping("/empleados")
public class ControllerEmpleado {
    
    @Autowired
    RepositoryEmpleado repositoryEmpleado;
    
    @Autowired
    RepositoryDepartamento repositoryDepartamento;
    
    @GetMapping()
    public List<Empleado> list() {
        return repositoryEmpleado.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> get(@PathVariable Long id) {
        Optional<Empleado> resEmp = repositoryEmpleado.findById(id);
        if(resEmp.isPresent())
            return ResponseEntity.ok(resEmp.get());
        else
            return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> put(@PathVariable Long id, @RequestBody Empleado input) {
        Optional<Empleado> resEmp = repositoryEmpleado.findById(id);
        if(resEmp.isPresent()){
            Empleado empToEdit= resEmp.get();
            empToEdit.setNombre(input.getNombre());
            empToEdit.setDireccion(input.getDireccion());
            empToEdit.setTelefono(input.getTelefono());
            Empleado empEdited = repositoryEmpleado.save(empToEdit);
            return ResponseEntity.ok(empEdited);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Empleado> post(@RequestBody Empleado input) {
        Optional<Departamento> depto = repositoryDepartamento.findById(input.getDepartamento().getClave());
        if (depto.isPresent()){
            Empleado empNew = repositoryEmpleado.save(input);
            input.setDepartamento(depto.get());
            return ResponseEntity.ok(empNew);
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> delete(@PathVariable Long id) {
        Optional<Empleado> resEmp = repositoryEmpleado.findById(id);
        if(resEmp.isPresent()){
            Empleado empDeleted = resEmp.get();
            repositoryEmpleado.delete(empDeleted);
            return ResponseEntity.ok(empDeleted);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
