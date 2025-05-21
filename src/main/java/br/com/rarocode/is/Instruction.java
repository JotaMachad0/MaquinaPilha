package br.com.rarocode.is;

// Importa a interface da máquina de execução
import br.com.rarocode.machine.MachineInt;
// Importa a classe que representa um valor genérico na pilha
import br.com.rarocode.machine.Value;

// Classe abstrata base para todas as instruções da máquina
public abstract class Instruction {


    // Executa essa instrução usando a máquina passada via interface MachineInt.
    // Retorna o próximo valor do contador (program counter).

    public int execute(MachineInt machine) {
        // Se a instrução é uma operação binária (ex: soma, subtração)
        if (this instanceof BinaryOperation binaryOp) {
            // Executa a operação binária com dois valores da pilha
            binaryOp.execute(machine.pop(), machine.pop(), machine);
            return machine.getCounter() + 1;

            // Se a instrução é uma operação unária (ex: incremento, negação)
        } else if (this instanceof UnaryOperation unaryOp) {
            // Executa a operação unária com um valor da pilha
            unaryOp.execute(machine.pop(), machine);
            return machine.getCounter() + 1;

            // Se a instrução é um statement (ex: Goto, Label, controle de fluxo)
        } else if (this instanceof Statement stmt) {
            // Executa a instrução de controle de fluxo, que pode alterar o contador
            return stmt.execute(machine.getCounter(), machine);

            // Caso a instrução não se enquadre em nenhum dos tipos acima
        } else {
            // Apenas avança para a próxima instrução
            return machine.getCounter() + 1;
        }
    }

    // Método auxiliar para empilhar um valor na máquina
    protected void push(MachineInt machine, Value<?> value){
        machine.push(value);
    }

    // Método auxiliar para desempilhar um valor da máquina
    protected Value<?> pop(MachineInt machine){
        return machine.pop();
    }

    // Método auxiliar para obter o contador associado ao label
    protected int getCounterByLabel(MachineInt machine, String label){
        return machine.getCounterByLabel(label);
    }

    // Método auxiliar para enviar mensagens de saída
    protected void notifyOutput(MachineInt machine, String message){
        machine.notifyOutput(message);
    }
}
