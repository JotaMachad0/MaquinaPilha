package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

// A instrução Store armazena o valor no topo da pilha em uma variável
public class Store extends Statement {
    private final String variable; // Nome da variável onde o valor será armazenado

    // Construtor recebe o nome da variável
    public Store(String variable) {
        this.variable = variable;
    }

    @Override
    public int execute(int count, MachineInt machine) {
        // Remove o valor do topo da pilha e armazena na variável indicada
        machine.store(variable, machine.pop());

        // Avança para a próxima instrução
        return count + 1;
    }

    // Retorna o nome da variável
    public String getVariable() {
        return variable;
    }
}
