package br.com.maxdev.restAPI;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	@Bean
	public Docket apiConfigDocs() 
	{
	 	 return new 
				Docket(DocumentationType.SWAGGER_12)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.maxdev.restAPI.resources"))
				.paths(PathSelectors.any())
				.build();
	 	 
	 	 //	.paths(PathSelectors.ant("/api/*"))
	}
	
	//META iNFORMATIONS - INFORMAÇÃO DA API DE FORMA GLOBAL
	private ApiInfo infoDocs() 
	{
		return new ApiInfo("Titulo Rest API","description - Cadastro de Produto","version 1.0","Termos de Conduta",new Contact("Diego de Andrade Ferreira", "https://www.maxdev.com.br" ,"diego.maxdev@gmail.com"),
				"Apache Licenças","Url",new ArrayList<VendorExtension>());
	}
}
