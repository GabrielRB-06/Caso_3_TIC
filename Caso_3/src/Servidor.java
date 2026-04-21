import java.util.Random;

public class Servidor extends Thread{
    private final int id;
    private final BuzonConsolidacion buzonConsolidacion;
    private final Random ran = new Random();

    public Servidor(int id, BuzonConsolidacion buzonConsolidacion){
        this.id = id;
        this.buzonConsolidacion = buzonConsolidacion;
    }

    @Override
    public void run(){
        int procesados = 0;
        while (true){
            Evento evento = buzonConsolidacion.retirar();

            if (evento.isFinal()){
                break;
            }

            int tiempoProcesamiento = ran.nextInt(901) + 100;
            try {
                Thread.sleep(tiempoProcesamiento);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }

            procesados++;
            System.out.println("[Servidor " + id + "] procesó el evento " + evento.getId());
        }
        System.out.println("[Servidor " + id + "] terminó. Procesó " + procesados + " eventos.");
    }

}
