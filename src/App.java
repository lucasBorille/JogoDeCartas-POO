
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


        boolean verify = true;
        Jogador jogadorQueComeca = jogador1;

        Baralho baralho = new Baralho();
            baralho.embaralhar();

            // Distribuindo as cartas

            for(int i=0;i<3;i++){
                jogador1.receberCarta(baralho.darCarta());
                jogador2.receberCarta(baralho.darCarta());
            }
            System.out.println();

        while (verify) { 
            //Criando array de dois jogadores e definindo a ordem
            ArrayList<Jogador> jogadores = new ArrayList<>();
            if(jogadorQueComeca == jogador1){
                jogadores.add(jogador1);
                jogadores.add(jogador2);  
            }else{
                jogadores.add(jogador2);  
                jogadores.add(jogador1);
            }

            //Jogada jogador 1

            for (Jogador jogador : jogadores) {
                //Mostrando a mão
                System.out.println("Mão " + jogador.getNome() +", Faça sua jogada");
                for (Carta carta : jogador.getMao()) {
                    System.out.print(carta + ", ");
                }

                //Lendo a carta jogada e adicionando na mesa
                int cartaJogada = sc.nextInt();
                mesa.add(jogador.jogarCarta(cartaJogada));
                jogador.setCartaJogada((mesa.get(mesa.size()-1).getValor()), mesa.get(mesa.size()-1).getNaipe());
                //Mostrando cartas na mesa
                System.out.println("Cartas da mesa: ");
                for (Carta carta : mesa) {
                    System.out.print(carta + ", ");
                }
                System.out.println();
            }

           //Criando valores das cartas(aqui mudaria com a manilha)

            String[] valores = {"4","5","6","7","Q","J","K","A","2","3"};
            List<String> valoresArray = Arrays.asList(valores);


            int indiceMaior = -1;

            for(Carta carta: mesa){
                if(valoresArray.indexOf(carta.getValor()) > indiceMaior){
                    indiceMaior = valoresArray.indexOf(carta.getValor());
                }    
            }
            ArrayList<Jogador> vencedores = new ArrayList<>();
            for (Jogador jogador : jogadores) {

                if(jogador.getCartaJogada().getValor().equals(valoresArray.get(indiceMaior))){
                    vencedores.add(jogador);
                }
            }
            mesa.clear();
            

            if(vencedores.size()>1){
                System.out.println("Embuxou!");
                for (Jogador jogador : vencedores) {
                    jogador.addPontosRodada(1);
                }
            }else {
                System.out.println(vencedores.get(0).getNome() + " Ganhou!");
                vencedores.get(0).addPontosRodada(1);
                jogadorQueComeca = vencedores.get(0);
            }

            

            for (Jogador jogador : jogadores) {
                if(jogador.getPontosRodada()== 2){
                    jogador.addPontosRodada(-2);
                    jogador.addPontos(1);
                    System.out.println(jogador.getNome() + " Ganhou 1 ponto!");
                    verify = false;
                }
            }
        }
        sc.close();
    }
}
