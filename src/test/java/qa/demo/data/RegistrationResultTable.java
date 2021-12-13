package qa.demo.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import qa.demo.data.enums.Gender;
import qa.demo.data.enums.Hobby;
import qa.demo.data.enums.Subject;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class RegistrationResultTable {
    String studentName,
            studentEmail,
            mobile,
            picture,
            address,
            stateAndCity;

    Gender gender;
    LocalDate dateOfBirth;
    List<Subject> subjects;
    List<Hobby> hobbies;
}
