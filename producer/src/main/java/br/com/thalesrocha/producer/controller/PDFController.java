package br.com.thalesrocha.producer.controller;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thalesrocha.producer.bean.PDF;
import br.com.thalesrocha.producer.exception.ApplicationException;
import br.com.thalesrocha.producer.service.ProducerService;

@RestController
public class PDFController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PDFController.class);

	@Autowired
	private ProducerService producerService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> upload(@RequestBody PDF pdf) {
		
		LOGGER.info("Requisição realizada");

		try {
			producerService.send(pdf);
			
			LOGGER.info("Arquivo enviado para fila de processamento");

			return ResponseEntity.status(HttpStatus.OK)
					.body(Collections.singletonMap("message", "Sucesso na requisição"));

		} catch (ApplicationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("message", e.getMessage()));
		}

	}

}
