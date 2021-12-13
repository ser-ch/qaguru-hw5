package qa.demo.data.enums.stateandcity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static qa.demo.data.enums.stateandcity.City.*;

@Getter
@AllArgsConstructor
public enum State {
    NCR("NCR", List.of(Delhi, Gurgaon, Noida)),
    Uttar_Pradesh("Uttar Pradesh", List.of(Agra, Lucknow, Merrut)),
    Haryana("Haryana", List.of(Karnal, Panipat)),
    Rajasthan("Rajasthan", List.of(Jaipur, Jaiselmer));

    private final String state;
    private final List<City> allowedCities;
}
