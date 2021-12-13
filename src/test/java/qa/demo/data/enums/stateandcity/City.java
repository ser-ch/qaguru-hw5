package qa.demo.data.enums.stateandcity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum City {
    Delhi("Delhi"), Gurgaon("Gurgaon"), Noida("Noida"),
    Agra("Agra"), Lucknow("Lucknow"), Merrut("Merrut"),
    Karnal("Karnal"), Panipat("Panipat"),
    Jaipur("Jaipur"), Jaiselmer("Jaiselmer");

    private final String city;
}
