#### SITUAÇÃO PROBLEMA: LOG DE CORRIDAS DE KART

Para resolver o determinado problema, tomei 2 estratégias.

1. Criei uma mini biblioteca que tem a responsabilidade de mapear um flat file(arquivo de texto) para um determinado modelo. Esta biblioteca se utilizou do pattern `Annotation Driven` muito utilizado atualmente em frameworks atuais tal como o `Spring Framework`. Vale ressaltar que não me utilizei de nenhum framework de apoio para a criação desta biblioteca, ou seja, todas as resoluções genéricas foram criadas se utilizando do puro `reflection` presente no Java. Para facilitar a análise, a biblioteca está dentro deste projeto como um módulo do gradle.  
2. Separei a lógica em 3 camadas. Sendo elas:

    1. Camada de serviço. Responsável pela orchestração para chegar no resultado final.
    2. Camada de repositório. Responsável pela extração das informações do arquivo. Está se utilizando da mini biblioteca `flat file` presente neste projeto.
    3. Camada de apresentação. Responsável por exibir o resultado proveniente da camada de serviço.

##### Exemplo de mapeamento do arquivo de log:
```
@FlatFile(ignoreFirstLine = true)
public class KartRacingFlat {

    @Column(name = "TIME", index = 0)
    private LocalTime currentTime;

    @Embedded
    private PilotFlat pilotFlat;

    @Column(name = "ROUND", index = 4)
    private Integer round;

    @Column(name = "ROUND_TIME", index = 5)
    private Duration roundDuration;

    @Column(name = "ROUND_AVERAGE_SPEED", index = 6)
    private Double roundAverageSpeed;

    // getters and setters
}
```

##### Exemplo de mapeamento do arquivo de log(isolando o piloto através da funcionalidade de `embedding`):
```
@Embeddable
public class PilotFlat {

    @Column(name = "PILOT_CODE", index = 1)
    private String code;

    @Column(name = "PILOT_NAME", index = 3)
    private String name;

    // getters and setters
}
```
Gostaria muito de ouvir o feedback de vocês da Gympass! Um grande abraço!