package com.nagarciah.pocs.ldap.conf;

import java.text.MessageFormat;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.ldap.LdapName;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerBeanMapperConfig {

	@Value("${bean.mapping.files:bean-mappings.xml}")
	private List<String> mappingFiles;
	
	@Bean
	public Mapper beanMapper() {
		Mapper mapper = new DozerBeanMapper(mappingFiles);

		return mapper;
	}

	public static class LdapNameConverter extends DozerConverter<String, Name> {
		
		public LdapNameConverter() {
			super(String.class, Name.class);
		}

		@Override
		public Name convertTo(String source, Name destination) {
			try{
				//FIXME dejar parametrizable y con control de error
				if(!source.contains("uid=")){
					source = MessageFormat.format("uid={0}", source);
				}
				return new LdapName(source); 
			} catch (InvalidNameException e) {
				throw new MappingException("Error en mapping: valor de origen=" + source + ", valor destino=" + destination, e);
			}
		}

		@Override
		public String convertFrom(Name source, String destination) {
			//FIXME dejar parametrizable y con control de error
			return source.toString().split("=")[1];
		}
	
	}
}
