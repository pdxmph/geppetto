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
package com.puppetlabs.xtext.formatting;

import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.ReplaceRegion;

import com.puppetlabs.xtext.dommodel.IDomNode;
import com.puppetlabs.xtext.dommodel.formatter.IDomModelFormatter;

// TODO: Fix where @Nullable comes from
//import com.google.inject.internal.Nullable;

/**
 * Suggested as API for formatter -
 *
 * @see IDomModelFormatter with extended api (to emit serialization/formatter errors)
 */
public interface IFormatter {
	ReplaceRegion format(/* @NotNull */IDomNode root, /* @Nullable */ITextRegion regionToFormat, IFormattingContext ctx);
}
