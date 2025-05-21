package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

// Instrução de salto condicional que salta para um label se o valor no topo da pilha for verdadeiro
public class GotoT extends Instruction {
    private final String label; // Label para onde o salto será feito caso a condição seja verdadeira

    // Construtor recebe o nome do label
    public GotoT(String label) {
        this.label = label;
    }

    @Override
    public int execute(MachineInt machine) {
        // Remove o valor do topo da pilha
        Value<?> condition = machine.pop();
        Object rawValue = condition.getValue();

        // Verifica se o valor é do tipo Boolean, senão lança exceção
        if (!(rawValue instanceof Boolean)) {
            throw new RuntimeException("GotoT expects a Boolean on the stack, but found: " + rawValue.getClass());
        }

        // Converte o valor para boolean
        boolean boolValue = (Boolean) rawValue;

        // Se verdadeiro, salta para o índice associado ao label
        if (boolValue) {
            return machine.getCounterByLabel(label);
        } else {
            // Se falso, continua para a próxima instrução sequencial
            return machine.getCounter() + 1;
        }
    }
}
