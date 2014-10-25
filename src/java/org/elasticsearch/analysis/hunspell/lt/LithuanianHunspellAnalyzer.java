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
import java.nio.charset.StandardCharsets;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.hunspell.Dictionary;
import org.apache.lucene.analysis.hunspell.HunspellStemFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.analysis.util.WordlistLoader;
import org.apache.lucene.util.IOUtils;

/** 
 * Hunspell-based analyzer for Lithuanian
 */
public final class LithuanianHunspellAnalyzer extends StopwordAnalyzerBase {
  private final Dictionary   dictionary;
  private final CharArraySet stemExclusionTable;
  
  /** 
   * Creates a new analyzer with the specified hunspell dictionary,
   * but with default stopwords and no stem exclusions.
   * 
   * @param dictionary Hunspell dictionary for this language
   */
  public LithuanianHunspellAnalyzer(Dictionary dictionary) {
    this(dictionary, getDefaultStopSet(), CharArraySet.EMPTY_SET);
  }

  /** 
   * Creates a new analyzer with the specified hunspell dictionary,
   * specified stopwords and stem exclusions.
   * 
   * @param dictionary Hunspell dictionary for this language
   * @param stopwords a stopword set
   * @param stemExclusionTable a stemming exclusion set
   */
  public LithuanianHunspellAnalyzer(Dictionary dictionary, CharArraySet stopwords, CharArraySet stemExclusionTable) {
    super(stopwords);
    this.dictionary = dictionary;
    this.stemExclusionTable = stemExclusionTable;
  }
  
  private static class DefaultSetHolder {
    private static final CharArraySet DEFAULT_SET;
  
    static {
      try {
        DEFAULT_SET = WordlistLoader.getWordSet(
                        IOUtils.getDecodingReader(LithuanianHunspellAnalyzer.class, LithuanianHunspellAnalyzer.DEFAULT_STOPWORD_FILE, StandardCharsets.UTF_8), 
                        "#", 
                        new CharArraySet(16, true));
      } catch (IOException ex) {
        // default set should always be present as it is part of the
        // distribution (JAR)
        throw new RuntimeException("Unable to load default stopword set");
      }
    }
  }
  
  /**
   * File containing default stopwords.
   */
  public final static String DEFAULT_STOPWORD_FILE = "stopwords.txt";
  
  /**
   * Returns a set of default (case-insensitive) stopwords
   */
  public static final CharArraySet getDefaultStopSet() {
    return DefaultSetHolder.DEFAULT_SET;
  }

  @Override
  protected TokenStreamComponents createComponents(String field) {
    final Tokenizer source = new StandardTokenizer();
    TokenStream result = new StopFilter(source, stopwords);
    if (!this.stemExclusionTable.isEmpty()) {
      result = new SetKeywordMarkerFilter(result, stemExclusionTable);
    }
    result = new HunspellStemFilter(result, dictionary);
    result = new LowerCaseFilter(result);
    return new TokenStreamComponents(source, result);
  }
}
