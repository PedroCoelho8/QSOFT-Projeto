package org.springframework.samples.petclinic.ArchUnitTests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

public class PetTest {

/*
* Annotation
*/

 @Test
    void petRestControllerShouldBeAnnotatedWithRestController() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleNameEndingWith("RestController")
            .should().beAnnotatedWith(RestController.class)
            .check(importedClasses);
    }

    @Test
    void petClassShouldNotBeAnnotatedWithRestController() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleName("Pet")
            .should().notBeAnnotatedWith(RestController.class)
            .check(importedClasses);
    }

    @Test
    void petMapperShouldNotBeAnnotatedWithSpringAnnotations() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleNameEndingWith("Mapper")
            .should().notBeAnnotatedWith(org.springframework.stereotype.Repository.class)
            .andShould().notBeAnnotatedWith(org.springframework.web.bind.annotation.RestController.class)
            .check(importedClasses);
    }

    @Test
    void petDtoShouldNotBeAnnotatedWithSpringAnnotations() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleNameEndingWith("Dto")
            .should().notBeAnnotatedWith(org.springframework.stereotype.Service.class)
            .andShould().notBeAnnotatedWith(org.springframework.web.bind.annotation.RestController.class)
            .check(importedClasses);
    }


/*
* Package Dependency
*/

    @Test
    void petClassShouldBeInModelPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleName("Pet") 
            .should().resideInAPackage("..model..")  
            .check(importedClasses);
    }

    @Test
    void petRestControllerShouldBeInControllerPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleName("PetRestController") 
            .should().resideInAPackage("..controller..")  
            .check(importedClasses);
    }

    @Test
    void petDtoShouldBeInDtoPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleName("PetDto")  
            .should().resideInAPackage("..dto..")  
            .check(importedClasses);
    }

    @Test
    void petMapperShouldBeInMapperPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRuleDefinition.classes()
            .that().haveSimpleName("PetMapper") 
            .should().resideInAPackage("..mapper..") 
            .check(importedClasses);
    }
}
