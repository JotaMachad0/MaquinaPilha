package br.com.rarocode.machine;

import br.com.rarocode.is.Instruction;
import br.com.rarocode.is.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

// Implementa a máquina de pilha, responsável por executar programas compostos por instruções
public class StackMachine implements MachineInt {
    // Lista com as instruções do programa
    private final List<Instruction> program;

    // Função de callback para saída
    private final Consumer<String> outputCallback;

    // Mapeia rótulos (labels) para suas posições no programa
    private final Map<String, Integer> labels = new HashMap<>();

    // Memória associativa usada por instruções como Store e Load
    private final Map<String, Value<?>> memory = new HashMap<>();

    // Pilha usada para armazenar operandos e resultados
    private final java.util.Stack<Value<?>> stack = new java.util.Stack<>();

    // Contador de instruções (program counter)
    private int counter = 0;

    // indica se a máquina está em execução
    private boolean running = true;

    // Construtor: inicializa a máquina e indexa os labels
    public StackMachine(List<Instruction> program, Consumer<String> outputCallback) {
        this.program = program;
        this.outputCallback = outputCallback;

        // Mapeia os rótulos para suas posições na lista de instruções
        for (int i = 0; i < program.size(); i++) {
            Instruction instr = program.get(i);
            if (instr instanceof Label labelInstr) {
                labels.put(labelInstr.getLabel(), i);
            }
        }
    }

    // Executa o programa carregado na máquina
    public void run() {
        running = true;
        counter = 0;

        // Laço principal de execução das instruções
        while (running && counter < program.size()) {
            Instruction instr = program.get(counter);
            counter = instr.execute(this); // Executa e atualiza o contador
        }
    }

    // Métodos da interface MachineInt

    @Override
    public void push(Value<?> value) {
        stack.push(value); // Empilha um valor
    }

    @Override
    public Value<?> pop() {
        return stack.pop(); // Desempilha um valor
    }

    @Override
    public void store(String name, Value<?> value) {
        memory.put(name, value); // Armazena valor associado a uma variável
    }

    @Override
    public Value<?> load(String name) {
        return memory.get(name); // Recupera valor da variável
    }

    @Override
    public void jumpTo(String label) {
        Integer target = labels.get(label);
        if (target == null) {
            throw new RuntimeException("Label not found: " + label); // Erro se o rótulo não existir
        }
        counter = target; // Altera o contador para o índice do label
    }

    @Override
    public void end() {
        running = false; // Interrompe a execução da máquina
    }

    @Override
    public int getCounter() {
        return counter; // Retorna o valor atual do contador de instruções
    }

    @Override
    public void setCounter(int counter) {
        this.counter = counter; // Atualiza manualmente o contador
    }

    @Override
    public int getCounterByLabel(String label) {
        Integer pos = labels.get(label);
        if (pos == null) throw new RuntimeException("Label not found: " + label);
        return pos; // Retorna a posição de um label
    }

    @Override
    public void notifyOutput(String message) {
        outputCallback.accept(message); // Envia mensagem para o callback de saída
    }

    // Parte 3 - Interpretador de código em alto nível (linguagem "português-like")
    public static StackMachine CodeHighLevel(String code, Consumer<String> outputCallback) {
        List<Instruction> instructions = new java.util.ArrayList<>();
        String[] lines = code.strip().split(";"); // Divide código em linhas/instruções

        for (String raw : lines) {
            String line = raw.strip();

            if (line.startsWith("empilhe ")) {
                String val = line.substring(8).trim();
                // Decide se o valor é Double ou Integer
                if (val.contains(".")) {
                    instructions.add(new Push(new Value<>(Double.parseDouble(val))));
                } else {
                    instructions.add(new Push(new Value<>(Integer.parseInt(val))));
                }

            } else if (line.startsWith("guarde ")) {
                String name = line.substring(7).trim();
                instructions.add(new Store(name));

            } else if (line.startsWith("carregue ")) {
                String name = line.substring(9).trim();
                instructions.add(new Load(name));

            } else if (line.equals("incremente")) {
                instructions.add(new Inc());

            } else if (line.equals("imprima")) {
                instructions.add(new Print());

            } else if (line.equals("igual")) {
                instructions.add(new Equal());

            } else if (line.startsWith("va para ") && line.endsWith(" se falso")) {
                String label = line.substring(8, line.length() - 9).trim();
                instructions.add(new GotoF(label));

            } else if (line.startsWith("va para ") && line.endsWith(" se verdadeiro")) {
                String label = line.substring(8, line.length() - 14).trim();
                instructions.add(new GotoT(label));

            } else if (line.startsWith("label: ")) {
                String label = line.substring(7).trim();
                instructions.add(new Label(label));

            } else if (line.equals("fim")) {
                instructions.add(new End());

            } else if (!line.isBlank()) {
                // Erro se a instrução não for reconhecida
                throw new RuntimeException("Instrução desconhecida: " + line);
            }
        }

        // Retorna nova instância da máquina com o programa interpretado
        return new StackMachine(instructions, outputCallback);
    }
}
