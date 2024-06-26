package backendc3.Clinica_Odontologica.security;

import backendc3.Clinica_Odontologica.entity.Usuario;
import backendc3.Clinica_Odontologica.entity.UsuarioRole;
import backendc3.Clinica_Odontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Crear usuario con rol USER
        String passSinCifrarUser = "user";
        String passCifradoUser = passwordEncoder.encode(passSinCifrarUser);
        Usuario usuarioUser = new Usuario("Usuario", "usuario", "user@example.com", passCifradoUser, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioUser);

        // Crear usuario con rol ADMIN
        String passSinCifrarAdmin = "admin";
        String passCifradoAdmin = passwordEncoder.encode(passSinCifrarAdmin);
        Usuario usuarioAdmin = new Usuario("Admin", "admin", "admin@admin.com", passCifradoAdmin, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }
}