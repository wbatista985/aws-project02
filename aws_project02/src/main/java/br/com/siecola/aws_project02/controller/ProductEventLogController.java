package br.com.siecola.aws_project02.controller;

import br.com.siecola.aws_project02.model.ProductEventLogDto;
import br.com.siecola.aws_project02.repository.ProductEventLogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class ProductEventLogController {

    private ProductEventLogRepository productEventLogRepository;

    public ProductEventLogController(ProductEventLogRepository productEventLogRepository) {
        this.productEventLogRepository = productEventLogRepository;
    }

    @GetMapping("events")
    public List<ProductEventLogDto> getAllPEvents() {
        return StreamSupport
                .stream(productEventLogRepository.findAll().spliterator(), false)
                //retorna a lista e map mapeia criando novo ProductEventLogDto
                .map(ProductEventLogDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("events/{code}")
    public List<ProductEventLogDto> findByCode(@PathVariable String code) {
        return productEventLogRepository.findAllByPk(code)
                .stream()
                //retorna a lista e map mapeia criando novo ProductEventLogDto
                .map(ProductEventLogDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("events/{code}/{event}")
    public List<ProductEventLogDto> findByCodeEvent(@PathVariable String code,
                                              @PathVariable String event) {
        return productEventLogRepository.findAllByPkAndSkStartsWith(code, event)
                .stream()
                //retorna a lista e map mapeia criando novo ProductEventLogDto
                .map(ProductEventLogDto::new)
                .collect(Collectors.toList());
    }
}

