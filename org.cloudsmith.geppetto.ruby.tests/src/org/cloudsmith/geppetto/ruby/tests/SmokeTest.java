/**
 * Copyright (c) 2011 Cloudsmith Inc. and other contributors, as listed below.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Cloudsmith
 * 
 */
package org.cloudsmith.geppetto.ruby.tests;

import java.io.File;

import junit.framework.TestCase;

import org.cloudsmith.geppetto.ruby.jruby.RubyHelper;
import org.cloudsmith.geppetto.ruby.jruby.RubyHelper.Result;
import org.cloudsmith.geppetto.ruby.jruby.RubyParserWarningsCollector.RubyIssue;
import org.eclipse.core.runtime.Path;

public class SmokeTest extends TestCase{
	
	public void testHelloWorld() throws Exception {
		File aRubyFile = TestDataProvider.getTestFile(new Path("testData/ruby/helloWorld.rb"));
		RubyHelper helper = new RubyHelper();
		helper.parse(aRubyFile);
	}
	public void testHelloBrokenWorld() throws Exception {
		File aRubyFile = TestDataProvider.getTestFile(new Path("testData/ruby/helloBrokenWorld.rb"));
		RubyHelper helper = new RubyHelper();
		Result r = helper.parse(aRubyFile);
		assertEquals("Expect one error",1, r.getIssues().size());
		RubyIssue theIssue = r.getIssues().get(0);
		assertTrue("Expect one syntax error", theIssue.isSyntaxError());
		assertEquals("source line starts with 1", 1, theIssue.getLine());
		assertEquals("the file path is reported", aRubyFile.getPath(), theIssue.getFileName());
		assertTrue("the error message is the expected", theIssue.getMessage().startsWith("syntax error, unexpected tLPAREN_ARG"));
	}
	public void testHelloBrokenWorld2() throws Exception {
		File aRubyFile = TestDataProvider.getTestFile(new Path("testData/ruby/helloBrokenWorld2.rb"));
		RubyHelper helper = new RubyHelper();
		Result r = helper.parse(aRubyFile);
		assertEquals("Expect one error",1, r.getIssues().size());
		RubyIssue theIssue = r.getIssues().get(0);
		assertTrue("Expect one syntax error", theIssue.isSyntaxError());
		assertEquals("source line is 2", 2, theIssue.getLine());
		assertEquals("the file path is reported", aRubyFile.getPath(), theIssue.getFileName());
		assertTrue("the error message is the expected", theIssue.getMessage().startsWith("syntax error, unexpected tLPAREN_ARG"));
	}

}
