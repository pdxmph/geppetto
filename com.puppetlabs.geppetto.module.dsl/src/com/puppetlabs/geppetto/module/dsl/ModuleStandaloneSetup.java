/*
 * generated by Xtext
 */
package com.puppetlabs.geppetto.module.dsl;


/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
public class ModuleStandaloneSetup extends ModuleStandaloneSetupGenerated {

	public static void doSetup() {
		new ModuleStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
