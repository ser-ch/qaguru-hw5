package qa.demo.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;
import qa.demo.data.enums.Gender;
import qa.demo.data.enums.Hobby;
import qa.demo.data.enums.Subject;
import qa.demo.data.enums.stateandcity.City;
import qa.demo.data.enums.stateandcity.State;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
public class RegistrationFormFactory {
    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static RegistrationFormData getAllRandomFormWithoutPicture() {
        var state = faker.options().option(State.values());
        return RegistrationFormData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option(Gender.values()))
                .mobilePhone(faker.numerify("##########"))
                .birthday(LocalDate.ofInstant(
                        faker.date().birthday().toInstant(), ZoneId.of("UTC")))
                .subjects(List.of(faker.options().option(Subject.values())))
                .hobbies(List.of(faker.options().option(Hobby.values())))
                .currentAddress(faker.address().fullAddress())
                .state(state)
                .city(faker.options().option(state.getAllowedCities().toArray(new City[0])))
                .build();
    }

    public static RegistrationFormData getRequiredRandomForm() {
        return RegistrationFormData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .gender(faker.options().option(Gender.values()))
                .mobilePhone(faker.numerify("##########"))
                .build();
    }
}
