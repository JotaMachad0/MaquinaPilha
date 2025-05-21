package br.com.rarocode.is;

// Importa a interface que representa o contexto da máquina de execução
import br.com.rarocode.machine.MachineInt;
// Importa a classe que encapsula valores genéricos utilizados na pilha da máquina
import br.com.rarocode.machine.Value;

// Define a instrução Add, que estende a classe abstrata Instruction
public class Add extends Instruction {

    @Override
    public int execute(MachineInt machine) {
        // Remove (desempilha) os dois operandos do topo da pilha da máquina
        Value<?> b = machine.pop();  // Segundo operando
        Value<?> a = machine.pop();  // Primeiro operando

        // Extrai os valores brutos dos objetos Value
        Object left = a.getValue();
        Object right = b.getValue();

        // Verifica se pelo menos um dos operandos é Double
        if (left instanceof Double || right instanceof Double) {
            // Realiza a soma com precisão de ponto flutuante
            double result = ((Number) left).doubleValue() + ((Number) right).doubleValue();
            // Empilha o resultado da operação na pilha da máquina
            machine.push(new Value<>(result));

            // Caso ambos sejam inteiros, realiza a soma inteira
        } else if (left instanceof Integer && right instanceof Integer) {
            int result = (Integer) left + (Integer) right;
            machine.push(new Value<>(result));

            // Lança exceção se os tipos não forem suportados
        } else {
            throw new RuntimeException("Unsupported types for Add: " +
                    left.getClass() + ", " + right.getClass());
        }

        // Retorna o próximo valor do contador de instruções (avança para a próxima)
        return machine.getCounter() + 1;
    }
}
