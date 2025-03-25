import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho{

    private final List<Carta> cartas;

    public Baralho(){
        cartas = new ArrayList<>();
        String[] naipes = {"Espadas","Copas","Paus", "Ouros"};
        String[] valores = {"4","5","6","7","Q","J","K","A","2","3"};

        for(String naipe: naipes){
            for(String valor: valores){
                cartas.add(new Carta(valor,naipe));
            }
        }
    }

    public void embaralhar(){
        Collections.shuffle(cartas);
    }

    public Carta darCarta() {
        if(cartas.isEmpty()){
            return null;
        }
        return cartas.remove(0);
    }
}
