package net.openstandards.xml.bind;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

/**
 * MOXyMarshallable.java
 * 
 * Copyright @2016 OpenStandards.net
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 **/

/**
 * This uses MOXy in eclipselink to marshal json. It avoids the need to create 
 * a jaxb.properties file by using {@link JAXBContextFactory} from eclipselink. 
 * <p>
 * Classes implementing this should declare the XmlRootElement annotation
 * @author erik
 *
 */
public interface MOXyMarshallable extends Marshallable {
	@Override
	default public Marshaller marshaller(Class clazz) throws JAXBException {
      Map<String, Object> properties = new HashMap<String, Object>(2);
      // This programmatically uses Eclipselink w/o requiring jaxb.properties.
      // jaxb.properties was creating a classpath issue with resteasy. 
      JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{clazz}, properties);
      Marshaller marshaller = jaxbContext.createMarshaller();		
      return marshaller;
    }

}
