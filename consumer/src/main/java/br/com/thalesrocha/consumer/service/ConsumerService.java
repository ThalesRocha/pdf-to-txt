package br.com.thalesrocha.consumer.service;

import br.com.thalesrocha.consumer.bean.PDF;

public interface ConsumerService {

	public void onReceiverQueue(PDF pdf);
	
}
