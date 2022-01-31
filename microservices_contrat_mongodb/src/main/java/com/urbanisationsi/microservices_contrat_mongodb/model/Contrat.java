package com.urbanisationsi.microservices_contrat_mongodb.model;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(of= {"id", "numeroContrat"})
@ToString(of= {"id", "numeroContrat", "dateDebut", "numeroProduit", "numeroAssure"})
@Document(collection= "gestionprevdb")
public class Contrat {

	@Id
	private String id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDebut;

	@NotNull
	@Indexed(unique = true, direction = IndexDirection.DESCENDING)
	private Long numeroContrat;

	@NotNull
	private Long numeroAssure;

	@NotNull
	private Long numeroProduit;

}
