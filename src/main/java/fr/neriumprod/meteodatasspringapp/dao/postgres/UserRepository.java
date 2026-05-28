package fr.neriumprod.meteodatasspringapp.dao.postgres;

import fr.neriumprod.meteodatasspringapp.entities.postgres.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository("postgresUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByUsernameAndPassword(String username, String password);

    User findById(long id);

    boolean existsById(long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Collection<User> findByBirthDate(Date birthDate);
}
