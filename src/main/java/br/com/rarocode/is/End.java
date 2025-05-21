package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class End extends Statement {
    @Override
    public int execute(int count, MachineInt machine) {
        machine.end();  // para indicar que a máquina deve parar
        return -1;      // valor -1 para indicar fim da execução
    }
}
