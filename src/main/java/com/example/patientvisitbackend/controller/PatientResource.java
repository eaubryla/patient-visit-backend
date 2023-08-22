package com.example.patientvisitbackend.controller;

import com.example.patientvisitbackend.model.Patient;
import com.example.patientvisitbackend.repository.PatientRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/patients")
public class PatientResource {

    @Inject
    PatientRepository patientRepository;

    @GET
    public Response getAllPatients() {
        return Response.ok(patientRepository.listAll()).build();
    }

    @GET
    @Path("/{patient_id}")
    public Response getPatientById(@PathParam("patient_id") Long patientId) {
        Patient patient = patientRepository.findById(patientId);
        if (patient != null) {
            return Response.ok(patient).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response createPatient(Patient patient) {
        patientRepository.persist(patient);
        return Response.status(Response.Status.CREATED).entity(patient).build();
    }

    @PUT
    @Path("/{patient_id}")
    @Transactional
    public Response updatePatient(@PathParam("patient_id") Long patientId, Patient patient) {
        Patient entity = patientRepository.findById(patientId);
        if (entity != null) {
            entity.setName(patient.getName());
            entity.setSurname(patient.getSurname());
            entity.setDateOfBirth(patient.getDateOfBirth());
            entity.setSocialSecurityNumber(patient.getSocialSecurityNumber());
            return Response.ok(entity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{patient_id}")
    @Transactional
    public Response deletePatient(@PathParam("patient_id") Long patientId) {
        Patient patient = patientRepository.findById(patientId);
        if (patient != null) {
            patientRepository.delete(patient);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
