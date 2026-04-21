import java.util.List;

public class Clasificador extends Thread{
    private final BuzonClasificacion buzonClasificacion;
    private final List<BuzonConsolidacion> buzonesConsolidacion;
    private final MonitorClasificadores monitor;

    public Clasificador(BuzonClasificacion buzonClasificacion, List<BuzonConsolidacion> buzonesConsolidacion, MonitorClasificadores monitor){
        this.buzonClasificacion = buzonClasificacion;
        this.buzonesConsolidacion = buzonesConsolidacion;
        this.monitor = monitor;
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
            } else {
                int indice = evento.getSeudo() - 1;
                buzonesConsolidacion.get(indice).depositar(evento);
            }
        }
    }
}
