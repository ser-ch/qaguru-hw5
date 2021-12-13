package qa.demo.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private String gender;

    public static Gender fromString(String gender) {
        for (Gender i : Gender.values()) {
            if (i.gender.equals(gender)) {
                return i;
            }
        }
        return null;
    }
}
