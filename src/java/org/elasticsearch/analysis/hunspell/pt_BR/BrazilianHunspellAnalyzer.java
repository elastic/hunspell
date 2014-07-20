package org.elasticsearch.analysis.hunspell.pt_BR;

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
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.hunspell.Dictionary;
import org.apache.lucene.analysis.hunspell.HunspellStemFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.pt.PortugueseAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.analysis.util.WordlistLoader;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.Version;

/** 
 * Hunspell-based analyzer for Brazilian Portuguese
 */
public final class BrazilianHunspellAnalyzer extends StopwordAnalyzerBase {
  private final Dictionary   dictionary;
  private final CharArraySet stemExclusionTable;
  
  /** 
   * Creates a new analyzer with the specified matchVersion and hunspell dictionary,
   * but with default stopwords and no stem exclusions.
   * 
   * @param matchVersion Lucene version to match
   * @param dictionary Hunspell dictionary for this language
   */
  public BrazilianHunspellAnalyzer(Version matchVersion, Dictionary dictionary) {
    this(matchVersion, dictionary, getDefaultStopSet(), CharArraySet.EMPTY_SET);
  }

  /** 
   * Creates a new analyzer with the specified matchVersion and hunspell dictionary,
   * specified stopwords and stem exclusions.
   * 
   * @param matchVersion Lucene version to match
   * @param dictionary Hunspell dictionary for this language
   * @param stopwords a stopword set
   * @param stemExclusionTable a stemming exclusion set
   */
  public BrazilianHunspellAnalyzer(Version matchVersion, Dictionary dictionary, CharArraySet stopwords, CharArraySet stemExclusionTable) {
    super(matchVersion, stopwords);
    this.dictionary = dictionary;
    this.stemExclusionTable = stemExclusionTable;
  }
  
  private static class DefaultSetHolder {
    private static final CharArraySet DEFAULT_SET;
  
    static {
      try {
        DEFAULT_SET = WordlistLoader.getSnowballWordSet(
                        IOUtils.getDecodingReader(SnowballFilter.class, PortugueseAnalyzer.DEFAULT_STOPWORD_FILE, StandardCharsets.UTF_8), 
                        new CharArraySet(Version.LUCENE_4_10, 16, true));
      } catch (IOException ex) {
        // default set should always be present as it is part of the
        // distribution (JAR)
        throw new RuntimeException("Unable to load default stopword set");
      }
    }
  }
  
  /**
   * Returns a set of default (case-insensitive) stopwords
   */
  public static final CharArraySet getDefaultStopSet() {
    return DefaultSetHolder.DEFAULT_SET;
  }

  @Override
  protected TokenStreamComponents createComponents(String field, Reader reader) {
    final Tokenizer source = new StandardTokenizer(matchVersion, reader);
    TokenStream result = new StopFilter(matchVersion, source, stopwords);
    if (!this.stemExclusionTable.isEmpty()) {
      result = new SetKeywordMarkerFilter(result, stemExclusionTable);
    }
    result = new HunspellStemFilter(result, dictionary);
    result = new LowerCaseFilter(matchVersion, result);
    return new TokenStreamComponents(source, result);
  }
}
