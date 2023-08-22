package com.example.patientvisitbackend.repository;

import com.example.patientvisitbackend.model.Visit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VisitRepository implements PanacheRepository<Visit> {
}