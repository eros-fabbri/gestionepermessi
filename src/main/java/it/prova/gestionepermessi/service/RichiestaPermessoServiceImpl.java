package it.prova.gestionepermessi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.attachment.AttachmentRepository;
import it.prova.gestionepermessi.repository.richiestapermesso.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	RichiestaPermessoRepository permessoRepository;
	@Autowired
	AttachmentRepository attachmentRepository;

	@Override
	@Transactional
	public void inserisciRichiestaConAllegato(RichiestaPermesso richiestaPermesso, MultipartFile file)
			throws IOException {

		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
		richiestaPermesso.setAttachment(attachment);
		attachmentRepository.save(attachment);
		permessoRepository.save(richiestaPermesso);

	}

	@Override
	@Transactional
	public List<RichiestaPermesso> findByDipendeId(Long id) {

		return permessoRepository.findAllByDipendenteId(id);
	}

	@Override
	@Transactional
	public void inserisciRichiesta(RichiestaPermesso richiestaPermesso) {
		permessoRepository.save(richiestaPermesso);
	}

	@Transactional(readOnly = true)
	public Page<RichiestaPermesso> findByExample(RichiestaPermesso example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			root.fetch("dipendente", JoinType.INNER);

			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(cb.upper(root.get("tipoPermesso")), example.getTipoPermesso()));

			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));

			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));
			if (example.getApprovato() != null)
				predicates.add(cb.equal(root.get("approvato"), example.isApprovato()));

			if (StringUtils.isNotEmpty(example.getCodiceCertificato()))
				predicates.add(cb.like(cb.upper(root.get("codiceCertificato")),
						"%" + example.getCodiceCertificato().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getNota()))
				predicates.add(cb.like(cb.upper(root.get("note")), "%" + example.getNota().toUpperCase() + "%"));

			if (example.getDipendente() != null && example.getDipendente().getId() != null) {
				predicates.add(
						cb.equal(root.join("dipendente", JoinType.INNER).get("id"), example.getDipendente().getId()));
			}

			query.distinct(true);
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return permessoRepository.findAll(specificationCriteria, paging);
	}

}
