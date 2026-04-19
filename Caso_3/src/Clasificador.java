import java.util.List;

public class Clasificador extends Thread{
    private final BuzonClasificacion buzonClasificacion;
    private final BuzonConsolidacion buzonConsolidacion;
    private final List<BuzonConsolidacion> buzonesConsolidacion;
    private final MonitorClasificadores monitor;
    private final int numServidores;

    public Clasificador(BuzonClasificacion buzonClasificacion, BuzonConsolidacion buzonConsolidacion, List<BuzonConsolidacion> buzonesConsolidacion, MonitorClasificadores monitor, int numServidores){
        this.buzonClasificacion = buzonClasificacion;
        this.buzonConsolidacion = buzonConsolidacion;
        this.buzonesConsolidacion = buzonesConsolidacion;
        this.monitor = monitor;
        this.numServidores = numServidores;
    }

    @Override
    public void run(){
        boolean cent = true;
        while (cent){
            Evento evento = buzonClasificacion.retirar();
            
            if (evento.isFinal()){
                if(monitor.esUltimo()){
                    for(BuzonConsolidacion buzon : buzonesConsolidacion){
                        Evento eventoFin = new Evento("FIN", 0, true);
                        buzon.depositar(eventoFin);
                    } 
                }
                break;
            }

            
        }
    }



}
