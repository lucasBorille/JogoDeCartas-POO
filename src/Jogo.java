import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private Baralho baralho;
    private String cartaVirada;
    private int pontosRodada;
    private Time time1;
    private Time time2;
    private List<Jogada> jogadasNaMesa = new ArrayList<>();
    private List<Jogador> jogadores = new ArrayList<>();

    public Jogo(String[] nomes) {
        this.pontosRodada = 1;

        time1 = new Time(new Jogador(nomes[0]), new Jogador(nomes[1]));
        time2 = new Time(new Jogador(nomes[2]), new Jogador(nomes[3]));

        jogadores.add(time1.getJogadores().get(0));
        jogadores.add(time2.getJogadores().get(0));
        jogadores.add(time1.getJogadores().get(1));
        jogadores.add(time2.getJogadores().get(1));
    }

    public void gerarBaralho() {
        this.baralho = new Baralho();
    }

    public Baralho getBaralho() {
        return this.baralho;
    }

    public void definirManilha() {
        this.cartaVirada = baralho.darCarta().getValor();
        String valorManilha = proximaCarta(cartaVirada);

        for (Carta c : baralho.getCartas()) {
            if (c.getValor().equals(valorManilha)) {
                c.setManilha();
            }
        }
    }

    public String getCartaVirada() {
        return this.cartaVirada;
    }

    private String proximaCarta(String valor) {
        String[] ordem = { "4", "5", "6", "7", "Q", "J", "K", "A", "2", "3" };
        for (int i = 0; i < ordem.length; i++) {
            if (ordem[i].equals(valor)) {
                return ordem[(i + 1) % ordem.length];
            }
        }
        return null;
    }

    public void distribuirCartas() {
        for (Jogador jogador : this.jogadores) {
            for (int i = 0; i < 3; i++) {
                jogador.receberCarta(baralho.darCarta());
            }
        }
    }

    public Time getTime1() {
        return time1;
    }

    public Time getTime2() {
        return time2;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void adicionarJogada(Jogador jogador, Carta carta) {
        jogadasNaMesa.add(new Jogada(jogador, carta));
    }

    public Jogada descobrirJogadaVencedora() {
        int maiorForca = -1;
        List<Jogada> candidatas = new ArrayList<>();

        for (Jogada jogada : jogadasNaMesa) {
            int forca = jogada.getCarta().getForca();

            if (forca > maiorForca) {
                maiorForca = forca;
                candidatas.clear();
                candidatas.add(jogada);
            } else if (forca == maiorForca) {
                candidatas.add(jogada);
            }
        }

        boolean houveEmpate = candidatas.size() > 1;

        for (Jogada j : jogadasNaMesa) {
            if (j.getCarta().getForca() == maiorForca) {
                j.setEmpate(houveEmpate);
            }
        }

        return candidatas.get(0);
    }

    public Time descobrirDuplaVencedoraDaRodada() {
        int forcaMaxima = -1;
        for (Jogada jogada : jogadasNaMesa) {
            if (jogada.getCarta().getForca() > forcaMaxima) {
                forcaMaxima = jogada.getCarta().getForca();
            }
        }

        List<Jogador> empatados = new ArrayList<>();
        for (Jogada jogada : jogadasNaMesa) {
            if (jogada.getCarta().getForca() == forcaMaxima) {
                empatados.add(jogada.getJogador());
            }
        }

        if (empatados.size() == 1) {
            Jogador vencedor = empatados.get(0);
            if (time1.getJogadores().contains(vencedor))
                return time1;
            else
                return time2;
        }

        boolean todosNaDupla1 = true;
        boolean todosNaDupla2 = true;

        for (Jogador j : empatados) {
            if (!time1.getJogadores().contains(j))
                todosNaDupla1 = false;
            if (!time2.getJogadores().contains(j))
                todosNaDupla2 = false;
        }

        if (todosNaDupla1)
            return time1;
        if (todosNaDupla2)
            return time2;

        return null;
    }

    public void definirDuplaGanhadoraDoPonto(Time time, int p) {
        String nome1 = time.getJogadores().get(0).getNome();
        String nome2 = time.getJogadores().get(1).getNome();
        System.out.println(nome1 + " e " + nome2 + " ganharam " + p + " ponto(s)!");
        time.addPontos(p);
        System.out.println(
                "\n=== PLACAR: Time 1 " + this.time1.getPontos() + " X " + this.time2.getPontos() + " Time 2 ===");
        for (Jogador jogador : jogadores) {
            jogador.limparMao();
        }
        time1.setPontosRodada(0);
        time2.setPontosRodada(0);
        limparJogadasNaMesa();
        limparMesa();
        time1.setFezPrimeira(false);
        time2.setFezPrimeira(false);
    }

    public void limparMesa() {
        jogadasNaMesa.clear();
    }

    public void mostrarJogadas() {
        for (Jogada jogada : jogadasNaMesa) {
            System.out.println(jogada);
        }
    }

    public void limparJogadasNaMesa() {
        jogadasNaMesa.clear();
    }

    public boolean jogoAcabou() {
        List<Time> times = new ArrayList<>();
        times.add(time1);
        times.add(time2);

        for (Time time : times) {
            if (time.getPontos() >= 12) {
                return true;
            }
        }
        return false;
    }

    public int getPontosRodada() {
        return pontosRodada;
    }

}
