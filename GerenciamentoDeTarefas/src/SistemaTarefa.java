import java.util.*;
import java.io.*;

public class SistemaTarefa {
    public static final String ARQUIVO = "tarefas.txt";
    private Scanner input = new Scanner(System.in);

    public void adicionaTarefas(){
        String tarefa;
        boolean c = false;
        System.out.println("Digite a tarefa que deseja adicionar: ");
        do{
            tarefa = input.nextLine();
            if(!tarefa.isBlank()){
                c = true;
            }
        }while(c == false);


        Tarefas tarefas = new Tarefas(tarefa);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO,true))){
            bw.write(tarefas.getTarefa());
            bw.newLine();
            System.out.println("Usuario cadastrado com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao adicionar a tarefa: "+e.getMessage());
        }
    }

    public void listaTarefas(){
        int index = 1;
        System.out.println("\n======= Tarefas =======");
        try(BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))){
            String linha;
            List<String> dados = new ArrayList<>();
            while ((linha = br.readLine()) != null){
                dados.add(linha);
            }
            for(String dado : dados){
                System.out.println(index + ". "+ dado);
                index++;
            }
        }catch (IOException e){
            System.out.println("Erro ao listar a(s) tarefa(s): "+e.getMessage());
        }
    }

    public void removeTarefas(){
        String tarefaRemove;
        boolean c = false;
        System.out.println("Digite a tarefa que deseja remover: ");
        do{
            tarefaRemove = input.nextLine().trim();
            if(!tarefaRemove.isBlank()){
                c = true;
            }
        }while (c == false);


        List<String> tarefas = new ArrayList<>();
        List<String> dados = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))){
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.equals(tarefaRemove)) { // Compara a linha inteira com a tarefa a ser removida
                    tarefas.add(linha); // Adiciona as tarefas que não serão removidas
                }
            }
        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo: "+ e.getMessage());
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO,false))){
            for (String tarefa : tarefas){
                bw.write(tarefa);
                bw.newLine();
            }
            System.out.println("Tarefa removida com sucesso!");
        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo! "+e.getMessage());
        }

    }
    public void sistema(){
        int opcao;
        do{
            System.out.println("\n======= MENU ========");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Remover Tarefa");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = input.nextInt();

            switch (opcao){
                case 1:
                    adicionaTarefas();
                    break;
                case 2:
                    listaTarefas();
                    break;
                case 3:
                    removeTarefas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
            }
        }while (opcao != 4);
    }
}
