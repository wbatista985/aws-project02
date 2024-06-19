package br.com.siecola.aws_project02.model;

import br.com.siecola.aws_project02.enums.EventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Envelope {

    private EventType eventType;
    private String data;
}
