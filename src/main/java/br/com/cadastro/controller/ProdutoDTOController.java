package br.com.cadastro.controller;

import br.com.cadastro.configs.RabbitMQConfig;
import br.com.cadastro.dto.ProdutoDTO;
import br.com.cadastro.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoDTOController {

    @Autowired
    private RabbitMQService mqService;

    @PostMapping
    public ResponseEntity enviarProduto(@RequestBody ProdutoDTO produtoDTO){
        System.out.println(produtoDTO);
        mqService.enviaProduto(RabbitMQConfig.FILA_PRODUTO_INSERCAO, produtoDTO);
        mqService.enviaProduto(RabbitMQConfig.FILA_PRODUTO_ALTERACAO, produtoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
