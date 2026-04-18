public class Evento {
    private final int seudo;
    private final String id;
    private final boolean fin;

    public Evento(String id, int seudo, boolean fin){
        this.seudo = seudo;
        this.id = id;
        this.fin = fin;
    }

    public int getSeudo(){
        return this.seudo;
    }

    public String getId(){
        return this.id;
    }

    public boolean isFinal(){
        return this.fin;
    }


}
