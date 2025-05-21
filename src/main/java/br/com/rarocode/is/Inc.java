package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Inc extends UnaryOperation {

    @Override
    public void execute(Value<?> a, MachineInt machine) {
        Integer val = ((Number) a.getValue()).intValue();
        machine.push(new Value<>(val + 1));
    }
}
