package br.com.thalesrocha.consumer.service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Base64;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import br.com.thalesrocha.consumer.bean.PDF;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	@JmsListener(destination = "queue.klink")
	public void onReceiverQueue(final PDF pdf) {

		LOGGER.info("Mensagem recebida da fila de processamento");

		InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(pdf.getFileContent().getBytes()));
		PrintStream out = null;

		try {
			final PDDocument doc = PDDocument.load(inputStream);
			final String text = new PDFTextStripper().getText(doc);
			final String outputFileName = pdf.getFileName() + ".txt";

			out = new PrintStream(new FileOutputStream(outputFileName));
			out.print(text);
			out.flush();

			LOGGER.info("Arquivo escrito: " + outputFileName);

		} catch (InvalidPasswordException e) {
			LOGGER.info(ExceptionUtils.getStackTrace(e));
		} catch (IOException e) {
			LOGGER.info(ExceptionUtils.getStackTrace(e));
		} finally {

			if (out != null) {
				out.close();
			}

		}

	}

}
