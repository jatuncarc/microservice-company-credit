package bootcamp.microservices.app.companycredit.services;

import bootcamp.microservices.app.companycredit.documents.CompanyCredit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyCreditService {

	public Flux<CompanyCredit> findAll();

	public Mono<CompanyCredit> findById(String id);

	public Mono<CompanyCredit> save(CompanyCredit companyCredit);

	public Mono<CompanyCredit> update(CompanyCredit companyCredit);

	public Mono<Void> deleteNonLogic(CompanyCredit companyCredit);

	public Mono<CompanyCredit> deleteLogic(CompanyCredit companyCredit);

}
