import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

//Lectura de archivos:
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int ni = Integer.parseInt(br.readLine().trim());
        int valorBase = Integer.parseInt(br.readLine().trim());
        int nc = Integer.parseInt(br.readLine().trim());
        int ns = Integer.parseInt(br.readLine().trim());
        int tam1 = Integer.parseInt(br.readLine().trim());
        int tam2 = Integer.parseInt(br.readLine().trim());
        br.close();

        int totalEventos = valorBase * ni * (ni + 1) / 2;

        System.out.println("Iniciando sistema IoT...");
        System.out.println("Sensores: " + ni + " | Clasificadores: " + nc + " | Servidores: " + ns + " | Eventos Totales: " + totalEventos);

//Inicializar buzones y componentes:
        BuzonEntrada buzonEntrada = new BuzonEntrada();
        BuzonAlertas buzonAlertas = new BuzonAlertas();
        BuzonClasificacion buzonClasificacion = new BuzonClasificacion(tam1);

        List<BuzonConsolidacion> buzonesConsolidacion = new ArrayList<>();
        for (int i = 0; i < ns; i++){
            buzonesConsolidacion.add(new BuzonConsolidacion(tam2));
        }

        List<Sensor> sensores = new ArrayList<>();
        for (int i = 1; i <= ni; i++){
            sensores.add(new Sensor(i, valorBase, ns, buzonEntrada));
        }

        Broker broker = new Broker(totalEventos, buzonEntrada, buzonAlertas, buzonClasificacion);
        Administrador administrador = new Administrador(buzonAlertas, buzonClasificacion, nc);
        MonitorClasificadores monitor = new MonitorClasificadores(nc);

        List<Clasificador> clasificadores = new ArrayList<>();
        for (int i = 0; i < nc; i++){
            clasificadores.add(new Clasificador(buzonClasificacion, buzonesConsolidacion, monitor));
        }

        List<Servidor> servidores = new ArrayList<>();
        for (int i = 1; i <= ns; i++){
            servidores.add(new Servidor(i, buzonesConsolidacion.get(i - 1)));
        }

//Inicializar threads:
        for (Servidor servidor : servidores){
            servidor.start();
        }

        for (Clasificador clasificador : clasificadores){
            clasificador.start();
        }

        administrador.start();
        broker.start();
        
        for (Sensor sensor : sensores){
            sensor.start();
        }

//Esperar a que terminen en orden:
        for (Sensor sensor : sensores){
            sensor.join();
        }
        broker.join();
        administrador.join();
        for (Clasificador clasificador : clasificadores){
            clasificador.join();
        }
        for (Servidor servidor : servidores){
            servidor.join();
        }

        System.out.println("Sistema IoT finalizado. Todos los eventos fueron procesados y todos los buzones quedaron vacíos...");
    }

}
