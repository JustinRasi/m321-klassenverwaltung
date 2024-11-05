package ch.tbz.m321_klassenverwaltung.Repository;

import ch.tbz.m321_klassenverwaltung.Model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {}
