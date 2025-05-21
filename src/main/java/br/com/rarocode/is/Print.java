package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Print extends UnaryOperation {

    @Override
    public void execute(Value<?> value, MachineInt machine) {
        System.out.println(value.getValue().toString());
        push(machine, value);
        notifyOutput(machine, value.getValue().toString());
    }
}
