import java.util.Random;

public class Sensor extends Thread {

    private final int id;
    private final int numEventos;
    private final int ns;
    private final BuzonIlimitado buzonEntrada;
    private final Random ran = new Random();

    public Sensor(int id, int valorBase, int ns, BuzonIlimitado buzonEntrada){
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
        int generado = ran.nextInt(ns) + 1;
        return generado;
    }

    @Override
    public void run(){
        for(int i = 1; i <= numEventos; i++){
            Evento evento = generadorEvento(i);
            buzonEntrada.depositarEvento(evento);
        }
    }

}
