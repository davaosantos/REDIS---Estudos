package org.example.C5_REDIS_VIDA_REAL_PT_03;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ConsumirItemDaFila {
    class Mensagem {
        private String nome;
        private String email;
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email;}
    };
    public void enviarEmailAtivacaoUsuario() {
        int timeout = 2;
        String chave = "fila:confirmar-usuario";
        Jedis jedis = new Jedis();
        List<String> mensagens = jedis.brpop(timeout, chave);
//        List<String> mensagens = jedis.blpop(timeout, chave);
        if (mensagens == null) {
            System.out.println(String.format(
                    "A fila %s está vazia.", chave));
        }
        else {
            String json = mensagens.get(1);
            Mensagem mensagem =
                    new Gson().fromJson(json, Mensagem.class);
            System.out.println(
                    String.format(
                            "Enviando e-mail para %s (%s)",
                            mensagem.getEmail(),
                            mensagem.getNome()
                    )
            );
        }
    }
    public static void main(String[] args) {
        ConsumirItemDaFila fila = new ConsumirItemDaFila();
        while (true) {
            fila.enviarEmailAtivacaoUsuario();
        }
    }
}

