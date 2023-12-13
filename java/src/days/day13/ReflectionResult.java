package days.day13;

public record ReflectionResult(ReflectionType reflectionType, int reflectionLocation) {

    @Override
    public boolean equals(Object obj) {
        ReflectionResult other = (ReflectionResult) obj;
        return other.reflectionType == this.reflectionType && other.reflectionLocation == this.reflectionLocation;
    }
}
