
import java.util.ArrayList;
import java.util.List;

public class Time {
    private List<Jogador> jogadores = new ArrayList<>();
    private int pontosRodada;
    private int pontos;
    private boolean fezPrimeira;

    public Time(Jogador j1, Jogador j2){
        jogadores.add(j1);
        jogadores.add(j2);
        pontosRodada = 0;
        pontos = 0;
        fezPrimeira = false;
    }
    public List<Jogador> getJogadores(){
        return jogadores;
    }

    public int getPontos() {
        return pontos;
    }

    public int getPontosRodada() {
        return pontosRodada;
    }

    public void setPontosRodada(int p){
        pontosRodada = p;
    }

    public void addPontosRodada(int i) {
        pontosRodada += i;
    }

    public void addPontos(int p) {
        pontos += p;
    }

    public boolean getFezPrimeira(){
        return fezPrimeira;
    }

    public void setFezPrimeira(boolean t){
        fezPrimeira = t;
    }

}
