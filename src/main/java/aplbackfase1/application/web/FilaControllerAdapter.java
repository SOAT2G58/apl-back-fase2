package aplbackfase1.application.web;

import aplbackfase1.application.web.requests.PedidoFilaRequest;
import aplbackfase1.domain.ports.in.IFilaUseCasePort;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class FilaControllerAdapter {

    @Autowired
    private IFilaUseCasePort filaUseCasePort;

    @PostMapping("/fila")
    public void inserePedidoNaFila(@RequestBody @NotNull PedidoFilaRequest pedidoFilaRequest) {
        var pedidoFila = filaUseCasePort.inserirPedidoNaFila(pedidoFilaRequest.toPedido());
        System.out.println(pedidoFila);
    }

}
