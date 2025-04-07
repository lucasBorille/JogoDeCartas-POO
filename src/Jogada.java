public class Jogada {
    private Jogador jogador;
    private Carta carta;
    private boolean empate;

    public Jogada(Jogador jogador, Carta carta) {
        this.jogador = jogador;
        this.carta = carta;
        this.empate = false;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Carta getCarta() {
        return carta;
    }

    public boolean getEmpate(){
        return empate;
    }

    public void setEmpate(boolean empate){
        this.empate = empate;
    }

    @Override
    public String toString() {
        return jogador.getNome() + " jogou " + carta;
    }
}