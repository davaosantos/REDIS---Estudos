package org.example.C6_REDIS_VIDA_REAL_PT_04;

import redis.clients.jedis.Jedis;

public class ArmazenarPontuacaoJogador {
    private void definirNovaPontuacao(int codigoJogador,
                                      int ponto
//                                      Double ponto
    ) {
        String chave = String.format("jogador:%04d:codigo",
                codigoJogador);
        Jedis jedis = new Jedis();
        long novaPontuacao = jedis.hincrBy(chave, "pontuacao",
                ponto);

//        double novaPontuacao = jedis.hincrByFloat(chave, "pontuacao",
//                ponto);

        System.out.println(
                String.format(
                        "A pontuação do jogador %04d é: %f",
                        codigoJogador,
                        novaPontuacao
                )
        );
    }

    public void adicionarVitoria(int codigoJogador) {
        definirNovaPontuacao(codigoJogador, 1);
//        definirNovaPontuacao(codigoJogador, 2.0);
    }

    public void adicionarDerrota(int codigoJogador) {
        definirNovaPontuacao(codigoJogador, -1);
//        definirNovaPontuacao(codigoJogador, 1.5);
    }

    public static void main(String[] args) {
        int codigoJogador = 1;
        ArmazenarPontuacaoJogador pontuacaoJogador =
                new ArmazenarPontuacaoJogador();
        pontuacaoJogador.adicionarVitoria(codigoJogador);
        pontuacaoJogador.adicionarVitoria(codigoJogador);
        pontuacaoJogador.adicionarDerrota(codigoJogador);
        pontuacaoJogador.adicionarVitoria(codigoJogador);
    }
};
