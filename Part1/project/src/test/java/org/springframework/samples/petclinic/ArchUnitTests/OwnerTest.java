package org.springframework.samples.petclinic.ArchUnitTests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class OwnerTest {

    private static final String MAINPACKAGE = "org.springframework.samples.petclinic";
    JavaClasses importedClasses = new ClassFileImporter().importPackages(MAINPACKAGE);

    /*** Class Dependency ***/
    @Test
    public void ownerClassesShouldOnlyHaveDependenciesOnItsClasses() {

        ArchRule rule = classes()
            .that()
            .haveNameMatching(".*Owner")
            .should()
            .onlyHaveDependentClassesThat()
            .haveSimpleNameContaining("Owner");

        rule.check(importedClasses);
    }


    /*** Class and package containment ***/
    @Test
    public void classAndPackageContainment() {
        ArchRule one = classes()
            .that()
            .haveSimpleName("OwnerRestController")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.controller..");

        ArchRule two = classes()
            .that()
            .haveSimpleName("OwnerRepository")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository..");

        ArchRule three = classes()
            .that()
            .haveSimpleName("JpaOwnerRepositoryImpl")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".repository.jpa..");

        ArchRule four = classes()
            .that()
            .haveSimpleName("OwnerDto")
            .should()
            .resideInAnyPackage(MAINPACKAGE + ".rest.dto..");

        one.check(importedClasses);
        two.check(importedClasses);
        three.check(importedClasses);
        four.check(importedClasses);
    }


    /*** Package Dependency ***/
    @Test
    public void domainPackageShouldNotDependOnAnyPackage() {

        ArchRule rule = noClasses()
            .that()
            .haveNameMatching(MAINPACKAGE + ".model.Owner")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                MAINPACKAGE + ".controller..",
                MAINPACKAGE + ".repository..",
                MAINPACKAGE + ".dto..");

        rule.check(importedClasses);
    }

    @Test
    public void repositoryShouldOnlyDependOnDTOAndPersistence() {

        ArchRule rule = classes()
            .that()
            .haveNameMatching(MAINPACKAGE + ".repository.OwnerRepository")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage( MAINPACKAGE + ".model..", MAINPACKAGE + ".jpa..");

        rule.check(importedClasses);
    }


    /*** Inheritance ***/
    @Test
    public void checkRepositoryClassesInheritance() {

        ArchRule rule = classes()
            .that()
            .resideInAnyPackage(MAINPACKAGE + ".repository.jpa..").and()
            .haveSimpleNameContaining("JpaRepository")
            .should().beAssignableTo(JpaRepository.class);

        rule.check(importedClasses);
    }

    @Test
    public void checkControllersAnnotations() {

        ArchRule rule = classes()
            .that()
            .resideInAnyPackage(MAINPACKAGE + ".controller..")
            .should()
            .beAnnotatedWith(RestController.class);

        rule.check(importedClasses);
    }


    @Test
    public void checkLayeredArchitecture() {

        ArchRule rule = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("Controller").definedBy(MAINPACKAGE + ".rest.controller..")
            .layer("DTO").definedBy(MAINPACKAGE + ".rest.dto..")
            .layer("Model").definedBy(MAINPACKAGE + ".model..")
            .layer("Repository").definedBy(MAINPACKAGE + ".repository..")
            .whereLayer("Controller").mayOnlyAccessLayers("DTO", "Repository", "Model")
            .whereLayer("DTO").mayOnlyAccessLayers("Model")
            .whereLayer("Model").mayNotAccessAnyLayer()
            .whereLayer("Repository").mayOnlyAccessLayers( "Model");

        rule.check(importedClasses);
    }


    /*** Cycle ***/
    @Test
    public void checkCycles() {
        ArchRule rule = slices().matching(MAINPACKAGE + ".(*)..").should().beFreeOfCycles();
        rule.check(importedClasses);
    }

}
