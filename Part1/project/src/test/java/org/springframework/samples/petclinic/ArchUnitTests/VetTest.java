package org.springframework.samples.petclinic.ArchUnitTests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.Test;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class VetTest {

    private static final String MAINPACKAGE = "org.springframework.samples.petclinic";
    JavaClasses importedClasses = new ClassFileImporter().importPackages(MAINPACKAGE);

    /*** Package Dependency ***/
    @Test
    public void domainPackageShouldNotDependOnAnyPackage() {

        noClasses()
            .that().haveNameMatching(MAINPACKAGE + ".model.Vet")
            .should().dependOnClassesThat()
            .resideInAnyPackage(
                MAINPACKAGE + ".controller..",
                MAINPACKAGE + ".repository..",
                MAINPACKAGE + ".dto..")
            .check(importedClasses);
    }


    @Test
    public void repositoryShouldOnlyDependOnDTOAndPersistence() {

        classes()
            .that().haveNameMatching(MAINPACKAGE + ".repository.VetRepository")
            .should().dependOnClassesThat()
            .resideInAnyPackage( MAINPACKAGE + ".model..", MAINPACKAGE + ".jpa..")
            .check(importedClasses);
    }


    /*** Class Dependency ***/
    @Test
    public void vetClassesShouldOnlyHaveDependenciesOnItsClasses() {

        classes()
            .that().haveNameMatching(".*Vet*")
            .should().onlyHaveDependentClassesThat()
            .haveSimpleNameContaining("Vet")
            .check(importedClasses);
    }


    /*** Class and package containment ***/
    @Test
    public void classAndPackageContainment() {
        classes()
            .that()
            .haveSimpleName("VetRestController")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.controller..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("VetRepository")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("JpaVetRepositoryImpl")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository.jpa..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("VetDto")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.dto..")
            .check(importedClasses);
    }

    /*** Inheritance ***/
    @Test
    public void checkVetRepositoryClassInheritance() {

        classes()
            .that().resideInAnyPackage(MAINPACKAGE + ".repository.jpa..")
            .and().haveSimpleNameContaining("JpaVetRepository")
            .should().beAssignableTo(VetRepository.class)
            .check(importedClasses);
    }

    @Test
    public void vetClassesShouldInheritFromBaseClass() {
        classes()
            .that().haveSimpleName("Vet")
            .should().beAssignableTo(Person.class).check(importedClasses);
    }


    /*** Annotation ***/
    @Test
    public void checkVetRestControllerAnnotation() {
        classes()
            .that().haveSimpleName("VetRestController")
            .should().beAnnotatedWith(RestController.class)
            .check(importedClasses);
    }



    /*** Layer ***/
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
            .whereLayer("Repository").mayOnlyAccessLayers( "Model")
            .check(importedClasses);
    }


    /*** Cycle ***/
    @Test
    public void checkCycles() {
        slices().matching(MAINPACKAGE + ".(*)..")
            .should().beFreeOfCycles()
            .check(importedClasses);
    }

}
