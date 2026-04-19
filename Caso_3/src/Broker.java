import java.util.Random;

public class Broker extends Thread{
    private final Random ran = new Random();
    private final int totalEventos;
    private final BuzonIlimitado buzonEntrada;
    private final BuzonIlimitado buzonAlertas;
    private final BuzonClasificacion buzonClasificacion;

    public Broker(int totalEventos, BuzonIlimitado buzonEntrada, BuzonIlimitado buzonAlertas, BuzonClasificacion buzonClasificacion){
        this.totalEventos = totalEventos;
        this.buzonEntrada = buzonEntrada;
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
    }

    @Override
    public void run(){
        int contador = 0;
        while(contador < totalEventos){
            Evento evento = buzonEntrada.retirarEvento();
            int generado = ran.nextInt(201); 
            if ((generado % 8) == 0){
                buzonAlertas.depositarEvento(evento);
            }
            else {
                while(!buzonClasificacion.depositar(evento)){
                    Thread.yield();
                }
            }
            contador++;
        }
        Evento eventoFin = new Evento("FIN", 0, true);
        buzonAlertas.depositarEvento(eventoFin);
    }

}
