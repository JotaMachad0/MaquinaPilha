package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

// Classe Load representa a instrução que carrega uma variável da memória da máquina para a pilha
public class Load extends Statement {
    private final String variable; // Nome da variável a ser carregada

    // Construtor recebe o nome da variável que será carregada
    public Load(String variable) {
        this.variable = variable;
    }

    // Metodo que executa a instrução
    @Override
    public int execute(int count, MachineInt machine) {
        // Recupera o valor da variável na máquina (memória)
        Value<?> value = machine.load(variable);
        // Empilha o valor recuperado na pilha da máquina
        machine.push(value);
        // Retorna o próximo índice da instrução a ser executada (próxima instrução)
        return count + 1;
    }

    // Getter para obter o nome da variável
    public String getVariable() {
        return variable;
    }
}
