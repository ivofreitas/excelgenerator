package com.simbiose.excelgenerator.model;

import lombok.Getter;

public enum Category {
	
	MASCULINO(1.1), FEMININO(1.2), INFANTIL(1.3), LONGO(1.4);
	
	private @Getter double value;
	
	Category(Double value) {
		this.value = value;
	}
}
