package com.devgabriel.dglearn.repositories;

import com.devgabriel.dglearn.entities.Enrollment;
import com.devgabriel.dglearn.entities.pk.EnrollmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPK> {
}
