package aplbackfase2.controllers.requestValidations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoNotificacaoRequest {
    //    private Long id;
//    @JsonProperty(value = "live_mode")
//    private boolean urlvalida;
//    @JsonProperty(value = "type")
//    private String tipo;
//    @JsonProperty(value = "date_created")
//    private Date dataCriacao;
//    @JsonProperty(value = "user_id")
//    private Long idUsuario;
//    @JsonProperty(value = "api_version")
//    private String versaoApi;
    @JsonProperty(value = "action")
    private String acao;
    @JsonProperty(value = "data")
    private PagamentoDadosRequest pagamentoDados;
}
