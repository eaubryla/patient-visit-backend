package com.example.patientvisitbackend;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

    @QuarkusTest
    public class PatientResourceIT {

        @Test
        public void testGetAllPatients() {
            given()
                    .when().get("/patients")
                    .then()
                    .statusCode(200);
        }

        @Test
        public void testGetPatientById() {
            Long patientId = 1L;
            given()
                    .pathParam("patient_id", patientId)
                    .when().get("/patients/{patient_id}")
                    .then()
                    .statusCode(200);
        }
}
