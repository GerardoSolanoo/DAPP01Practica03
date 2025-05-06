/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DAPP01Practica03;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 *
 * @author sgerardov
 */
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable{
    @Id
    @GeneratedValue(    generator = "empleados_clave_seq",
                        strategy = GenerationType.SEQUENCE
                    )
    @SequenceGenerator( name = "empleados_clave_seq", 
                        sequenceName = "empleados_clave_seq", 
                        initialValue = 1, 
                        allocationSize= 1
                        )
    @Column()
    private Long clave;
    
    @Column()
    private String nombre;
    
    @Column()
    private String direccion;
    
    @Column()
    private String telefono;
    
    @ManyToOne
    @JoinColumn(name = "deptoid")
    private Departamento departamento;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
