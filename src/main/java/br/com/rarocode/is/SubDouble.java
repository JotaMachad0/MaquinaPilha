package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class SubDouble extends BinaryOperation {

    @Override
    public void execute(Value<?> a, Value<?> b, MachineInt machine) {
        // Converte o valor a para Double
        Double left = ((Number) a.getValue()).doubleValue();

        // Converte o valor b para Double
        Double right = ((Number) b.getValue()).doubleValue();

        // Calcula b - a e empilha o resultado na máquina
        machine.push(new Value<>(right - left));
    }
}
