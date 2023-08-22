package com.example.patientvisitbackend.repository;

import com.example.patientvisitbackend.model.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
}