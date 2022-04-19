package bootcamp.microservices.app.companycredit.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.microservices.app.companycredit.documents.CompanyCredit;
import bootcamp.microservices.app.companycredit.services.CompanyCreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CompanyCreditController {

	@Autowired
	private CompanyCreditService companyCreditService;

	@PostMapping
	public Mono<CompanyCredit> createClient(@Valid @RequestBody CompanyCredit companyCredit) {
		return companyCreditService.save(companyCredit);
	}

	@GetMapping("/all")
	public Flux<CompanyCredit> searchAll() {
		return companyCreditService.findAll();
	}

	@GetMapping("/id/{id}")
	public Mono<CompanyCredit> searchById(@PathVariable String id) {
		return companyCreditService.findById(id);
	}

	@PutMapping
	public Mono<CompanyCredit> updateClientCredit(@RequestBody CompanyCredit companyCredit) {
		return companyCreditService.update(companyCredit);
	}

	@DeleteMapping
	public Mono<CompanyCredit> deleteClientCredit(@Valid @RequestBody CompanyCredit companyCredit) {
		return companyCreditService.deleteLogic(companyCredit);
	}

}
