package com.example.patientvisitbackend;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name="Extra Menu", description="This API has nothing yet")
        },
        info = @Info(
                title="Java Test API",
                version = "1.0.1",
                contact = @Contact(
                        name = "Eric AUBRY",
                        email = "eaubryla@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class Main extends Application {
}