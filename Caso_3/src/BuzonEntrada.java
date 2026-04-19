import java.util.ArrayList;
import java.util.List;

public class BuzonEntrada {
    private final List<Evento> eventos = new ArrayList<>();

    public synchronized void depositarEvento(Evento evento){
        eventos.add(evento);
        notify();
    }

    public synchronized Evento retirarEvento(){
        while (eventos.isEmpty()){
            try {
                wait();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        Evento evento1 = eventos.get(0);
        eventos.remove(0);
        return evento1;
    }
}

