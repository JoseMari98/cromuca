package es.uca.cromuca.entities;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
public class Usuario implements UserDetails {
    @Id //esto sirve para decir cual es el id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String nombre = "", apellido = "", password = "";
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique = true)
    private String username = "";
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique = true)
    private String email = "";
    private String role = "";
    private double presupuestoPlato;

    //Getters
    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public double getPresupuestoPlato() {
        return presupuestoPlato;
    }

    //Setters
    public void setId(Long Id) {
        this.id = Id;
    }

    public void setApellido(String Apellido1) {
        this.apellido = Apellido1;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setPassword(String Contrasena) {
        this.password = Contrasena;
    }

    public void setUsername(String nombreUsuario) {
        this.username = nombreUsuario;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPresupuestoPlato(double presupuestoPlato) {
        this.presupuestoPlato = presupuestoPlato;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}