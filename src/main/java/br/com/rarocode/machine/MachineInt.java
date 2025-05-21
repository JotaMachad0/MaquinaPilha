package br.com.rarocode.machine;

public interface MachineInt {
    // Adiciona um valor à pilha da máquina
    void push(Value<?> value);

    // Remove e retorna o valor do topo da pilha
    Value<?> pop();

    // Armazena um valor na memória associando-o a uma variável pelo nome
    void store(String name, Value<?> value);

    // Recupera o valor armazenado na memória pelo nome da variável
    Value<?> load(String name);

    // Salta para a instrução associada ao label especificado
    void jumpTo(String label);

    // Encerra a execução da máquina
    void end();

    // Obtém a posição atual do contador de instruções
    int getCounter();

    // Define a posição do contador de instruções
    void setCounter(int counter);

    // Obtém a posição do contador a partir do nome de um label
    int getCounterByLabel(String label);

    // Envia uma mensagem para saída
    void notifyOutput(String message);
}
