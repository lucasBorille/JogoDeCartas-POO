import java.util.Arrays;
import java.util.List;

public class Carta {
    private final String valor;
    private final String naipe;
    private int forca;

    public Carta(String valor, String naipe){
        this.valor = valor;
        this.naipe = naipe;

        String[] valores = {"4","5","6","7","Q","J","K","A","2","3"};
        List<String> valoresArray = Arrays.asList(valores);

        for (String carta : valoresArray) {
            if(this.valor.equals(carta)){
                this.forca = valoresArray.indexOf(carta);
            }
        }
    }

    public String getValor(){
        return this.valor;
    }

    public String getNaipe(){
        return this.naipe;
    }

    public int getForca(){
        return this.forca;
    }


    public void setManilha(){
        switch (naipe) {
            case "Ouros": this.forca = 11; break;
            case "Espadas": this.forca = 12; break;
            case "Copas": this.forca = 13; break;
            case "Paus": this.forca = 14; break;
            default:
            break;
        }
    }

    @Override
    public String toString(){
        return (valor + " de " + naipe);
    }
}

