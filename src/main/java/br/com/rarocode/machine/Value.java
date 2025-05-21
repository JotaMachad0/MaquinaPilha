package br.com.rarocode.machine;

public class Value<T> {
    private final T value; // Valor genérico armazenado

    // Construtor que inicializa o valor
    public Value(T value){
        this.value = value;
    }

    // Metodo para obter o valor armazenado
    public T getValue() {
        return value;
    }
}
