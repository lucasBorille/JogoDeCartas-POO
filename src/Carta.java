public class Carta {
    private final String valor;
    private final String naipe;

    public Carta(String valor, String naipe){
        this.valor = valor;
        this.naipe = naipe;
    }

    public String getValor(){
        return valor;
    }

    public String getNaipe(){
        return naipe;
    }
}

