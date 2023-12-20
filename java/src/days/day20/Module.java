package days.day20;

import java.util.Arrays;
import java.util.List;

public record Module(Type t, String source, List<String> targets) {

    public Module(final String input) {
        this(switch (input.charAt(0)) {
                    case '%' -> Type.FLIPFLOP;
                    case '&' -> Type.CONJUNCTION;
                    default -> Type.BROADCAST;
                },
                input.split(" -> ")[0].replaceAll("[&%]", ""),
                Arrays.asList(input.split(" -> ")[1].split(", ")));
    }
}