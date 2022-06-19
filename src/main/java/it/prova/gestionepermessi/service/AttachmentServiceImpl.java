package it.prova.gestionepermessi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.repository.attachment.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	AttachmentRepository attachmentRepository;

	public Attachment salvaFile(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
		return attachmentRepository.save(attachment);
	}

}
