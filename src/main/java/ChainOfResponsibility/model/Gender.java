package ChainOfResponsibility.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Gender {
    Male("male"),
    Female("female");

    private String value;

    private Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static boolean isLegalGender(String gender) {
        Optional<Gender> result = Arrays.stream(Gender.values()).
                filter(g -> Objects.equals(g.getValue(), gender)).findAny();
        return result.isPresent();
    }

    public static boolean isNotLegalGender(String gender) {
        return !isLegalGender(gender);
    }
}
