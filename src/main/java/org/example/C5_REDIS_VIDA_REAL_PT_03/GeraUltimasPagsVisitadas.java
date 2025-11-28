package org.example.C5_REDIS_VIDA_REAL_PT_03;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

public class GeraUltimasPagsVisitadas {

    public static void main(String[] args) {
        String chave = "ultimas_paginas_visitadas";
        String[] paginasVisitadas = {
                "/inicio",
                "/contato",
                "/sobre-mim",
                "/todos-os-posts",
                "/armazenando-dados-no-redis"
        };

        Jedis jedis = new Jedis();
        adicionaElementosLista(jedis, chave, paginasVisitadas);

        List<String> ultimasTresPags = jedis.lrange(chave, 0, 2);
        ultimasTresPags.forEach(System.out::println);

        System.out.println(String.format("Tamanho da lista antes do LTRIM : %s", jedis.llen(chave)));

        System.out.println(String.format("Tamanho da lista após do LTRIM : %s", jedis.ltrim(chave, 0, 2)));


    }

    private static void adicionaElementosLista(Jedis jedis, String chave, String[] paginasVisitadas) {
        Long resultado = jedis.lpush(chave, paginasVisitadas);

        System.out.println(String.format(
                "A lista %s contem %d numero de elementos", chave, resultado
        ));
    }
}