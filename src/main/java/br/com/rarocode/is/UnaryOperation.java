package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

// Classe abstrata para representar operações unárias
public abstract class UnaryOperation extends Instruction {

    // Método abstrato que define como a operação unária será executada
    public abstract void execute(Value<?> value, MachineInt machine);

    @Override
    public int execute(MachineInt machine) {
        // Remove o valor do topo da pilha
        Value<?> val = machine.pop();

        // Executa a operação unária com o valor retirado e a máquina
        execute(val, machine);

        // Avança para a próxima instrução
        return machine.getCounter() + 1;
    }
}
