package qa.demo.tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import qa.demo.data.RegistrationFormFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static io.qameta.allure.Allure.step;

class StudentRegistrationFormTest extends TestBase {

    @Test
    void registrationFormWithFakerTest() {
        var registrationData = RegistrationFormFactory.getAllRandomFormWithoutPicture();
        registrationData.setPictureName("Rajesh_Koothrappali.jpg");

        registrationPage
                .openPage()
                .assertOnRegistrationPage()
                .fillForm(registrationData)
                .submitForm();

        step("Проверяем результат отправки формы", () -> {
            var resultTable = registrationPage.assertOnFormSubmittingResult().getRegistrationResultTable();
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(resultTable.studentName)
                    .isEqualTo(registrationData.firstName + " " + registrationData.lastName);
            softly.assertThat(resultTable.studentEmail).isEqualTo(registrationData.email);
            softly.assertThat(resultTable.gender).isEqualTo(registrationData.gender);
            softly.assertThat(resultTable.mobile).isEqualTo(registrationData.mobilePhone);
            softly.assertThat(resultTable.dateOfBirth).isEqualTo(registrationData.birthday);
            softly.assertThat(resultTable.subjects).isEqualTo(registrationData.subjects);
            softly.assertThat(resultTable.hobbies).isEqualTo(registrationData.hobbies);
            softly.assertThat(resultTable.picture).isEqualTo(registrationData.pictureName);
            softly.assertThat(resultTable.address).isEqualTo(registrationData.currentAddress);
            softly.assertThat(resultTable.stateAndCity)
                    .isEqualTo(registrationData.state + " " + registrationData.city);
            softly.assertAll();
        });
    }

    @Test
    void registrationFormWithOnlyRequiredFieldsTest() {
        var registrationData = RegistrationFormFactory.getRequiredRandomForm();

        registrationPage
                .openPage()
                .assertOnRegistrationPage()
                .fillForm(registrationData)
                .submitForm();

        step("Проверяем результат отправки формы", () -> {
        var resultTable = registrationPage.assertOnFormSubmittingResult().getRegistrationResultTable();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(resultTable.studentName).as("student name")
                .isEqualTo(registrationData.firstName + " " + registrationData.lastName);
        softly.assertThat(resultTable.studentEmail).as("email").isEqualTo(registrationData.email);
        softly.assertThat(resultTable.gender).as("gender").isEqualTo(registrationData.gender);
        softly.assertThat(resultTable.mobile).as("mobile").isEqualTo(registrationData.mobilePhone);
        softly.assertThat(resultTable.dateOfBirth).as("birthday")
                .isEqualTo(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")));
        softly.assertThat(resultTable.subjects).as("subjects").isEqualTo(registrationData.subjects);
        softly.assertThat(resultTable.hobbies).as("hobbies").isEqualTo(registrationData.hobbies);
        softly.assertThat(resultTable.picture).as("picture").isEqualTo(registrationData.pictureName);
        softly.assertThat(resultTable.address).as("address").isEqualTo(registrationData.currentAddress);
        softly.assertThat(resultTable.stateAndCity).as("city and state")
                .isEqualTo(null);
        softly.assertAll();
        });
    }
}
