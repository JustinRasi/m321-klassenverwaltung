package ch.tbz.m321_klassenverwaltung.Repository;

import ch.tbz.m321_klassenverwaltung.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}