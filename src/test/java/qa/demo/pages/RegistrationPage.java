package qa.demo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.demo.data.RegistrationFormData;
import qa.demo.data.RegistrationResultTable;
import qa.demo.data.enums.Gender;
import qa.demo.data.enums.Hobby;
import qa.demo.data.enums.Subject;
import qa.demo.data.enums.stateandcity.City;
import qa.demo.data.enums.stateandcity.State;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final String FORM_TITLE = "Student Registration Form";
    private final String SUBMITTING_RESULT_TITLE = "Thanks for submitting the form";
    private final String URL = "https://demoqa.com/automation-practice-form";

    private SelenideElement
            formTitle = $(".practice-form-wrapper");
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement emailInput = $("#userEmail");
    private SelenideElement phoneNumberInput = $("#userNumber");
    private SelenideElement resultSubmitFormTitle = $("#example-modal-sizes-title-lg");
    private SelenideElement resultTable = $(".table-responsive");

    @Step("открыть страницу заполнения формы")
    public RegistrationPage openPage() {
        open(URL);
        return this;
    }

    @Step("проверяем, что страница открыта")
    public RegistrationPage assertOnRegistrationPage() {
        formTitle.shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.val(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.val(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.val(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String phoneNumber) {
        phoneNumberInput.val(phoneNumber);
        return this;
    }

    public RegistrationPage setBirthDate(LocalDate birthDate) {
        var month = birthDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select")
                .selectOption(month);
        $(".react-datepicker__year-select").scrollTo().selectOption(String.valueOf(birthDate.getYear()));
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(String.valueOf(birthDate.getDayOfMonth()))).click();
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String pictureName) {
        $("#uploadPicture").uploadFromClasspath(pictureName);
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    public RegistrationPage setState(State state) {
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state.getState())).scrollIntoView(true).click();
        return this;
    }

    public RegistrationPage setCity(City city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city.getCity())).scrollIntoView(true).click();
        return this;
    }

    public RegistrationPage setCityAndState(String state, String city) {
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    @Step("Отправить форму")
    public void submitForm() {
        $("#submit").scrollTo().click();
    }

    @Step("Заполнение формы")
    public RegistrationPage fillForm(RegistrationFormData registrationFormData) {
        if (registrationFormData.getFirstName() != null) setFirstName(registrationFormData.getFirstName());
        if (registrationFormData.getLastName() != null) setLastName(registrationFormData.getLastName());
        if (registrationFormData.getEmail() != null) setEmail(registrationFormData.getEmail());
        if (registrationFormData.getGender() != null) setGender(registrationFormData.getGender().getGender());
        if (registrationFormData.getMobilePhone() != null) setPhoneNumber(registrationFormData.getMobilePhone());
        if (registrationFormData.getBirthday() != null) setBirthDate(registrationFormData.getBirthday());
        if (registrationFormData.getHobbies() != null)
            registrationFormData.getHobbies().forEach(hobby -> setHobby(hobby.getHobby()));
        if (registrationFormData.getPictureName() != null) uploadPicture(registrationFormData.getPictureName());
        if (registrationFormData.getSubjects() != null)
            registrationFormData.getSubjects().forEach(subject -> setSubject(subject.getSubject()));
        if (registrationFormData.getCurrentAddress() != null)
            setCurrentAddress(registrationFormData.getCurrentAddress());
        if (registrationFormData.getState() != null) setState(registrationFormData.getState());
        if (registrationFormData.getCity() != null) setCity(registrationFormData.getCity());
        return this;
    }

    public RegistrationPage assertOnFormSubmittingResult() {
        resultSubmitFormTitle.shouldHave(text(SUBMITTING_RESULT_TITLE));
        return this;
    }

    public RegistrationResultTable getRegistrationResultTable() {
        LocalDate birthDate = null;
        try {
            birthDate = LocalDate.parse(getTableValueByLabel("Date of Birth"),
                    DateTimeFormatter.ofPattern("dd MMMM,yyyy").withLocale(Locale.ENGLISH));
        } catch (Exception ignored) {
        }
        String[] subjectsArray = (getTableValueByLabel("Subjects") != null)
                ? getTableValueByLabel("Subjects").split(",")
                : new String[0];
        String[] hobbiesArray = (getTableValueByLabel("Hobbies") != null)
                ? getTableValueByLabel("Hobbies").split(",")
                : new String[0];

        return RegistrationResultTable.builder()
                .studentName(getTableValueByLabel("Student Name"))
                .studentEmail(getTableValueByLabel("Student Email"))
                .gender(Gender.fromString(getTableValueByLabel("Gender")))
                .mobile(getTableValueByLabel("Mobile"))
                .dateOfBirth(birthDate)
                .subjects(Arrays.stream(subjectsArray)
                        .map(Subject::fromString)
                        .collect(Collectors.toList()))
                .hobbies(Arrays.stream(hobbiesArray)
                        .map(Hobby::fromString)
                        .collect(Collectors.toList()))
                .picture(getTableValueByLabel("Picture"))
                .address(getTableValueByLabel("Address"))
                .stateAndCity(getTableValueByLabel("State and City"))
                .build();
    }

    private String getTableValueByLabel(String label) {
        var value = resultTable.$(byText(label)).sibling(0).getText();
        return (value.isEmpty()) ? null : value;
    }

}
