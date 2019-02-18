package fundamentals.exceptionsExamples;

public class ExceptionsExample {
    public static void main(String[] args) {

        try {
            System.out.println(divide(3, 0)); //kodzik gdzie spodziewam sie bledu
        } catch (ArithmeticException e) { //e wrzucamy jako nowy obiekt klasy exception
            System.out.println("wyst wyjatek: " + e.getMessage()); //przechwytujemy jego message i printujemy
        } //nie przejmujemy se i lecimy dalej

        System.out.println(divide(3, 3));

        try{
            divideWithException(2,0);
        }catch(MyRuntimeException | ArithmeticException e){
            System.out.println("wyst wyjatek: " + e.getMessage());
        }finally{
            System.out.println("blok finally");
        }

        try {
            divideWithCheckedException(2,0);
        } catch (MyCheckedException e) {
            e.printStackTrace();
        }

        System.out.println("test programu"); //nie wyswietli sie jesli z drugiego try skasuje obsluge myruntimeexc

    }

    public static int divideWithException(int a, int b) {
        if (b == 0) {
            throw new MyRuntimeException("nie dziel cholero przez zero");
        }
        return a / b;
    }

    public static int divide(int a, int b) {
        return a / b;
    }

    public static int divideWithCheckedException(int a, int b) throws MyCheckedException{
        if (b == 0) {
            throw new MyCheckedException("nie dziel przez zero");
        }
        return a / b;
    }

}
