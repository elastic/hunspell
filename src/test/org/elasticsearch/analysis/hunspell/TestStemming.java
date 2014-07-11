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

import java.io.File;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.hunspell.Dictionary;
import org.apache.lucene.analysis.hunspell.HunspellStemFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.IOUtils;

import com.carrotsearch.randomizedtesting.annotations.Name;
import com.carrotsearch.randomizedtesting.annotations.ParametersFactory;

/**
 * Loads up test data for each language and executes them.
 * <p>
 * NOTE: normally not a fan of parameterized tests. But in this case,
 * the idea is to just add text files for a language and not write java code.
 */
public class TestStemming extends BaseTokenStreamTestCase {
  final String language;
  InputStream dictionaryStream = null;
  InputStream affixStream = null;
  
  /**
   * Creates the parameters (list of languages) by looking for text files.
   * Test will run for each language found.
   */
  @ParametersFactory
  public static Iterable<Object[]> parameters() throws Exception {
    List<Object[]> params = new ArrayList<>();
    URL url = TestStemming.class.getResource("/stemming-data");
    File directory = new File(url.toURI());
    for (File file : directory.listFiles()) {
      String name = file.getName();
      if (name.endsWith(".txt")) {
        params.add(new Object[] { name.substring(0, name.length()-4) });
      }
    }
    return params;
  }
  
  /** 
   * Instantiates this test case for the specified language 
   */
  public TestStemming(@Name("language") String language) {
    this.language = language;
  }
  
  public void test() throws Exception {
    LineNumberReader reader = new LineNumberReader(
                                    IOUtils.getDecodingReader(
                                        getClass().getResourceAsStream("/stemming-data/" + language + ".txt"), 
                                        StandardCharsets.UTF_8));
    Analyzer analyzer = null;
    String aff = null;
    String dic = null;
    String line = null;
    while ((line = reader.readLine()) != null) {
      int comment = line.indexOf('#');
      if (comment >= 0) {
        line = line.substring(0, comment);
      }
      line = line.trim();
      if (line.isEmpty()) {
        continue;
      }
      String elements[] = line.split("\\s+");
      if (elements.length > 2) {
        throw new RuntimeException("Illegal number of elements in line: " + reader.getLineNumber());
      }
      if (elements.length == 1) {
        String kv[] = elements[0].split("=");
        if (kv.length != 2) {
          throw new RuntimeException("Malformed line: " + reader.getLineNumber());
        }
        switch(kv[0]) {
          case "aff":
            if (aff != null) {
              throw new RuntimeException("Duplicate .aff declaration: " + reader.getLineNumber());
            }
            aff = kv[1];
            break;
          case "dic":
            if (dic != null) {
              throw new RuntimeException("Duplicate .dic declaration: " + reader.getLineNumber());
            }
            dic = kv[1];
        }
      } else {
        if (aff == null || dic == null) {
          throw new RuntimeException("Must specify .aff and .dic before any stem tests" + reader.getLineNumber());
        }
        if (analyzer == null) {
          dictionaryStream = getClass().getResourceAsStream("/" + language + "/" + dic);
          affixStream = getClass().getResourceAsStream("/" + language + "/" + aff);
          final Dictionary dictionary = new Dictionary(affixStream, dictionaryStream);
          analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String field, Reader reader) {
              MockTokenizer tokenizer = new MockTokenizer(reader, MockTokenizer.KEYWORD, false);
              HunspellStemFilter filter = new HunspellStemFilter(tokenizer, dictionary, false);
              return new TokenStreamComponents(tokenizer, filter);
            }
          };
        }
        String input = elements[0];
        String outputs[] = elements[1].split(",");
        compareStems(analyzer, input, outputs, reader.getLineNumber());
      }
    }
    analyzer.close();
    reader.close();
  }
  
  /** we use this for comparisons for easier debugging and also to ignore order of tokens */
  private void compareStems(Analyzer analyzer, String input, String outputs[], int lineNumber) throws Exception {
    Arrays.sort(outputs);
    List<String> expected = Arrays.asList(outputs);
    List<String> actual = new ArrayList<>();
    try (TokenStream ts = analyzer.tokenStream("bogus", input)) {
      CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);
      ts.reset();
      while (ts.incrementToken()) {
        actual.add(termAtt.toString());
      }
      ts.end();
    }
    Collections.sort(actual);
    assertEquals("'" + input + "' (line " + lineNumber + "), ", expected, actual);
  }
  
  @Override
  public void tearDown() throws Exception {
    IOUtils.closeWhileHandlingException(dictionaryStream, affixStream);
    super.tearDown();
  }
}
