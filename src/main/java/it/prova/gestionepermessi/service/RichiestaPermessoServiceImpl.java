package it.prova.gestionepermessi.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.attachment.AttachmentRepository;
import it.prova.gestionepermessi.repository.attachment.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	RichiestaPermessoRepository permessoRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	
	@Override
	@Transactional
	public void inserisciRichiestaConAllegato(RichiestaPermesso richiestaPermesso, MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
		richiestaPermesso.setAttachment(attachment);
		attachmentRepository.save(attachment);
		permessoRepository.save(richiestaPermesso); 
		
		
	}

	
	

}
