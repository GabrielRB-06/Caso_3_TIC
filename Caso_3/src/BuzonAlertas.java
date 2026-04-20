import java.util.ArrayList;
import java.util.List;

public class BuzonAlertas {
    private final List<Evento> eventos = new ArrayList<>();

    public synchronized void depositarEvento(Evento evento){
        eventos.add(evento);
    }

    public synchronized Evento retirarEvento(){
        if (eventos.isEmpty()){
            return null;
        }
        return eventos.remove(0);
    }
}
