package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public abstract class BinaryOperation extends Instruction {

    // Recebe os dois operandos (left e right) e a máquina para executar a operação
    public abstract void execute(Value<?> left, Value<?> right, MachineInt machine);


    @Override
    public int execute(MachineInt machine) {
        // Retira o operando direito do topo da pilha
        Value<?> right = machine.pop();
        // Retira o operando esquerdo do topo da pilha
        Value<?> left = machine.pop();

        // Executa a operação binária com os operandos retirados
        execute(left, right, machine);

        // Retorna o próximo índice da instrução a ser executada
        return machine.getCounter() + 1;
    }
}
