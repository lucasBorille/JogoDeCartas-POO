import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private int pontos;
    private int pontosRodada;
    private Carta cartaJogada;
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

    public Carta getCartaJogada(){
        return cartaJogada;
    }

    public void setCartaJogada(String v, String n){
        this.cartaJogada = new Carta(v, n);
    }

    public int getPontos(){
        return pontos;
    }

    public int getPontosRodada(){
        return pontosRodada;
    }

    public void addPontosRodada(int i){
        pontosRodada+=i;
    }

    public void addPontos(int p){
        pontos += p;
    }

    public void limparMao(){
        mao.clear();
    }

    public String getNome(){
        return nome;
    }

    @Override
    public String toString(){
        return nome;
    }

}
