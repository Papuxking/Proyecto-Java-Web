package grupo3.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DataSource dataSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT correo, password FROM usuarios WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String correo = resultSet.getString("correo");
                String password = resultSet.getString("password");

                return User.withUsername(correo)
                        .password(password) // Toma la contraseña sin encriptar
                        .roles("USER")
                        .build();
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la base de datos", e);
        }
    }
}
