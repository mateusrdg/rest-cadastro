package br.com.cadastro.configs;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class RabbitMQConfig {

    private static final String NOME_EXCHANGE = "produto";
    public static final String FILA_PRODUTO_INSERCAO = "produtos-insercao";
    public static final String FILA_PRODUTO_ALTERACAO = "produtos-alteracao";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConfig(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    public Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaProdutoInsercao = this.fila(FILA_PRODUTO_INSERCAO);
        Queue filaProdutoAlteracao = this.fila(FILA_PRODUTO_ALTERACAO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacao1 = this.relacionamento(filaProdutoInsercao, troca);
        Binding ligacao2 = this.relacionamento(filaProdutoAlteracao, troca);

        this.amqpAdmin.declareQueue(filaProdutoInsercao);
        this.amqpAdmin.declareQueue(filaProdutoAlteracao);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacao1);
        this.amqpAdmin.declareBinding(ligacao2);
    }
}
