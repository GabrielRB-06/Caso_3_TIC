import java.util.ArrayList;
import java.util.List;

public class BuzonAlertas {
    private final List<Evento> eventos = new ArrayList<>();

    public synchronized void depositarEvento(Evento evento){
        eventos.add(evento);
    }

    public synchronized Evento retirarEvento(){
        Evento evento1 = eventos.get(0);
        eventos.remove(0);
        return evento1;
    }
}
