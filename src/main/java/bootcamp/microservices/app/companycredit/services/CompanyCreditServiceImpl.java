package bootcamp.microservices.app.companycredit.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootcamp.microservices.app.companycredit.documents.CompanyCredit;
import bootcamp.microservices.app.companycredit.exceptions.customs.CustomNotFoundException;
import bootcamp.microservices.app.companycredit.repository.CompanyCreditRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyCreditServiceImpl implements CompanyCreditService {

	private static final Logger log = LoggerFactory.getLogger(CompanyCreditServiceImpl.class);

	@Autowired
	private CompanyCreditRepository companyCreditRepository;

	@Override
	public Flux<CompanyCredit> findAll() {
		return companyCreditRepository.findAll()
				.switchIfEmpty(Mono.error(new CustomNotFoundException("Clients not exist")));
	}

	@Override
	public Mono<CompanyCredit> findById(String id) {
		return companyCreditRepository.findById(id)
				.switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCredit not found")));
	}

	@Override
	public Mono<CompanyCredit> update(CompanyCredit companyCredit) {
		return companyCreditRepository.findById(companyCredit.getId()).flatMap(c -> {
			companyCredit.setModifyUser(companyCredit.getModifyUser());
			companyCredit.setModifyDate(new Date());
			return companyCreditRepository.save(companyCredit);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCredit not found")));
	}

	@Override
	public Mono<CompanyCredit> save(CompanyCredit companyCredit) {
		return companyCreditRepository.save(companyCredit);
	}

	@Override
	public Mono<Void> deleteNonLogic(CompanyCredit companyCredit) {
		return companyCreditRepository.findById(companyCredit.getId()).flatMap(c -> {
			return companyCreditRepository.delete(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCredit not found")));
	}

	@Override
	public Mono<CompanyCredit> deleteLogic(CompanyCredit companyCredit) {
		return companyCreditRepository.findById(companyCredit.getId()).flatMap(c -> {
			c.setModifyUser(companyCredit.getModifyUser());
			c.setModifyDate(new Date());
			return companyCreditRepository.save(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyCredit not found")));
	}

}
