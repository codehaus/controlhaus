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



import com.amazon.xml.types.DetailsDocument.Details;
import com.amazon.xml.types.ProductInfoDocument.ProductInfo;
import com.bea.xml.XmlOptions;

public class ProductViewBean {
private final ProductInfo productInfo;
        private final DetailReader detailReader;
        private final int numResults;
        
        public ProductViewBean(ProductInfo productInfo) {
            this.productInfo = productInfo;
            if ( isError() ) {
                detailReader = new NullDetailReader();
                numResults = 0;
            } else {
                numResults = productInfo.getDetailsArray().length;
                detailReader = new DefaultDetailReader(productInfo.getDetailsArray(0));
                System.out.println(productInfo.getDetailsArray(0));
            }
        }
        
        public boolean isError() {
            return productInfo.getErrorMsg() != null;
        }
        
        public int getNumResults() {
            return numResults;
        }
        
        public String getErrorMessage() {            
            return productInfo.getErrorMsg().xmlText();            
        }
        
        public String getTitle() {
            return detailReader.getTitle(); 
        }
        
        public String getAuthors() {
            return detailReader.getAuthors();
        }
        
        public String getBigImageUrl() {
            return detailReader.getBigImageUrl();
        }
        public String getReleaseDate() {
            return detailReader.getReleaseDate();
        }
        public String getManufacturer() {
            return detailReader.getManufacturer();
        }
        public String getListPrice() {
            return detailReader.getListPrice();
        }
        public String getAmazonPrice() {
            return detailReader.getAmazonPrice();
        }
        
        
    }
    
    interface DetailReader {
        public String getTitle();
        public String getAuthors();
        public String getBigImageUrl();
        public String getReleaseDate();
        public String getManufacturer();
        public String getListPrice();
        public String getAmazonPrice();
    }
    
    final class NullDetailReader implements DetailReader {
        private static final String default_response = "";
        
        public String getTitle() {
            return default_response;
        } 
        
        public String getAuthors() {
            return default_response;
        }
        
        public String getBigImageUrl() {
            return default_response;
        }       

        public String getAmazonPrice()
        {
            return default_response;
        }

        public String getListPrice()
        {
            return default_response;
        }

        public String getManufacturer()
        {
            return default_response;
        }

        public String getReleaseDate()
        {
            return default_response;
        }
    }
    
    final class DefaultDetailReader implements DetailReader {
        private final Details details;
        
        public DefaultDetailReader(Details details) {
            this.details = details;
        }
        
        public String getTitle() {
            return details.getProductName();
        }
        
        public String getAuthors() {
            StringBuffer result = new StringBuffer();
            String []authors = details.getAuthors().getAuthorArray();
            for(int i=0;i<authors.length;i++) {
                if ( result.length() > 0 ) {
                    result.append(", ");
                }
                result.append(authors[i]);
            }
            return result.toString();
        }
        
        public String getBigImageUrl(){
            return details.getImageUrlLarge();
        }

        public String getAmazonPrice()
        {
            return details.getOurPrice();
        }

        public String getListPrice()
        {
            return details.getListPrice();
        }

        public String getManufacturer()
        {
            return details.getManufacturer();
        }

        public String getReleaseDate()
        {
            return details.getReleaseDate();
        }
    } 
