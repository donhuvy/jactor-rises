package nu.hjemme.client.datatype;

import org.apache.commons.lang.Validate;

import java.util.Objects;

/** @author Tor Egil Jacobsen */
public class Parameter {
    private static final String EQUAL_SIGN = "=";

    private final String name;
    private final String value;

    public Parameter(String name, String value) {
        Validate.notEmpty(name, "The parameter name cannot be empty");
        Validate.notEmpty(value, "The parameter value cannot be empty");

        this.name = name;
        this.value = value;
    }

    public Parameter(String parameterAndValue) {
        this(parameterAndValue.split(EQUAL_SIGN)[0], parameterAndValue.split(EQUAL_SIGN)[1]);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Parameter parameter = (Parameter) o;

        return Objects.equals(name, parameter.name) && Objects.equals(value, parameter.value);
    }

    @Override
    public String toString() {
        return name + EQUAL_SIGN + value;
    }

    public String getKey() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
