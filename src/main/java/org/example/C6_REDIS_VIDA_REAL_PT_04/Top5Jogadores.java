package org.example.C6_REDIS_VIDA_REAL_PT_04;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

import java.util.Iterator;
import java.util.List;

public class Top5Jogadores {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        zrevrange(jedis); // Apresenta os 5 melhores jogadores
        zrevrangeWithScores(jedis); // Apresenta os 5 melhores E os SCORES com o Range
    }

    private static void zrevrangeWithScores(Jedis jedis) {
        List<Tuple> jogadores = jedis.zrevrangeWithScores("scores", 0, 4);
        Iterator<Tuple> iterator = jogadores.iterator();
        System.out.println("-------- COM SCORES ------");
        for (int index = 1; iterator.hasNext(); index++) {
            Tuple tuple = iterator.next();
            System.out.println(
                    String.format(
                            "Posição %d - %s (%.0f pontos)",
                            index,
                            tuple.getElement(),
                            tuple.getScore()
                    )
            );
        }
    }

    private static void zrevrange(Jedis jedis) {
        List<String> jogadores = jedis.zrevrange("scores", 0, 4);
        Iterator<String> iterator = jogadores.iterator();
        for (int index = 1; iterator.hasNext(); index++) {
            System.out.println(
                    String.format("Posição %d - %s", index,
                            iterator.next())
            );
        }
    }
}
