/*   Copyright 2004 BEA Systems, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */


package org.controlhaus.google; 

import com.bea.control.Control;

public interface Google extends Control
{ 

    org.controlhaus.google.ActualGoogleControl.GoogleSearchResult googleSearchExtended(java.lang.String query, int start, int maxResults, boolean filter, java.lang.String restrict, boolean safeSearch, java.lang.String languageRestrict);

    org.controlhaus.google.ActualGoogleControl.GoogleSearchResult googleSearch(java.lang.String query, int start);
} 
