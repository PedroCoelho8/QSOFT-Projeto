package org.springframework.samples.petclinic.ArchUnitTests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.Test;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetTypeRepository;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class PetTypeTest {

    private static final String MAINPACKAGE = "org.springframework.samples.petclinic";
    JavaClasses importedClasses = new ClassFileImporter().importPackages(MAINPACKAGE);

    //Package Dependency
    @Test
    public void domainPackageShouldNotDependOnAnyPackage() {

        noClasses()
            .that().haveNameMatching(MAINPACKAGE + ".model.PetType")
            .should().dependOnClassesThat()
            .resideInAnyPackage(
                MAINPACKAGE + ".controller..",
                MAINPACKAGE + ".repository..",
                MAINPACKAGE + ".dto..")
            .check(importedClasses);
    }


    //Class Dependency
    @Test
    public void petTypeClassesShouldOnlyHaveDependenciesOnItsClasses() {

        classes()
            .that().haveNameMatching(".*PetType*")
            .should().onlyHaveDependentClassesThat()
            .haveSimpleNameContaining("PetType")
            .check(importedClasses);
    }

    // Class and Package Containment
    @Test
    public void classAndPackageContainment() {
        classes()
            .that()
            .haveSimpleName("PetTypeRestController")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.controller..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("PetTypeRepository")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("JpaPetTypeRepositoryImpl")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository.jpa..")
            .check(importedClasses);

        classes()
            .that()
            .haveSimpleName("PetTypeDto")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.dto..")
            .check(importedClasses);
    }

    // Inheritance
    @Test
    public void checkPetTypeRepositoryClassInheritance() {

        classes()
            .that().resideInAnyPackage(MAINPACKAGE + ".repository.jpa..")
            .and().haveSimpleNameContaining("JpaPetTypeRepository")
            .should().beAssignableTo(PetTypeRepository.class)
            .check(importedClasses);
    }

    @Test
    public void petTypeClassesShouldInheritFromBaseClass() {
        classes()
            .that().haveSimpleName("PetType")
            .should().beAssignableTo(PetType.class).check(importedClasses);
    }

    // Annotation
    @Test
    public void checkPetTypeRestControllerAnnotation() {
        classes()
            .that().haveSimpleName("PetTypeRestController")
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

    // Cycle 
    @Test
    public void checkCycles() {
        slices().matching(MAINPACKAGE + ".(*)..")
            .should().beFreeOfCycles()
            .check(importedClasses);
    }

}
