import java.util.ArrayList;
import java.util.List;

public class BuzonConsolidacion {
    private final List<Evento> lista = new ArrayList<>();
    private final int capacidad;

    public BuzonConsolidacion(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void depositar(Evento evento) {
        while (lista.size() == capacidad) {
            try{
                wait();
            } catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }
        lista.add(evento);
        notify();
    }

    public synchronized Evento retirar() {
        while (lista.isEmpty()) {
            try {
                wait();
            } catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }
        Evento e = lista.remove(0);
        notify();
        return e;
    }
}
