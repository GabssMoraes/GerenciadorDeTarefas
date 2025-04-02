public class Tarefas {
    private String tarefa;

    public Tarefas(String tarefa){
        this.tarefa = tarefa;
    }

    public String getTarefa(){
        return tarefa;
    }

    public void setTarefa(String tarefa){
        this.tarefa = tarefa;
    }

    public String toString(){
        return "- " + tarefa;
    }
}
