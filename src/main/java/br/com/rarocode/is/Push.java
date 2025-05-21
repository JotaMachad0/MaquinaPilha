package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Push<T> extends Instruction {
    // Valor que será empilhado na pilha da máquina
    private final Value<T> parameter;

    // Construtor que recebe o valor a ser empilhado
    public Push(Value<T> value) {
        this.parameter = value;
    }

    @Override
    public int execute(MachineInt machine) {
        // Empilha o valor na pilha da máquina
        push(machine, this.parameter);
        // Avança o contador para a próxima instrução
        return machine.getCounter() + 1;
    }

    @Override
    public String toString() {
        // Retorna uma representação textual da instrução e do valor empilhado
        return getClass().getSimpleName() + " " + this.parameter.getValue();
    }
}
