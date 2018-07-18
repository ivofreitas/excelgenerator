package com.simbiose.excelgenerator.model;

public enum Category {
	
	MASCULINO(1.1), FEMININO(1.2), INFANTIL(1.3), LONGA(1.4), MOLETOM(1.5);
	
	private double value;
	
	Category(Double value) {
		this.value = value;
	}
	
	public static Category of(double value) {
		for(Category category : Category.values()) {
			if (category.value == value) {
				return category;
			}
		}
		throw new IllegalArgumentException("Value: " + value + " not accepted");
	}
}
