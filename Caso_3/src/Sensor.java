import java.util.Random;

public class Sensor extends Thread {

    private final int id;
    private final int numEventos;
    private final int ns;
    private final Buzon buzonEntrada;

    public Sensor(int id, int valorBase, int ns, Buzon buzonEntrada){
        this.id = id;
        this.ns = ns;
        this.numEventos =  id*valorBase;
        this.buzonEntrada = buzonEntrada;
    }

    public Evento generadorEvento(int num){
        String idEvento = this.id + "_" + num;
        int seudo = generadorSeudo(this.ns);
        boolean fin = false;
        Evento nuevoEvento = new Evento(idEvento, seudo, fin);

        return nuevoEvento;

    }

    public final int generadorSeudo(int ns){
        Random ran = new Random();
        int generado = ran.nextInt(ns) + 1;
        return generado;
    }

}
