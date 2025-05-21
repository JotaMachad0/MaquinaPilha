package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Equal extends BinaryOperation {

    @Override
    public void execute(Value<?> a, Value<?> b, MachineInt machine) {
        boolean isEqual = a.getValue().equals(b.getValue());
        machine.push(new Value<>(isEqual)); // push 1 if equal, else 0
    }
}
