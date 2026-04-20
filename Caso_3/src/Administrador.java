import java.util.Random;

public class Administrador extends Thread{
    private final Random ran = new Random();
    private final BuzonAlertas buzonAlertas;
    private final BuzonClasificacion buzonClasificacion;
    private final int numClasificadores;

    public Administrador(BuzonAlertas buzonAlertas, BuzonClasificacion buzonClasificacion, int numClasificadores){
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        this.numClasificadores = numClasificadores;
    }

    @Override
    public void run(){
        boolean cent = true;

        while (cent){
            Evento evento = null;
            while(evento == null){
                evento = buzonAlertas.retirarEvento();
                if (evento == null){
                    Thread.yield();
                }
            }

            if (evento.isFinal()){
                for(int i = 0; i < numClasificadores; i++){
                    Evento eventoFin = new Evento("FIN", 0, true);
                    while(!buzonClasificacion.depositar(eventoFin)){
                        Thread.yield();
                    }
                }
                cent = false;
            } else {
                int num = ran.nextInt(21);
                if ((num % 4) == 0){
                    while (!buzonClasificacion.depositar(evento)){
                        Thread.yield();
                    }
                }
            }
        }

    }

}
