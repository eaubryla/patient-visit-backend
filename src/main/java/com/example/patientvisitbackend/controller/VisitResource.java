package com.example.patientvisitbackend.controller;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.example.patientvisitbackend.model.Visit;
import com.example.patientvisitbackend.repository.VisitRepository;
import com.example.patientvisitbackend.repository.PatientRepository;

import java.util.List;

@Path("/visits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VisitResource {

    @Inject
    EntityManager entityManager;

    @Inject
    VisitRepository visitRepository;

    @Inject
    PatientRepository patientRepository;

    @GET
    public Response getAllVisits() {
        return Response.ok(visitRepository.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getVisitById(@PathParam("id") Long id) {
        Visit visit = visitRepository.findById(id);
        if (visit != null) {
            return Response.ok(visit).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response createVisit(Visit visit) {
        visitRepository.persist(visit);
        return Response.status(Response.Status.CREATED).entity(visit).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateVisit(@PathParam("id") Long id, Visit visit) {
        Visit entity = visitRepository.findById(id);
        if (entity != null) {
            entity.setDate(visit.getDate());
            entity.setVisitType(visit.getVisitType());
            entity.setVisitReason(visit.getVisitReason());
            entity.setComments(visit.getComments());
            entity.setPatientId(visit.getPatientId());
            return Response.ok(entity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteVisit(@PathParam("id") Long id) {
        Visit visit = visitRepository.findById(id);
        if (visit != null) {
            visitRepository.delete(visit);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/patient/{patientId}")
    public List<Visit> getVisitsByPatient(@PathParam("patientId") Long patientId) {
        return entityManager.createQuery("SELECT v FROM Visit v WHERE v.patientId = :patientId", Visit.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }
}
