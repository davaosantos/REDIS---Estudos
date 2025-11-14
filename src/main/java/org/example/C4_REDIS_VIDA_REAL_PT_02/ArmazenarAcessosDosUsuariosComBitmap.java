package org.example.C4_REDIS_VIDA_REAL_PT_02;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class ArmazenarAcessosDosUsuariosComBitmap {
    public void armazenar(long codigoDoUsuario, String data) {
        Jedis jedis = new Jedis();
        String chave = String.format("acesso:%s", data);
        jedis.setbit(chave, codigoDoUsuario, true);
    }

    public static void main(String[] args) {
        int quantidadeDeUsuarios = 500;
        int quantidadeDeAcessos = 1000;
        int quantidadeDeDias = 30;
        Random random = new Random();
        ArmazenarAcessosDosUsuariosComBitmap acesso =
                new ArmazenarAcessosDosUsuariosComBitmap();
        for (Integer numero = 1; numero <= quantidadeDeAcessos; numero++) {
            long usuario = (random.nextInt(quantidadeDeUsuarios) + 1);
            String data = String.format(
                    "%02d/11/2013",
                    (random.nextInt(quantidadeDeDias) + 1)
            );
            acesso.armazenar(usuario, data);
        }
    }
}

