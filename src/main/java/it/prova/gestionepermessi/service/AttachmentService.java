package it.prova.gestionepermessi.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.Attachment;

public interface AttachmentService {
	
	public Attachment salvaFile(MultipartFile file) throws IOException;
}
