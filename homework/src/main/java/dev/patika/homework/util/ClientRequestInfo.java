package dev.patika.homework.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@Component
@SessionScope
public class ClientRequestInfo {

    private String clientIpAdress;
    private String clientURL;
    private String sessionActivityId;
}
