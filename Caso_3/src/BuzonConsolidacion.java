import java.util.ArrayList;
import java.util.List;

public class BuzonConsolidacion {
    private final List<Evento> lista = new ArrayList<>();
    private final int capacidad;

    public BuzonConsolidacion(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void depositar(Evento e) throws InterruptedException {
        while (lista.size() == capacidad) wait();
        lista.add(e);
        notifyAll();
    }

    public synchronized Evento retirar() throws InterruptedException {
        while (lista.isEmpty()) wait();
        Evento e = lista.remove(0);
        notifyAll();
        return e;
    }
}
