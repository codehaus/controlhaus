package model;
/*
 * GoogleClient.jcx
 * 
 * Copyright 2004 BEA Systems, Inc.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * Original author: Jonathan Colwell
 */


import org.apache.beehive.controls.api.bean.ControlExtension;
import org.controlhaus.webservice.ServiceControl;
import org.controlhaus.webservice.ServiceControl.Location;
import org.controlhaus.webservice.ServiceControl.WSDL;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
@ControlExtension
@Location(urls = {"http://api.google.com/search/beta2"})
@WSDL(path = "d:/dev/workspace/NEW_Control_Haus_Service_Controler/samples/google/src/model/GoogleSearch.wsdl",
      service = "GoogleSearchService")
public interface GoogleClient extends ServiceControl {

    @OperationName("doGetCachedPage")
    public byte[] getCachedPage(String key, String url) throws Exception;

    @OperationName("doSpellingSuggestion")
    public String suggestSpelling(String key, String phrase) throws Exception;

    @OperationName("doGoogleSearch")
    public GoogleSearch.GoogleSearchResult search(String key,
                                                  String q,
                                                  int start,
                                                  int maxResults,
                                                  boolean filter,
                                                  String restrict,
                                                  boolean safeSearch,
                                                  String lr,
                                                  String ie,
                                                  String oe) throws Exception;

}
