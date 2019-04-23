package collectionscomparator;

@FunctionalInterface
public interface CollectionComparisonComparator<A, B> {
    boolean isEqual(A elementA, B elementB);
}
