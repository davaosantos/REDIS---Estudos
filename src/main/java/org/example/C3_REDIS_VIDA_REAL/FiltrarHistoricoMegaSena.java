package org.example.C3_REDIS_VIDA_REAL;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class FiltrarHistoricoMegaSena {

    private static final Jedis jedisSing = new Jedis();

    public static void main(String[] args) {

        FiltrarHistoricoMegaSena filtrarHistoricoMegaSena = new FiltrarHistoricoMegaSena();

//        filtragemPorParametros(filtrarHistoricoMegaSena);
        filtragemPorHash(filtrarHistoricoMegaSena);
    }

    private static void filtragemPorHash(FiltrarHistoricoMegaSena filtrarHistoricoMegaSena) {
        String ganhadores = "22";
        String dataSorteio = "30-12-2025";
        String numeros = "8, 18, 26, 42, 56, 58";

        String chave = String.format("resultado:%s:megasena", dataSorteio);

        long resultado1 = jedisSing.hset(chave, "ganhadores", ganhadores);
        long resultado2 = jedisSing.hset(chave, "numeros", numeros);

        String mensagem = String.format("Resultado 1 = %d, Resultado 2 = %d", resultado1, resultado2);
        System.out.println(mensagem);

        System.out.println("---- BUSCANDO DENTRO DA CHAVE DO TIPO HASH ---------");
        String chaveBusca = "resultado:30-12-2025:megasena";

        String ganhadoresBusca = jedisSing.hget(chaveBusca, "ganhadores");
        String numerosBusca = jedisSing.hget(chaveBusca, "numeros");

        String mensagemBusca = String.format("Ganhadores = %s, Numeros = [%s]", ganhadoresBusca, numerosBusca);
        System.out.println(mensagemBusca);

        System.out.println("----EXERCICIO REMOVENDO CAMPO COM HDEL ------");
        long resultadoHdel = jedisSing.hdel(chave, "ganhadores");
        System.out.println("Resultado hdel : " + resultadoHdel);

        System.out.println("----EXERCICIO VERIFICANDO SE UM CAMPO EXISTE COM HEXIST ------");
        boolean existeCampoGanhadores = jedisSing.hexists(chave, "ganhadores");
        boolean existeCampoNumeros = jedisSing.hexists(chave, "numeros");
        System.out.println(String.format("Existe campo ganhadores : %s, Existe campo numeros : %s", existeCampoGanhadores, existeCampoNumeros));

        System.out.println("----EXERCICIO VERIFICANDO QUANTOS CAMPOS ESTAO ASSOCIADOS A UM HASH COM HLEN ------");
        long qtdCamposAssociadosAoHash = jedisSing.hlen(chave);
        System.out.println(qtdCamposAssociadosAoHash);
    }

    private static void filtragemPorParametros(FiltrarHistoricoMegaSena filtrarHistoricoMegaSena) {
        int mes = 12;
        int ano = 2025;

        System.out.println("------ FILTRANDO TODOS OS RESULTADOS ------");
        Set<String> chaves = filtrarHistoricoMegaSena.filtrarResultados(mes, ano, jedisSing);
        System.out.println(chaves);

        System.out.println("-----USANDO MGET TODOS OS VALORES DE TODAS AS CHAVES --------");
        filtrarHistoricoMegaSena.filtrarTodos(chaves, jedisSing);

        System.out.println("---USANDO STRLEN PARA SABER TAMANHO DAS CHAVES ---------");
        filtrarHistoricoMegaSena.tamanhoChave(chaves, jedisSing);

        System.out.println("---BUSCANDO COM UM RANGE, COMO UM SUBSTRING---------");
        filtrarHistoricoMegaSena.getRange(chaves, jedisSing, 0, 1);
    }

    public void getRange(Set<String> chaves, Jedis jedisSing, int rangeStart, int rangeEnd){
        chaves.forEach(c -> System.out.println(jedisSing.getrange(c, rangeStart, rangeEnd)));
    }

    public void filtrarTodos(Set<String> chaves, Jedis jedisSing){
        chaves.forEach(c -> System.out.println(jedisSing.mget(c)));
    }

    public void tamanhoChave(Set<String> chaves, Jedis jedisSing){
        chaves.forEach(c -> System.out.println(jedisSing.strlen(c)));
    }

    public Set<String> filtrarResultados(int mes, int ano, Jedis jedisSing){
        String chave = "resultado:*-%02d-%04d:megasena"; // keys resultado:[0]?-[1]?-[20]*:megasena
        return jedisSing.keys(String.format(chave, mes, ano));
    };

}
