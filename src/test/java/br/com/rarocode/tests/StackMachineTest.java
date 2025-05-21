package br.com.rarocode.tests;

import br.com.rarocode.is.*;
import br.com.rarocode.machine.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StackMachineTest {

    @Test
    public void shouldAddTwoNumbers(){
        // Testa a soma de dois números inteiros
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(33)),
                new Push(new Value<>(51)),
                new Add(),
                new Print(),
                new End()
        );
        List<String> expected = Arrays.asList("84");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldSubtractTwoNumbers(){
        // Testa a subtração de dois números inteiros
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(10)),
                new Push(new Value<>(7)),
                new Sub(),
                new Print(),
                new End()
        );
        List<String> expected = Arrays.asList("3");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldMultiplyTwoNumbers(){
        // Testa a multiplicação de dois números inteiros
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(10)),
                new Push(new Value<>(7)),
                new Mul(),
                new Print(),
                new End()
        );
        List<String> expected = Arrays.asList("70");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldDivideTwoNumbers(){
        // Testa a divisão inteira de dois números
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(20)),
                new Push(new Value<>(7)),
                new Div(),
                new Print(),
                new End()
        );
        List<String> expected = Arrays.asList("2");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldCalcExpression(){
        // Testa uma expressão aritmética composta ( ((10+5)-3)*2 / 6 )
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(10)),
                new Push(new Value<>(5)),
                new Add(),
                new Push(new Value<>(3)),
                new Sub(),
                new Push(new Value<>(2)),
                new Mul(),
                new Push(new Value<>(6)),
                new Div(),
                new Print(),
                new End()
        );
        List<String> expected = Arrays.asList("4");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldPrintFromZeroToFive() {
        // Programa que imprime de 0 até 5 usando Label, Inc, Equal e GotoF
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(6)),       // limite superior +1
                new Store("n"),
                new Push(new Value<>(0)),
                new Store("i"),
                new Label("Inicio"),
                new Load("i"),
                new Print(),
                new Load("i"),
                new Inc(),
                new Store("i"),
                new Load("i"),
                new Load("n"),
                new Equal(),
                new GotoF("Inicio"),          // enquanto i != n volta para Inicio
                new Label("Fim"),
                new End()
        );

        List<String> expected = Arrays.asList("0", "1", "2", "3", "4", "5");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }



    @Test
    public void shouldStoreAndLoad(){
        // Testa as instruções de Store e Load para variáveis e loop para imprimir 0 a 5
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(5)),
                new Store("limite"),
                new Push(new Value<>(0)),
                new Store("i"),
                new Label("Inicio"),
                new Load("i"),
                new Print(),
                new Load("limite"),
                new Equal(),
                new Load("i"),
                new Inc(),
                new Store("i"),
                new GotoF("Inicio"),
                new End()
        );
        List<String> expected = Arrays.asList("0","1","2","3","4","5");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    // Testes novos da atividade

    @Test
    public void shouldUseGreaterThan(){
        // Testa a instrução GreaterThan: 10 > 5 => true (1)
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(10)),
                new Push(new Value<>(5)),
                new GreaterThan(),
                new Print(),
                new End()
        );
        List<String> expected = List.of("1");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldUseLessThan(){
        // Testa a instrução LessThan: 3 < 7 => true (1)
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(3)),
                new Push(new Value<>(7)),
                new LessThan(),
                new Print(),
                new End()
        );
        List<String> expected = List.of("1");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldUseNotEquals(){
        // Testa a instrução NotEquals: 3 != 4 => true (1)
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(3)),
                new Push(new Value<>(4)),
                new NotEquals(),
                new Print(),
                new End()
        );
        List<String> expected = List.of("1");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldReadAndPrintInput(){
        // Testa a instrução Read que injeta o valor 42 e imprime
        List<Instruction> program = Arrays.asList(
                new Read(() -> new Value<>(42)),
                new Print(),
                new End()
        );
        List<String> expected = List.of("42");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldUseGotoT() {
        // Testa a instrução GotoT: se topo da pilha é true, salta para label "Ok"
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(true)),
                new GotoT("Ok"),
                new Push(new Value<>(0)),
                new Print(),
                new Label("Ok"),
                new Push(new Value<>(99)),
                new Print(),
                new End()
        );
        List<String> expected = List.of("99");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldAddTwoDoubles(){
        // Testa soma de números Double: 3.5 + 2.5 = 6.0
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(3.5)),
                new Push(new Value<>(2.5)),
                new Add(),
                new Print(),
                new End()
        );
        List<String> expected = List.of("6.0");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldCheckIfPrime(){
        // Testa se o número 7 é primo
        int candidate = 7;
        List<Instruction> program = new ArrayList<>(List.of(
                new Push(new Value<>(candidate)),
                new Store("n"),
                new Push(new Value<>(2)),
                new Store("i"),
                new Label("Loop"),
                new Load("i"),
                new Load("n"),
                new GreaterThan(),
                new GotoT("IsPrime"),
                new Load("n"),
                new Load("i"),
                new Div(),
                new Push(new Value<>(0)),
                new NotEquals(),
                new GotoT("Next"),
                new Push(new Value<>(0)),
                new Print(),
                new End(),
                new Label("Next"),
                new Load("i"),
                new Inc(),
                new Store("i"),
                new GotoF("Loop"),
                new Label("IsPrime"),
                new Push(new Value<>(1)),
                new Print(),
                new End()
        ));
        List<String> expected = List.of("1");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }

    @Test
    public void shouldRunHighLevelCodeAndPrintFromZeroToFive() {
        // Simula código em linguagem de alto nível com variáveis, loop e condição de saída
        List<Instruction> program = Arrays.asList(
                new Push(new Value<>(0)),
                new Store("i"),
                new Push(new Value<>(5)),
                new Store("limite"),
                new Label("Inicio"),
                new Load("i"),
                new Print(),
                new Load("i"),
                new Load("limite"),
                new Equal(),
                new GotoT("Fim"), // se i == limite, vai para o fim
                new Load("i"),
                new Inc(),
                new Store("i"),
                new Push(new Value<>(false)), // empilha falso para o GotoF
                new GotoF("Inicio"),          // sempre salta para o início, pois topo é falso
                new Label("Fim"),
                new End()
        );

        List<String> expected = List.of("0", "1", "2", "3", "4", "5");
        List<String> output = new ArrayList<>();
        StackMachine sm = new StackMachine(program, output::add);
        sm.run();
        assertThat(output, is(expected));
    }
}
