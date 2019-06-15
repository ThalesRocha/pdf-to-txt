package br.com.thalesrocha.producer.service;

import br.com.thalesrocha.producer.bean.PDF;
import br.com.thalesrocha.producer.exception.ApplicationException;

public interface ProducerService {

	void send(PDF pdf) throws ApplicationException;

}
