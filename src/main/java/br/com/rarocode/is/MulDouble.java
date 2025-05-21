package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class MulDouble extends BinaryOperation {

    @Override
    public void execute(Value<?> a, Value<?> b, MachineInt machine) {
        // Converte o valor do operando 'a' para Double
        Double left = ((Number) a.getValue()).doubleValue();
        // Converte o valor do operando 'b' para Double
        Double right = ((Number) b.getValue()).doubleValue();
        // Calcula a multiplicação dos dois valores e empilha o resultado na máquina
        machine.push(new Value<>(left * right));
    }
}
