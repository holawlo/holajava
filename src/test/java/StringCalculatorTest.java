import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stringCalculator.NegativeNumberFoundException;
import stringCalculator.StringCalculator;

public class StringCalculatorTest {

    @Test
    void shouldReturnZeroWhenTextIsEmpty() {
        Integer result = StringCalculator.adding("");
        Assertions.assertTrue(result.equals(0));
    }

    @Test
    void shouldReturnNumberWhenTextIsOneNumber() {
        Integer result = StringCalculator.adding("3");
        Assertions.assertTrue(result.equals(3));
    }

    @Test
    void shouldReturnSumWhenTextContainsNumbersSeparatedWithComma() {
        Integer result = StringCalculator.adding("3,2 ");
        Assertions.assertTrue(result.equals(5));
    }

    @Test
    void shouldReturnSumWhenTextContainsNumbersSeparatedWithCommaOrNewLineSymbol() {
        Integer result = StringCalculator.adding("3,2 \n4");
        Assertions.assertTrue(result.equals(9));
    }

    @Test
    void shouldReturnSumWhenTextContainsNumbersSeparatedWithCustomDelimiter() {
        Integer result = StringCalculator.adding("//s\n3s2s 4");
        Assertions.assertTrue(result.equals(9));
    }

    @Test
    void shouldReturnSumWhenTextContainsNumbersSeparatedWithCustomLongDelimiter() {
        Integer result = StringCalculator.adding("//sss\n3sss2sss 4");
        Assertions.assertTrue(result.equals(9));
    }

    @Test
    void shouldReturnSumIgnoringNumbersBiggerThan1000() {
        Integer result = StringCalculator.adding("//s\n3s2000s 4");
        Assertions.assertTrue(result.equals(7));
    }

    @Test
    void shouldThrowExceptionWhenTextContainsNegativeNumbers() {

        NegativeNumberFoundException negativeNumberFoundException = Assertions.assertThrows(
                NegativeNumberFoundException.class,
                () -> StringCalculator.adding("//s\n3s-2s -4"));

        Assertions.assertEquals("Tak nie można! [-2, -4]",negativeNumberFoundException.getMessage());
    }

    @Test
    void shouldReturnSumWithManyCustomDelimiters() {
        Integer result = StringCalculator.adding("//[xxx][yyy][kk]\n3xxx2000yyy 4kk83");
        Assertions.assertTrue(result.equals(90));
    }

}
