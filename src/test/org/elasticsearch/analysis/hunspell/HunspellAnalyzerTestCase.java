package org.elasticsearch.analysis.hunspell;

/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.InputStream;

import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.hunspell.Dictionary;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.carrotsearch.randomizedtesting.RandomizedContext;

/**
 * Base class for testing hunspell-based analyzers
 */
public abstract class HunspellAnalyzerTestCase extends BaseTokenStreamTestCase {
  private static Dictionary dictionary;
  
  @BeforeClass
  public static void beforeClass() throws Exception {
    Class<?> targetClass = RandomizedContext.current().getTargetClass();
    String parts[] = targetClass.getPackage().getName().split("\\.");
    String language = parts[parts.length-1];
    InputStream affixStream = HunspellAnalyzerTestCase.class.getResourceAsStream("/" + language + "/" + language + ".aff");
    InputStream dictionaryStream = HunspellAnalyzerTestCase.class.getResourceAsStream("/" + language + "/" + language + ".dic");
    try (InputStream aff = affixStream; InputStream dic = dictionaryStream) {
      dictionary = new Dictionary(aff, dic);
    }
  }
  
  @AfterClass
  public static void afterClass() throws Exception {
    dictionary = null;
  }
  
  public Dictionary getDictionary() {
    return dictionary;
  }
}
