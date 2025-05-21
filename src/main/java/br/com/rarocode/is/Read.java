package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

import java.util.function.Supplier;

public class Read extends Instruction {
    private final Supplier<Value<?>> inputSupplier; // Fornecedor que fornece o valor de entrada

    // Construtor recebe o Supplier que simula a entrada de dados
    public Read(Supplier<Value<?>> inputSupplier) {
        this.inputSupplier = inputSupplier;
    }

    @Override
    public int execute(MachineInt machine) {
        // Pega o valor do supplier (simula uma leitura de entrada)
        Value<?> val = inputSupplier.get();

        // Empilha o valor lido na pilha da máquina
        machine.push(val);

        // Avança o contador da máquina para a próxima instrução
        return machine.getCounter() + 1;
    }
}
