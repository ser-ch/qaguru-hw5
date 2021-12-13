package qa.demo.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import qa.demo.data.enums.Gender;
import qa.demo.data.enums.Hobby;
import qa.demo.data.enums.Subject;
import qa.demo.data.enums.stateandcity.City;
import qa.demo.data.enums.stateandcity.State;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder()
@FieldDefaults(level = AccessLevel.PUBLIC)
public class RegistrationFormData {
    String
            firstName,
            lastName,
            email,
            mobilePhone,
            currentAddress;
    Gender gender;
    State state;
    City city;
    @Builder.Default
    List<Hobby> hobbies = new ArrayList<Hobby>();
    String pictureName;
    @Builder.Default
    List<Subject> subjects = new ArrayList<Subject>();
    LocalDate birthday;
}
