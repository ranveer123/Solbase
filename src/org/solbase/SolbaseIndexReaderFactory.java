/**
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.solbase;

import java.io.IOException;


import org.apache.lucene.store.Directory;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.SolrException.ErrorCode;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.core.IndexReaderFactory;
import org.solbase.lucenehbase.IndexReader;

public class SolbaseIndexReaderFactory extends IndexReaderFactory {  
        
    private String indexName;
    
    public void init(@SuppressWarnings("rawtypes") NamedList args){
        super.init(args);
        
        indexName = (String)args.get("indexName");
            
        if(indexName == null || indexName.length() == 0)
            throw new SolrException(ErrorCode.NOT_FOUND, "<str name=\"indexName\">example</str>  tag required");
                
    }
    
    @Override
    public IndexReader newReader(Directory indexDir, boolean readOnly) throws IOException {               
        return new org.solbase.lucenehbase.IndexReader(indexName);        
    }
    
    public void setIndexName(String indexName) {
    	this.indexName = indexName;
    }

}
