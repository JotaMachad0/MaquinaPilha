package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Sub extends BinaryOperation {

    @Override
    public void execute(Value<?> left, Value<?> right, MachineInt machine) {
        int a = ((Number) left.getValue()).intValue();
        int b = ((Number) right.getValue()).intValue();
        int result = a - b;
        machine.push(new Value<>(result));
    }
}
