package org.example.C3_REDIS_VIDA_REAL;

import redis.clients.jedis.Jedis;

public class RedisLotoFacil {

    public static void main(String[] args) {

//        exemploSemData(jedis);

        Jedis jedis = new Jedis();
        String dataSorteio1 = "01-12-2025";
        String numerosSorteio1 = "10, 11, 28, 35, 40, 44";
        String chave1 = String.format("resultado:%s:megasena", dataSorteio1); // resultado:01-12-2025:megasena

        String dataSorteio2 = "08-12-2025";
        String numerosSorteio2 = "12, 14, 25, 37, 42, 50";
        String chave2 = String.format("resultado:%s:megasena", dataSorteio2);

        String dataSorteio3 = "15-12-2025";
        String numerosSorteio3 = "15, 22, 27, 34, 41, 52";
        String chave3 = String.format("resultado:%s:megasena", dataSorteio3);

        String dataSorteio4 = "21-12-2025";
        String numerosSorteio4 = "11, 16, 29, 39, 49, 51";
        String chave4 = String.format("resultado:%s:megasena", dataSorteio4);

        String resultado = jedis.mset(
                chave1, numerosSorteio1,
                chave2, numerosSorteio2,
                chave3, numerosSorteio3,
                chave4, numerosSorteio4
        );

        System.out.println(resultado);
    }

    private static void exemploSemData(Jedis jedis) {
        String chave = "resultado:megasena";
        String numerosUltimoSorteio = "4,7,9,21,33";
        String resultado = jedis.set(chave, numerosUltimoSorteio);
        System.out.println(resultado);
    }
}
