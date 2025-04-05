
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ArrayList<Carta> mesa = new ArrayList<>();

        Jogador jogador1 = new Jogador("Riccetto");
        Jogador jogador2 = new Jogador("Lucas");

        ArrayList<Jogador> jogadorInicial = new ArrayList<>();

        jogadorInicial.add(jogador1);
        jogadorInicial.add(jogador2);

        int inicial = 0;

        while (jogador1.getPontos() < 12 && jogador2.getPontos() < 12) {
            boolean verify = true;
            Jogador jogadorQueComeca = jogadorInicial.get(inicial);

            Baralho baralho = new Baralho();
            baralho.embaralhar();

            // Distribuindo as cartas

            for (int i = 0; i < 3; i++) {
                jogador1.receberCarta(baralho.darCarta());
                jogador2.receberCarta(baralho.darCarta());
            }
            System.out.println();

            // Criar Manilha
            String[] valores = { "4", "5", "6", "7", "Q", "J", "K", "A", "2", "3" };
            List<String> valoresArray = Arrays.asList(valores);

            String manilha = baralho.darCarta().getValor();
            // Printando a carta virada
            System.out.println("Carta Virada: " + manilha);

            // Dando o valor da manilha como o proximo
            for (String carta : valoresArray) {
                if (carta == manilha) {

                    if (manilha == "3") {
                        manilha = "4";
                        break;
                    } else {
                        manilha = valoresArray.get(valoresArray.indexOf(carta) + 1);
                        break;
                    }

                }
            }
            int rodada = 1;
            jogador1.setFezPrimeira(false);
            jogador2.setFezPrimeira(false);
            while (verify) {
                // Criando array de dois jogadores e definindo a ordem
                ArrayList<Jogador> jogadores = new ArrayList<>();
                if (jogadorQueComeca == jogador1) {
                    jogadores.add(jogador1);
                    jogadores.add(jogador2);
                } else {
                    jogadores.add(jogador2);
                    jogadores.add(jogador1);
                }

                // Jogada jogador 1

                for (Jogador jogador : jogadores) {

                    // Mostrando a mão
                    System.out.println("RODADA: " + rodada);
                    System.out.println("Mão " + jogador.getNome() + ", Faça sua jogada");

                    for (Carta carta : jogador.getMao()) {
                        if (carta.getValor().equals(manilha)) {
                            carta.setManilha();
                        }
                        System.out.print(carta + ", ");
                    }

                    // Lendo a carta jogada e adicionando na mesa
                    int cartaJogada = sc.nextInt();
                    mesa.add(jogador.jogarCarta(cartaJogada));
                    jogador.setCartaJogada((mesa.get(mesa.size() - 1).getValor()),
                            mesa.get(mesa.size() - 1).getNaipe());
                    if (jogador.getCartaJogada().getValor() == manilha) {
                        jogador.getCartaJogada().setManilha();
                    }
                    // Mostrando cartas na mesa
                    System.out.println("Cartas da mesa: ");
                    for (Carta carta : mesa) {
                        System.out.print(carta + ", ");
                    }
                    System.out.println();
                }

                // Definindo a maior forca da mesa
                int maiorForca = -1;

                for (Carta carta : mesa) {
                    if (carta.getForca() > maiorForca) {
                        maiorForca = carta.getForca();
                    }
                }

                ArrayList<Jogador> vencedores = new ArrayList<>();
    
                for (Jogador jogador : jogadores) {
                    if (jogador.getCartaJogada().getForca() == maiorForca) {
                        vencedores.add(jogador);
                    }
                }
                mesa.clear();

                if (vencedores.size() > 1) {
                    System.out.println("Embuxou!");
                    if(rodada == 1){
                        jogador1.addPontosRodada(1);
                        jogador2.addPontosRodada(1);
                    }else if(rodada == 2){
                        if(jogador1.getPontosRodada() == 1 && jogador2.getPontosRodada()==0){
                            jogador1.addPontosRodada(1);
                        }else if(jogador2.getPontosRodada() == 1 && jogador1.getPontosRodada()==0){
                            jogador2.addPontosRodada(1);
                        }
                    }else{
                        if(jogador1.getFezPrimeira()){
                            jogador1.setPontosRodada(2);
                            jogador2.setPontosRodada(0);
                        }else if(jogador2.getFezPrimeira()){
                            jogador2.setPontosRodada(2);
                            jogador1.setPontosRodada(0);
                        }else{
                            System.out.println("EMPATE");
                            break;
                        }
                    }
                } else {
                    if(rodada == 1){
                        vencedores.get(0).setFezPrimeira(true);
                    }
                    System.out.println(vencedores.get(0).getNome() + " Ganhou a rodada!");
                    vencedores.get(0).addPontosRodada(1);
                    jogadorQueComeca = vencedores.get(0);
                }

                for (Jogador jogador : jogadores) {
                    if (jogador.getPontosRodada() == 2) {
                        jogador.addPontosRodada(-2);
                        jogador.addPontos(1); // ALTERAR DEPOIS PARA 1
                        System.out.println(jogador.getNome() + " Ganhou 1 ponto!");
                        verify = false;
                    }
                }
                if (!verify) {
                    for (Jogador jogador : jogadores) {
                        jogador.limparMao();
                        jogador.setPontosRodada(0);
                    }
                }
                rodada++;
            }

            if (inicial == jogadorInicial.size() - 1) {
                inicial = 0;
            } else {
                inicial++;
            }
        }
        sc.close();
    }
}
