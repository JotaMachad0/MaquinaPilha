package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

// Instrução para comparar se um valor é maior que o outro
public class GreaterThan extends BinaryOperation {

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Value<?> left, Value<?> right, MachineInt machine) {
        // Converte os valores da pilha para Number e depois para double para comparar
        double a = ((Number) left.getValue()).doubleValue();
        double b = ((Number) right.getValue()).doubleValue();

        // Compara se 'a' é maior que 'b', resultado será 1 se verdadeiro, 0 caso contrário
        int result = (a > b) ? 1 : 0;

        // Empilha o resultado da comparação na máquina
        machine.push(new Value<>(result));
    }
}
