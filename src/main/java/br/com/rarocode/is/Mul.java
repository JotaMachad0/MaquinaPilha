package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Mul extends BinaryOperation {

    @Override
    public void execute(Value<?> a, Value<?> b, MachineInt machine) {
        Integer left = ((Number) a.getValue()).intValue();
        Integer right = ((Number) b.getValue()).intValue();
        machine.push(new Value<>(right * left));
    }
}

