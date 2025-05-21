package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class GotoF extends Statement {

    private final String label; // Label para onde saltar se a condição for falsa

    public GotoF(String label) {
        this.label = label;
    }

    @Override
    public int execute(int count, MachineInt machine) {
        // Remove o valor do topo da pilha para avaliar a condição
        Value<?> value = machine.pop();

        boolean condition = false; // Inicializa condição como falsa
        Object val = value.getValue();

        // Verifica o tipo do valor e converte para booleano
        if (val instanceof Boolean) {
            condition = (Boolean) val; // Usa valor booleano diretamente
        } else if (val instanceof Number) {
            // Considera zero como falso, diferente de zero como verdadeiro
            condition = ((Number) val).intValue() != 0;
        }

        // Se a condição for falsa, salta para o label especificado
        if (!condition) {
            return machine.getCounterByLabel(label);
        }

        // Caso contrário, segue para a próxima instrução
        return count + 1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + this.label; // Representação textual da instrução
    }
}
