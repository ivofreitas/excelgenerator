package com.simbiose.excelgenerator.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "product")
public class Product {

	@Id
	@Column(name = "sku")
	private String sku;
	
	@Column(name = "nome_produto")
	private String name;
	
	@Column(name = "descricao_produto")
	private String description;
	
	@Column(name = "codigo_categoria")
	private Category category;
	
	@Column(name = "url_produto")
	private String url;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "sku_pai")
	private Product skuFather;
	
	@OneToMany(mappedBy="skuFather")
	private Set<Product> skuChildren = new HashSet<>();
	
}
