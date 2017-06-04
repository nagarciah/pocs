package com.nagarciah.pocs.ldap.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool.factory.PoolingContextSource;
import org.springframework.ldap.pool.validation.DefaultDirContextValidator;
import org.springframework.stereotype.Service;

import com.nagarciah.pocs.ldap.dto.LdapSourceConfig;

// FIXME Revisar bien esta clase porque podria tener problemas de concurrencia en los mapas
@Service
public class LdapTemplateManager {

	AppConfigurationManager appConfigManager;

	private Map<String, LdapTemplate> ldapTemplates;
	private boolean initialized;

	@Autowired
	public LdapTemplateManager(AppConfigurationManager appConfigManager) {
		super();
		// TODO Hacer estos mapas actualizables, expirables, sincronizados. Un Ldap puede estar temporalmente no disponible, eso nos afecta? requerir[ia estos controles por eso?
		this.ldapTemplates = new HashMap<>();
		this.appConfigManager = appConfigManager;
	}

	
	@PostConstruct
	public void initialize(){ // TODO este metodo requiere ser sincronizado si es @service?
		List<LdapSourceConfig> configs = appConfigManager.loadLdapSourcesConfig();
		
		//TODO Esto podria optimizarse ppara eliminar solo lo que ya no este
		//TODO Se deben cerrar las conexiones al LDAP o reinciar el pool??? No deberian quedar conexiones abiertas
		this.ldapTemplates.clear(); 
		
		configs.forEach(c -> { 
			synchronized (ldapTemplates) { // FIXME Se requiere si el mapa es sincronizado?
				ldapTemplates.put(c.getKey(), createTemplate(c));
			}
		});
		
		initialized = true;
	}

	public LdapTemplate getTemplateByKey(String key) {
		checkForInitialization();
		
		LdapTemplate ldapTemplate = this.ldapTemplates.get(key);
		if(ldapTemplate == null){
			throw new LdapTemplateNotFoundException(String.format("No se encontró una configuracion LDAP con key=\"%s\". Verifique la configuración de conexiones LDAP", key)); 
		}
		return ldapTemplate;
	}

	public Map<String, LdapTemplate> getAllTemplates() {
		checkForInitialization();
		return ldapTemplates;
	}


	private LdapTemplate createTemplate(LdapSourceConfig config) {
		// FIXME Crear correctamente la plantilla y asignar el mapper usando un mapper personalizado  con mappings configurables externalizados
		LdapContextSource ctxSource = new LdapContextSource(); 
		ctxSource.setUrls(config.getUrls().split(","));
		ctxSource.setBase(config.getBase());
		ctxSource.setUserDn(config.getUsername());
		ctxSource.setPassword(config.getPassword());

		// Se debe invocar porque la clase esta siendo usada fuera del contexto de Spring
		ctxSource.afterPropertiesSet(); 
		
		// FIXME Quiza sea mejor usar PoolingContextSource por rendimiento. Probar con carga ambos casos y buscar parametros optimos
		// DOC http://docs.spring.io/spring-ldap/docs/1.3.x/reference/html/pooling.html
		ctxSource.setPooled(false);
		PoolingContextSource ldapPool = new PoolingContextSource();
		ldapPool.setContextSource(ctxSource);
		ldapPool.setMaxTotal(ldapPool.getMaxActive());
		ldapPool.setMaxWait(5000);
		ldapPool.setTestOnBorrow(true);
		ldapPool.setDirContextValidator(new DefaultDirContextValidator());

		LdapTemplate ldapTemplate = new LdapTemplate(ldapPool);
		//ldapTemplate.setObjectDirectoryMapper(new ObjectDirectoryMapper);
		
		return ldapTemplate;
	}

	private void checkForInitialization() {
		if(!initialized){
			throw new LdapTemplateManagerException(this.getClass().getName() + " no ha sido inicializado. Si esta clase no se usa como un bean de Spring, despues de crear una instancia de esta clase debe invocarle manualmente el metodo initialize() para resolver sus dependencias y valores iniciales");
		}
	}
}
