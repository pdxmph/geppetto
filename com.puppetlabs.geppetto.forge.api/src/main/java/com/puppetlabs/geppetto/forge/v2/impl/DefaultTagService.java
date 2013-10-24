/**
 * Copyright (c) 2013 Puppet Labs, Inc. and other contributors, as listed below.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Puppet Labs
 */
package com.puppetlabs.geppetto.forge.v2.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;

import com.puppetlabs.geppetto.forge.model.Constants;
import com.puppetlabs.geppetto.forge.v2.model.Module;
import com.puppetlabs.geppetto.forge.v2.model.Tag;
import com.puppetlabs.geppetto.forge.v2.service.ListPreferences;
import com.puppetlabs.geppetto.forge.v2.service.TagService;

public class DefaultTagService extends AbstractForgeService implements TagService {
	private static String getTagPath(String name) {
		return Constants.COMMAND_GROUP_TAGS + '/' + name;
	}

	@Override
	public Tag create(Tag tag) throws IOException {
		return getClient(true).postJSON(Constants.COMMAND_GROUP_TAGS, tag, Tag.class);

	}

	@Override
	public void delete(String name) throws IOException {
		getClient(true).delete(getTagPath(name));
	}

	@Override
	public Tag get(String name) throws IOException {
		return getClient(false).getV2(getTagPath(name), null, Tag.class);
	}

	@Override
	public List<Tag> getAll(ListPreferences listPreferences) throws IOException {
		List<Tag> tags = null;
		try {
			tags = getClient(false).getV2(Constants.COMMAND_GROUP_TAGS, toQueryMap(listPreferences), Constants.LIST_TAG);
		}
		catch(HttpResponseException e) {
			if(e.getStatusCode() != HttpStatus.SC_NOT_FOUND)
				throw e;
		}
		if(tags == null)
			tags = Collections.emptyList();
		return tags;
	}

	@Override
	public List<Module> getModules(String name, ListPreferences listPreferences) throws IOException {
		List<Module> modules = null;
		try {
			modules = getClient(false).getV2(
				getTagPath(name) + "/modules", toQueryMap(listPreferences), Constants.LIST_MODULE);
		}
		catch(HttpResponseException e) {
			if(e.getStatusCode() != HttpStatus.SC_NOT_FOUND)
				throw e;
		}
		if(modules == null)
			modules = Collections.emptyList();
		return modules;
	}

	@Override
	public Tag update(String name, Tag tag) throws IOException {
		return getClient(true).patch(getTagPath(name), tag, Tag.class);
	}
}
