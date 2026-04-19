public class MonitorClasificadores {
    private int activos;

    public MonitorClasificadores(int numClasificadores){
        this.activos = numClasificadores;
    }

    public synchronized boolean esUltimo(){
        activos--;
        return activos == 0;
    }
}
