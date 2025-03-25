import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private int pontos;
    private final String nome;
    private final ArrayList<Carta> mao;


    public Jogador(String nome) {
        this.nome = nome;
        mao = new ArrayList<>();
        pontos = 0;
    }

    public void receberCarta(Carta carta){
        mao.add(carta);
    }
    
    public List<Carta> getMao(){
        return mao;
    }

    public Carta jogarCarta(int i){
        return mao.remove(i-1);
    }

    public int getPontos(){
        return pontos;
    }

    public void addPontos(int p){
        pontos += p;
    }

    public void limparMao(){
        mao.clear();
    }

    @Override
    public String toString(){
        return nome;
    }

}
