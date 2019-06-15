package br.com.thalesrocha.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import br.com.thalesrocha.producer.bean.PDF;
import br.com.thalesrocha.producer.exception.ApplicationException;

@Service
public class ProducerServiceImpl implements ProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void send(PDF pdf) throws ApplicationException {

		if (pdf != null && pdf.getFileName() == null && pdf.getFileContent() == null) {
			throw new ApplicationException("Parâmetros inválidos");
		}
		
		LOGGER.info("PDF file-name: " + pdf.getFileName());
			
		jmsTemplate.convertAndSend("queue.klink", pdf);

	}

}
