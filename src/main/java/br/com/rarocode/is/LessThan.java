package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class LessThan extends Instruction {

    @Override
    @SuppressWarnings("unchecked")
    public int execute(MachineInt machine) {
        Value<?> b = machine.pop();
        Value<?> a = machine.pop();

        // Converter para double para comparação numérica
        double aVal = ((Number) a.getValue()).doubleValue();
        double bVal = ((Number) b.getValue()).doubleValue();

        int result = (aVal < bVal) ? 1 : 0;

        // Empilhar o resultado
        machine.push(new Value<>(result));

        // Avançar para próxima instrução
        return machine.getCounter() + 1;
    }
}
