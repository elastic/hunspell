package org.elasticsearch.analysis.hunspell.lt;

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

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.elasticsearch.analysis.hunspell.HunspellAnalyzerTestCase;
import org.elasticsearch.analysis.hunspell.HunspellAnalyzerTestCase.Lang;

@Lang("lv")
public class TestLatvianHunspellAnalyzer extends HunspellAnalyzerTestCase {
  
  /** Test stopword removal */
  public void testStopWord() throws Exception {
    Analyzer a = new LatvianHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary());
    assertAnalyzesTo(a, "un", 
        new String[] { });
  }
  
  /** Test stemmer exceptions */
  public void testStemExclusion() throws IOException{
    CharArraySet set = new CharArraySet(TEST_VERSION_CURRENT, 1, true);
    set.add("kurmjiem");
    Analyzer a = new LatvianHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary(), CharArraySet.EMPTY_SET, set);
    assertAnalyzesTo(a, "kurmjiem", new String[] {"kurmjiem"});
  }
  
  /** blast some random strings through the analyzer */
  public void testRandomStrings() throws Exception {
    checkRandomData(random(), new LatvianHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary()), 1000*RANDOM_MULTIPLIER);
  }
}
