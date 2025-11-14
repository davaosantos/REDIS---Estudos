package org.example.C4_REDIS_VIDA_REAL_PT_02;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class ExpiracaoSessao {

    private static final Jedis jedisFinal = new Jedis();

    public static void main(String[] args) {

        String codUsuario = "0573";
        String nmUsuario = "David Alisson";
        String chave = "sessao:usuario:" + codUsuario;
        int trintaMinutosEmSegundos = 1800;

        String emailUsuario = "david_Alisson60@yahoo.com.br";

//        testandoHmSet(codUsuario, nmUsuario, emailUsuario);

        long resultado = jedisFinal.expire(chave,
                trintaMinutosEmSegundos);
        System.out.println(resultado);
    }

    private static void testandoHmSet(String codUsuario, String nmUsuario, String emailUsuario, String chave) {
        Map<String, String> campos = new HashMap<>(){
            {
                put("codigo", codUsuario);
                put("nome", nmUsuario);
                put("email", emailUsuario);
            }
        };

        String resultado = jedisFinal.hmset(chave, campos);
        System.out.println(resultado);
    }


}
