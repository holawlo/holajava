package collectionscomparator;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

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
        throw new NotImplementedException("Do dzieła!"); //todo
    }
}
