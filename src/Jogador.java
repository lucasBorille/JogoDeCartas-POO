import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private final String nome;
    private final ArrayList<Carta> mao;

    public Jogador(String nome) {
        this.nome = nome;
        mao = new ArrayList<>();
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public Carta jogarCarta(int i){
        return mao.remove(i-1);
    }

    public List<Carta> getMao() {
        return mao;
    }

    public void mostrarMao(){
        System.out.println("Mão " + this.nome + ", Faça sua jogada"); 
        for(Carta carta: this.mao){
            System.out.print(carta + ", ");
        }
    }

    public void limparMao() {
        mao.clear();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
