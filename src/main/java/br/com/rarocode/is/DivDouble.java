package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;
import br.com.rarocode.is.BinaryOperation;

// Classe que representa a operação de divisão para valores do tipo Double
public class DivDouble extends BinaryOperation {

    // Implementa o metodo execute da operação binária
    @Override
    public void execute(Value<?> left, Value<?> right, MachineInt machine) {
        // Converte os valores genéricos para Double
        Double a = (Double) left.getValue();
        Double b = (Double) right.getValue();

        // Realiza a divisão (note que divide right pelo left: b / a)
        Double resultado = b / a;

        // Empilha o resultado da divisão de volta na pilha da máquina
        machine.push(new Value<>(resultado));
    }
}
