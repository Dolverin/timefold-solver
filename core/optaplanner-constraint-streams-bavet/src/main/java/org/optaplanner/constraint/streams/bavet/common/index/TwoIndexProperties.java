package org.optaplanner.constraint.streams.bavet.common.index;

import java.util.Objects;

final class TwoIndexProperties implements IndexProperties {

    private final Object propertyA;
    private final Object propertyB;

    TwoIndexProperties(Object propertyA, Object propertyB) {
        this.propertyA = propertyA;
        this.propertyB = propertyB;
    }

    @Override
    public <Type_> Type_ toKey(int index) {
        switch (index) {
            case 0:
                return (Type_) propertyA;
            case 1:
                return (Type_) propertyB;
            default:
                throw new IllegalArgumentException("Impossible state: index (" + index + ") != 0");
        }
    }

    @Override
    public <Type_> Type_ toKey(int from, int to) {
        switch (to - from) {
            case 1:
                return toKey(from);
            case 2:
                if (from != 0 || to != 2) {
                    throw new IllegalArgumentException("Impossible state: key from (" + from + ") to (" + to + ").");
                }
                return (Type_) this;
            default:
                throw new IllegalArgumentException("Impossible state: key from (" + from + ") to (" + to + ").");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TwoIndexProperties)) {
            return false;
        }
        TwoIndexProperties other = (TwoIndexProperties) o;
        return Objects.equals(propertyA, other.propertyA)
                && Objects.equals(propertyB, other.propertyB);
    }

    @Override
    public int hashCode() { // Not using Objects.hash(Object...) as that would create an array on the hot path.
        int result = Objects.hashCode(propertyA);
        result = 31 * result + Objects.hashCode(propertyB);
        return result;
    }

    @Override
    public String toString() {
        return "[" + propertyA + ", " + propertyB + "]";
    }

}
