package fundamentals.mutableImmutableFinal;

public class MainMutableFinal {
    public static void main(String[] args) {
        equalsExample();
        referenceExample();
        staticFinal();
    }

    private static void staticFinal() {
        System.out.println("STATIC FINAL METHOD:");

        TestPerson person1 = new TestPerson();
        TestPerson person2 = new TestPerson();
        System.out.println(person1.getIdentity());
        System.out.println("ludzi na świecie jest: " + (TestPerson.counter - 1));
        //zmiana imienia jako pola obiektu
        person1.setFirstName("Ola");
        ExperimentalObject eo = new ExperimentalObject(1,2,person1);
        eo.setNotFinalImmutable(6);
        TestPerson personFromEo = eo.getFinalMutable();
        System.out.println(eo.getFinalMutable().getFirstName());
        eo.getFinalMutable().setFirstName("AAA");
        System.out.println(eo.getFinalMutable().getFirstName());
        personFromEo.setFirstName("Anna");
        System.out.println(eo.getFinalMutable().getFirstName());
    }


    private static void referenceExample() {
        Long firstNumber = 127L;
        Long secondNumber = 127L;
        System.out.println(firstNumber == secondNumber);
        System.out.println(firstNumber.equals(secondNumber));

    }

    private static void equalsExample() {
        TestPerson person1 = new TestPerson();
        TestPerson person2 = new TestPerson();
        person1.setFirstName("Anna");
        person2.setFirstName("Anna");
        person1.setIdentity(123);
        person2.setIdentity(123);
        person1.setSurname("Nowak");
        person2.setSurname("N");
        System.out.println(person1 == person2);
        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode() == person2.hashCode());

        TestPerson person3 = new TestPerson("Anna", "Nowak", 123);
        System.out.println(person1.equals(person3));
        person3.setIdentity(111);
        System.out.println(person1.equals(person3));

        String a = "A"; //immutable, wynika z klasy String
        String b = a + "!"; //jedyny sposob zmiany

    }
}
