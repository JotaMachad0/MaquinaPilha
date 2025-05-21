package br.com.rarocode.is;

// Importa a interface que representa o contexto da máquina de execução
import br.com.rarocode.machine.MachineInt;
// Importa a classe que encapsula valores genéricos utilizados na pilha da máquina
import br.com.rarocode.machine.Value;

// Define a instrução AddDouble, que estende BinaryOperation (operação binária)
public class AddDouble extends BinaryOperation {

    @Override
    public void execute(Value<?> left, Value<?> right, MachineInt machine) {
        // Converte os valores dos operandos para Double
        Double a = (Double) left.getValue();  // Primeiro operando
        Double b = (Double) right.getValue(); // Segundo operando

        // Realiza a soma dos valores e empilha o resultado
        machine.push(new Value<>(b + a));
    }
}
