package es.uca.cromuca;

import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.vaadin.artur.helpers.LaunchUtil;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

    @EnableJpaRepositories
    public class Config {
    }

    @Bean
    public CommandLineRunner loadData(UsuarioService usuarioService) {
        return (args) -> {
            try {
                boolean valido = usuarioService.loadUserByUsername("admin").getRole().equals("Admin");
            } catch (UsernameNotFoundException e) {
                Usuario u = new Usuario();
                u.setNombre("admin");
                u.setPassword("admin");
                u.setApellido("admin");
                u.setEmail("admin@admin.es");
                u.setUsername("admin");
                u.setRole("Admin");
                usuarioService.create(u);
            }
            try {
                boolean valido = usuarioService.loadUserByUsername("usuario").getRole().equals("User");
            } catch (UsernameNotFoundException e) {
                Usuario u = new Usuario();
                u.setNombre("usuario");
                u.setPassword("user1234");
                u.setApellido("usuario");
                u.setEmail("usuario@usuario.es");
                u.setUsername("user");
                u.setRole("User");
                usuarioService.create(u);
            }
        };
    }

}
