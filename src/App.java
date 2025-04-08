
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] nomes = { "Lucas", "Thiago", "Ricceto", "Talysson" };

        Jogo jogo = new Jogo(nomes);

        while (!jogo.jogoAcabou()) {
            jogo.gerarBaralho();

            jogo.getBaralho().embaralhar();

            jogo.definirManilha();

            jogo.distribuirCartas();

            System.out.println("\nCarta Virada: " + jogo.getCartaVirada());

            int rodada;
            for (rodada = 1; rodada <= 3; rodada++) {

                for (Jogador jogador : jogo.getJogadores()) {

                    jogador.mostrarMao();

                    int cartaJogada;
                    while (true) {
                        System.out.print("Carta: ");
                        cartaJogada = sc.nextInt();

                        if (cartaJogada < 1 || cartaJogada > jogador.getMao().size()) {
                            System.out.println("\nJogada inválida!");
                        } else {
                            break;
                        }
                    }

                    jogo.adicionarJogada(jogador, jogador.jogarCarta(cartaJogada));

                    jogo.mostrarJogadas();
                }

                Time vencedora = jogo.descobrirDuplaVencedoraDaRodada();

                if (vencedora == jogo.getTime1()) {
                    System.out.println("\nTime 1 venceu a rodada!");
                    jogo.getTime1().addPontosRodada(1);
                    if (rodada == 1)
                        jogo.getTime1().setFezPrimeira(true);
                } else if (vencedora == jogo.getTime2()) {
                    System.out.println("\nTime 2 venceu a rodada!");
                    jogo.getTime2().addPontosRodada(1);
                    if (rodada == 1)
                        jogo.getTime2().setFezPrimeira(true);
                } else {
                    System.out.println("\nEMBUXOU!\n");
                    if (rodada == 1) {
                        jogo.getTime1().addPontosRodada(1);
                        jogo.getTime2().addPontosRodada(1);
                    } else if (rodada == 2) {
                        if (jogo.getTime1().getPontosRodada() == 0 || jogo.getTime2().getPontosRodada() == 0) {
                            jogo.getTime1().addPontosRodada(1);
                            jogo.getTime2().addPontosRodada(1);
                        }
                    } else {
                        if (jogo.getTime1().getFezPrimeira()) {
                            jogo.definirDuplaGanhadoraDoPonto(jogo.getTime1(), jogo.getPontosRodada());
                            break;
                        } else if (jogo.getTime2().getFezPrimeira()) {
                            jogo.definirDuplaGanhadoraDoPonto(jogo.getTime2(), jogo.getPontosRodada());
                            break;
                        } else {
                            System.out.println("EMPATOU!");
                            jogo.getTime1().setPontosRodada(0);
                            jogo.getTime2().setPontosRodada(0);
                        }
                    }
                }

                if (jogo.getTime1().getPontosRodada() >= 2) {
                    jogo.definirDuplaGanhadoraDoPonto(jogo.getTime1(), jogo.getPontosRodada());
                    break;
                } else if (jogo.getTime2().getPontosRodada() >= 2) {
                    jogo.definirDuplaGanhadoraDoPonto(jogo.getTime2(), jogo.getPontosRodada());
                    break;
                }

                jogo.limparMesa();
                jogo.limparJogadasNaMesa();

            }

        }
        sc.close();
    }
}
