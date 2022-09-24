package br.com.projeto.authjwt.publishers;

import br.com.projeto.authjwt.integration.rabbitmq.user.UserEventResponse;
import br.com.projeto.authjwt.integration.rabbitmq.user.enums.ActionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.exchange.userEventExchange}")
    private String exchangeUserEvent;

    public void publishUserEvent(UserEventResponse userEventResponse, ActionType actionType){
        userEventResponse.setActionType(actionType.toString());
        rabbitTemplate.convertAndSend(exchangeUserEvent, "", userEventResponse);
    }
}
