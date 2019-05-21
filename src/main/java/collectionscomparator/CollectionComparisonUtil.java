package collectionscomparator;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionComparisonUtil {

    public static <A, B> CollectionComparisonResult<A, B> compareCollections(Collection<A> collectionA, Collection<B> collectionB, CollectionComparisonComparator<A, B> equaler) {
        CollectionComparisonResult<A,B> result = new CollectionComparisonResult<>();
        for (A a : collectionA) {
            Optional<B> foundFromB = collectionB.stream()
                    .filter(b -> equaler.isEqual(a, b))
                    .findFirst();
            if(foundFromB.isPresent()){
                result.getCommon().put(a,foundFromB.get());
            }else{
                result.getOnlyInFirst().add(a);
            }
        }

        for (B b : collectionB) {
            Optional<A> foundFromA = collectionA.stream()
                    .filter(a -> equaler.isEqual(a, b))
                    .findFirst();
            if(!foundFromA.isPresent()){
                result.getOnlyInSecond().add(b);
            }
        }
        return result;
    }


    public static <ELEMENT, KEY> CollectionComparisonResult<ELEMENT, ELEMENT> compareCollections(Collection<ELEMENT> collectionA, Collection<ELEMENT> collectionB, Function<ELEMENT, KEY> keyProvider) {
        CollectionComparisonResult<ELEMENT, ELEMENT> result = new CollectionComparisonResult<>();
        Map<KEY, ELEMENT> mapForBCollection = collectionB.stream().collect(Collectors.toMap(keyProvider, b -> b));
        Map<KEY, ELEMENT> mapForACollection = collectionA.stream().collect(Collectors.toMap(keyProvider, a -> a));

        for (ELEMENT a : collectionA) {
            if (mapForBCollection.containsKey(keyProvider.apply(a))) {
                result.getCommon().put(a, mapForBCollection.get(keyProvider.apply(a)));//miesiac
            } else {
                result.getOnlyInFirst().add(a);
            }
        }

        for (ELEMENT b : collectionB) {
            if (!mapForACollection.containsKey(keyProvider.apply(b))) {
                result.getOnlyInSecond().add(b);
            }
        }
        return result;
    }
}