package org.example.C2_COMO_FUNCIONA;

import redis.clients.jedis.Jedis;

public class OlaRedis {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost");
        Jedis jedis = new Jedis();
        String resultado = getEcho(jedis, "ultimo_usuario_logado", "tony_stark");
        System.out.println(resultado);

        System.out.println("------ INICIANDO GET DA VARIAVEL -----");
        String ultimoUsuarioLogado = jedis.get("ultimo_usuario_logado");
        System.out.println(ultimoUsuarioLogado);

        System.out.println("------ REMOVENDO VARIAVEL -------");
        long delLogado = jedis.del("ultimo_usuario_logado");
        System.out.println(delLogado);
    }

    private static String getEcho(Jedis jedis, String key, String value) {
        return jedis.set(key, value);
    }
}
