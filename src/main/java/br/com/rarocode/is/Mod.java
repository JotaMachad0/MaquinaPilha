package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Mod extends Instruction {

    @Override
    public int execute(MachineInt machine) {
        // Remove o divisor do topo da pilha
        Value<?> divisor = machine.pop();
        // Remove o dividendo logo abaixo do divisor na pilha
        Value<?> dividend = machine.pop();

        // Obtém os valores brutos para verificação de tipo
        Object dividendValue = dividend.getValue();
        Object divisorValue = divisor.getValue();

        // Se ambos os valores forem Inteiros, realiza o operador módulo com int
        if (dividendValue instanceof Integer && divisorValue instanceof Integer) {
            int a = (Integer) dividendValue;
            int b = (Integer) divisorValue;
            machine.push(new Value<>(a % b));  // Empilha o resultado do módulo
        }
        // Se ambos os valores forem Double, realiza o operador módulo com double
        else if (dividendValue instanceof Double && divisorValue instanceof Double) {
            double a = (Double) dividendValue;
            double b = (Double) divisorValue;
            machine.push(new Value<>(a % b));  // Empilha o resultado do módulo
        }
        // Caso os tipos não sejam suportados
        else {
            throw new RuntimeException("Mod operation requires Integer or Double types");
        }

        // Retorna o próximo índice da instrução a ser executada
        return machine.getCounter() + 1;
    }
}
