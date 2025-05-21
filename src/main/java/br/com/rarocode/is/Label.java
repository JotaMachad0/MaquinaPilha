package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;

public class Label extends Instruction {
    private final String label;

    public Label(String label) {
        this.label = label;
    }

    @Override
    public int execute(MachineInt machine) {
        // Label é uma marcação, não altera o fluxo
        return machine.getCounter() + 1;
    }

    public String getLabel() {
        return label;
    }
}
