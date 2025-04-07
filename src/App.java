
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] nomes = {"Lucas", "Tiago","Ricceto","Talysson"};

        Jogo jogo = new Jogo(nomes);
        

        while (!jogo.jogoAcabou()) {
            jogo.gerarBaralho();
            
            jogo.getBaralho().embaralhar();

            jogo.definirManilha();

            jogo.distribuirCartas();

            System.out.println("Carta Virada: " + jogo.getCartaVirada());

            int rodada;
            for(rodada = 1; rodada<=3; rodada++) {

                for (Jogador jogador : jogo.getJogadores()) {

                    jogador.mostrarMao();

                    // Lendo a carta jogada e adicionando na mesa
                    int cartaJogada = sc.nextInt();
                    jogo.adicionarJogada(jogador, jogador.jogarCarta(cartaJogada));

                    // Mostrando cartas na mesa
                    jogo.mostrarJogadas();
                }
                
                

                Time vencedora = jogo.descobrirDuplaVencedoraDaRodada();

                if(vencedora == jogo.getTime1()){
                    System.out.println("Time 1 Ganhou a rodada");
                    jogo.getTime1().addPontosRodada(1);
                    if(rodada == 1) jogo.getTime1().setFezPrimeira(true);
                }else if(vencedora == jogo.getTime2()){
                    System.out.println("Time 2 Ganhou a rodada");
                    jogo.getTime2().addPontosRodada(1);
                    if(rodada == 1) jogo.getTime2().setFezPrimeira(true);
                }else{
                    System.out.println("EMBUXOU!");
                    if(rodada == 1){
                        jogo.getTime1().addPontosRodada(1);
                        jogo.getTime2().addPontosRodada(1);
                    }else if(rodada == 2){
                        if(jogo.getTime1().getPontosRodada()==0 || jogo.getTime2().getPontosRodada()==0){
                            jogo.getTime1().addPontosRodada(1);
                            jogo.getTime2().addPontosRodada(1);
                        }
                    }else{
                        if(jogo.getTime1().getFezPrimeira()){
                            jogo.definirDuplaGanhadoraDoPonto(jogo.getTime1(), jogo.getPontosRodada());
                            break;
                        }else if(jogo.getTime2().getFezPrimeira()){
                            jogo.definirDuplaGanhadoraDoPonto(jogo.getTime2(), jogo.getPontosRodada());
                            break;
                        }else{
                            System.out.println("EMPATE!");
                        }
                    }
                }

                if(jogo.getTime1().getPontosRodada()>=2){
                    jogo.definirDuplaGanhadoraDoPonto(jogo.getTime1(), jogo.getPontosRodada());
                    break;
                }else if(jogo.getTime2().getPontosRodada()>=2){
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
