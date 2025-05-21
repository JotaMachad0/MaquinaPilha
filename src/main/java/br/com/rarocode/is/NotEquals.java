package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class NotEquals extends Instruction {

    @Override
    public int execute(MachineInt machine) {
        // Remove o valor do topo da pilha (operando direito)
        Value<?> b = machine.pop();
        // Remove o próximo valor do topo da pilha (operando esquerdo)
        Value<?> a = machine.pop();

        // Compara se os valores são diferentes
        boolean result = !a.getValue().equals(b.getValue());
        // Empilha 1 se forem diferentes, 0 caso contrário
        machine.push(new Value<>(result ? 1 : 0));

        // Retorna o índice da próxima instrução a ser executada
        return machine.getCounter() + 1;
    }
}
