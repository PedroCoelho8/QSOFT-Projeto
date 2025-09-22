package org.springframework.samples.petclinic.ArchUnitTests;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;


public class ArchitectureTest {

    @Test
    public void controllersShouldUseRepositoryWrappersDtos(){
        classes()
            .that()
            .resideInAPackage("..controller..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..repository..", "..dto..");
    }


    @Test
    public void controllerShouldOnlyDependOnService(){
        classes()
            .that()
            .resideInAPackage("..controller..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..service..", "..dto..");
    }

    @Test
    public void persistenceShouldNotBeDependendByOtherClasses() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.springframework.samples.petclinic");

        ArchRule rule = noClasses()
            .that()
            .resideInAnyPackage("..jpa..", "..model..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..dto..", "..controller..");

        rule.check(importedClasses);

    }

    @Test
    public void noCircularDependencies(){
        noClasses()
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..controller..");
    }

    @Test
    public void otherLayersShouldDependPersistence(){
        classes()
            .that()
            .resideInAnyPackage("..service..", "..controller..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..jpa..", "..model..");
    }

}


