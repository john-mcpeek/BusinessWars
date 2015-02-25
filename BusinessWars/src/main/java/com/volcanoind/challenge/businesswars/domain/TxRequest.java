package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxRequest {

	@NotNull
	@JsonProperty( "all-or-none" )
	private Boolean allOrNone;

	@NotNull
	@DecimalMin( "0" )
	private BigDecimal total;
	
	@NotEmpty
	private List<LineItem> lineItems = new ArrayList<LineItem>();
}
