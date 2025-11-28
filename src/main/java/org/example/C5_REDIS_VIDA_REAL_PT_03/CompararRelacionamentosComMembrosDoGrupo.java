package org.example.C5_REDIS_VIDA_REAL_PT_03;

import org.example.UtilRedis;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class CompararRelacionamentosComMembrosDoGrupo {
    public void verAmigosDoGrupo(String pessoa, String grupo) {
        String chavePessoa = String.format(
                "pessoas:%s:relacionamentos", pessoa
        );
        String chaveGrupo = String.format(
                "grupos:%s:membros", grupo
        );

        Jedis jedis = UtilRedis.getJedisInstance();

        Set<String> pessoas = jedis.sinter(chavePessoa,
                chaveGrupo);
        System.out.println(String.format(
                        "%s são amigos de %s " +
                                "e fazem também parte do grupo que gosta de %s",
                        pessoas.toString(),
                        pessoa,
                        grupo
                )
        );
    }

    public void alteraGrupo(String pessoa, String grupoOrigem, String grupoDestino){

        String chavePessoa = String.format(
                "pessoas:%s:relacionamentos", pessoa
        );

        String chaveGrupoOrigem = String.format(
                "grupos:%s:membros", grupoOrigem
        );

        String chaveGrupoDestino = String.format(
                "grupos:%s:membros", grupoDestino
        );

        System.out.println(UtilRedis.getJedisInstance().smove(chaveGrupoOrigem, chaveGrupoDestino, pessoa));
    }

    public static void main(String[] args) {
        CompararRelacionamentosComMembrosDoGrupo relacionamentos
                = new CompararRelacionamentosComMembrosDoGrupo();
        relacionamentos.verAmigosDoGrupo("rafael", "cachorro");
        relacionamentos.verAmigosDoGrupo("rodrigo",
                "video-game");
        relacionamentos.verAmigosDoGrupo("andressa", "novela");

        relacionamentos.alteraGrupo("rodrigo", "video-game", "judo");
    }
}

