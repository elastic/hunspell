package org.elasticsearch.analysis.hunspell.fr;

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

@Lang("fr")
public class TestFrenchHunspellAnalyzer extends HunspellAnalyzerTestCase {
  
  /** Test stopword removal */
  public void testStopWord() throws Exception {
    Analyzer a = new FrenchHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary());
    assertAnalyzesTo(a, "votre",  new String[] { });
  }
  
  /** Test that stopwords are not case sensitive */
  public void testStopwordsCasing() throws IOException {
    Analyzer a = new FrenchHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary());
    assertAnalyzesTo(a, "Votre", new String[] { });
  }
  
  /** Test stemmer exceptions */
  public void testStemExclusion() throws IOException{
    CharArraySet set = new CharArraySet(TEST_VERSION_CURRENT, 1, true);
    set.add("ai");
    Analyzer a = new FrenchHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary(), CharArraySet.EMPTY_SET, set);
    assertAnalyzesTo(a, "J'ai soif", new String[] {"ai", "soif"});
  }
  
  /** Test elision */
  public void testElision() throws Exception {
    FrenchHunspellAnalyzer fa = new FrenchHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary());
    assertAnalyzesTo(fa, "voir l'embrouille", new String[] { "voir", "embrouiller" });
  }
  
  /** Test elision is not case sensitive */
  public void testElisionCasing() throws Exception {
    FrenchHunspellAnalyzer fa = new FrenchHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary());
    assertAnalyzesTo(fa, "L'embrouille", new String[] { "embrouiller" });
  }
  
  /** blast some random strings through the analyzer */
  public void testRandomStrings() throws Exception {
    checkRandomData(random(), new FrenchHunspellAnalyzer(TEST_VERSION_CURRENT, getDictionary()), 1000*RANDOM_MULTIPLIER);
  }
}
