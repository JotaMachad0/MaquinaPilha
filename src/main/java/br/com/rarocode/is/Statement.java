package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;

public abstract class Statement extends Instruction {
    public abstract int execute(int currentCounter, MachineInt machine);

    @Override
    public int execute(MachineInt machine) {
        return execute(machine.getCounter(), machine);
    }
}
