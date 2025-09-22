package org.springframework.samples.petclinic.ArchUnitTests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.Test;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import org.mapstruct.Mapper;



public class SpecialtyTest {

    private static final String MAINPACKAGE = "org.springframework.samples.petclinic";
    JavaClasses importedClasses = new ClassFileImporter().importPackages(MAINPACKAGE);

    // Package Dependency
    @Test
    public void domainPackageShouldNotDependOnAnyPackage() {
        noClasses()
            .that().haveNameMatching(MAINPACKAGE + ".model.Specialty")
            .should().dependOnClassesThat()
            .resideInAnyPackage(
                MAINPACKAGE + ".controller..",
                MAINPACKAGE + ".repository..",
                MAINPACKAGE + ".dto..")
            .check(importedClasses);
    }

    // Class and Package Containment
    @Test
    public void classAndPackageContainment() {
        classes()
            .that()
            .haveSimpleName("SpecialtyRestController")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.controller..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("SpecialtyRepository")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("JpaSpecialtyRepositoryImpl")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository.jpa..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("SpecialtyDto")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.dto..")
            .check(importedClasses);
    }

    // Inheritance
    @Test
    public void checkSpecialtyRepositoryClassInheritance() {
        classes()
            .that().resideInAnyPackage(MAINPACKAGE + ".repository.jpa..")
            .and().haveSimpleNameContaining("JpaSpecialtyRepository")
            .should().beAssignableTo(SpecialtyRepository.class)
            .check(importedClasses);
    }

    // Annotation
    @Test
    public void checkSpecialtyRestControllerAnnotation() {
        classes()
            .that().haveSimpleName("SpecialtyRestController")
            .should().beAnnotatedWith(RestController.class)
            .check(importedClasses);
    }

    // Layer
    @Test
    public void checkLayeredArchitecture() {
        layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("Controller").definedBy(MAINPACKAGE + ".rest.controller..")
            .layer("DTO").definedBy(MAINPACKAGE + ".rest.dto..")
            .layer("Model").definedBy(MAINPACKAGE + ".model..")
            .layer("Repository").definedBy(MAINPACKAGE + ".repository..")
            .whereLayer("Controller").mayOnlyAccessLayers("DTO", "Repository", "Model")
            .whereLayer("DTO").mayOnlyAccessLayers("Model")
            .whereLayer("Model").mayNotAccessAnyLayer()
            .whereLayer("Repository").mayOnlyAccessLayers("Model")
            .check(importedClasses);
    }

    
}
