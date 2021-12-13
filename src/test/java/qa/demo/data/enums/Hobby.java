package qa.demo.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Hobby {
    SPORTS("Sports"), READING("Reading"), MUSIC("Music");

    private String hobby;

    public static Hobby fromString(String hobbyString) {
        for (Hobby hobby : Hobby.values()) {
            if (hobby.hobby.equals(hobbyString)) {
                return hobby;
            }
        }
        return null;
    }
}
