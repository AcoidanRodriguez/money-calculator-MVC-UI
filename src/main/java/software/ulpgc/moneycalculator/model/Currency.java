package software.ulpgc.moneycalculator.model;

public record Currency(String code) {
    @Override
    public String toString() {
        return code;
    }
}
