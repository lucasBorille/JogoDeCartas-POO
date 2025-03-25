
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ArrayList<Carta> mesa = new ArrayList<>();

        Jogador jogador1 = new Jogador("Riccetto");
        Jogador jogador2 = new Jogador("Lucas");

        boolean verify = true;
        while (verify) { 
            Baralho baralho = new Baralho();
            baralho.embaralhar();
            int i;
            // Distribuindo as cartas
            for (i = 0; i < 3; i++) {
                jogador1.receberCarta(baralho.darCarta());
                jogador2.receberCarta(baralho.darCarta());
            }

            //Jogada jogador 1

            //Mostrando a mão
            System.out.println("Mão Jogador 1, Faça sua jogada");
            for (Carta carta : jogador1.getMao()) {
                System.out.print(carta+ ", ");
            }
            //Lendo a carta jogada e adicionando na mesa
            int cartaJogada = sc.nextInt();
            mesa.add(jogador1.jogarCarta(cartaJogada));

            //Mostrando cartas na mesa
            System.out.println("Cartas da mesa: ");
            for (Carta carta : mesa) {
                System.out.print(carta + ", ");
            }
            System.out.println();

            //Mesma coisa com o jogador 2
            System.out.println("Mão Jogador 2, Faça sua jogada");
            for (Carta carta : jogador2.getMao()) {
                System.out.print(carta+ ", ");
            }
            System.out.println();
            cartaJogada = sc.nextInt();

            mesa.add(jogador2.jogarCarta(cartaJogada));

            System.out.println("Cartas da mesa: ");
            for (Carta carta : mesa) {
                System.out.print(carta+ ", ");
            }

            String[] valores = {"4","5","6","7","Q","J","K","A","2","3"};

            verify = false;
        }
        
    }
}
