package br.com.rarocode.is;

import br.com.rarocode.machine.MachineInt;
import br.com.rarocode.machine.Value;

public class Div extends BinaryOperation {

    @Override
    public void execute(Value<?> left, Value<?> right, MachineInt machine) {
        // Como você espera trabalhar com Integer, deve fazer cast dos valores
        Integer a = (Integer) left.getValue();
        Integer b = (Integer) right.getValue();

        Integer resultado = a / b;

        machine.push(new Value<>(resultado));
    }
}
