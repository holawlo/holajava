package oneClassTasks;

import lombok.AllArgsConstructor;

//Napisz klasę generyczną Pair przechowującą dwa elementy różnych typów (T,E)
@AllArgsConstructor
public class Pair <T, E>{

    private T elementT;
    private E elementE;

    //Napisz metodę equals w klasie Pair - porównującą te wartości.

    public boolean equals(T elementT, E elementE){
        return true;
    }

}
