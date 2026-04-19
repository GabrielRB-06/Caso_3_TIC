import java.util.ArrayList;
import java.util.List;

public class BuzonClasificacion {
    private final List<Evento> lista = new ArrayList<>();
    private final int capacidad;

    public BuzonClasificacion(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized boolean depositar(Evento e) {
        if (lista.size() < capacidad) {
            lista.add(e);
            return true; // éxito
        }
        return false; // lleno, el thread maneja el yield
    }

    public synchronized Evento retirar() {
        if (!lista.isEmpty()) return lista.remove(0);
        return null; // el Clasificador maneja el yield
    }
}