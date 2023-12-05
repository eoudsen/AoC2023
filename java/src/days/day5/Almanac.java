package days.day5;

import java.util.List;
import java.util.TreeSet;

public class Almanac {

    private final Mapping seedToSoil = new Mapping();
    private final Mapping soilToFertilizer = new Mapping();
    private final Mapping fertilizerToWater = new Mapping();
    private final Mapping waterToLight = new Mapping();
    private final Mapping lightToTemperature = new Mapping();
    private final Mapping temperatureToHumidity = new Mapping();
    private final Mapping humidityToLocation = new Mapping();

    public Almanac(final List<String> mappingInputs) {
        Mapping currentMapping = null;
        for (String mappingInput : mappingInputs) {
            if (mappingInput.contains("seed-to-soil")) {
                currentMapping = seedToSoil;
                continue;
            }
            if (mappingInput.contains("soil-to-fertilizer")) {
                currentMapping = soilToFertilizer;
                continue;
            }
            if (mappingInput.contains("fertilizer-to-water")) {
                currentMapping = fertilizerToWater;
                continue;
            }
            if (mappingInput.contains("water-to-light")) {
                currentMapping = waterToLight;
                continue;
            }
            if (mappingInput.contains("light-to-temperature")) {
                currentMapping = lightToTemperature;
                continue;
            }
            if (mappingInput.contains("temperature-to-humidity")) {
                currentMapping = temperatureToHumidity;
                continue;
            }
            if (mappingInput.contains("humidity-to-location")) {
                currentMapping = humidityToLocation;
                continue;
            }
            if (mappingInput.isEmpty()) {
                continue;
            }
            if (currentMapping != null) {
                currentMapping.addInput(mappingInput);
            }
        }
    }

    public Long seedToSoil(final Long seedNumber) {
        return seedToSoil.getMapping(seedNumber);
    }

    public Long soilToFertilizer(final Long soilNumber) {
        return soilToFertilizer.getMapping(soilNumber);
    }

    public Long fertilizerToWater(final Long fertilizerNumber) {
        return fertilizerToWater.getMapping(fertilizerNumber);
    }

    public Long waterToLight(final Long waterNumber) {
        return waterToLight.getMapping(waterNumber);
    }

    public Long lightToTemperature(final Long lightNumber) {
        return lightToTemperature.getMapping(lightNumber);
    }

    public Long temperatureToHumidity(final Long temperatureNumber) {
        return temperatureToHumidity.getMapping(temperatureNumber);
    }

    public Long humidityToLocation(final Long humidityNumber) {
        return humidityToLocation.getMapping(humidityNumber);
    }

    public TreeSet<Range> seedToSoil(final TreeSet<Range> seedNumber) {
        return seedToSoil.getMapping(seedNumber);
    }

    public TreeSet<Range> soilToFertilizer(final TreeSet<Range> soilNumber) {
        return soilToFertilizer.getMapping(soilNumber);
    }

    public TreeSet<Range> fertilizerToWater(final TreeSet<Range> fertilizerNumber) {
        return fertilizerToWater.getMapping(fertilizerNumber);
    }

    public TreeSet<Range> waterToLight(final TreeSet<Range> waterNumber) {
        return waterToLight.getMapping(waterNumber);
    }

    public TreeSet<Range> lightToTemperature(final TreeSet<Range> lightNumber) {
        return lightToTemperature.getMapping(lightNumber);
    }

    public TreeSet<Range> temperatureToHumidity(final TreeSet<Range> temperatureNumber) {
        return temperatureToHumidity.getMapping(temperatureNumber);
    }

    public TreeSet<Range> humidityToLocation(final TreeSet<Range> humidityNumber) {
        return humidityToLocation.getMapping(humidityNumber);
    }
}
