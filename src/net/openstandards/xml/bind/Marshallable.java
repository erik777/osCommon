package net.openstandards.xml.bind;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Marshallable.java
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

public interface Marshallable {
	public Marshaller marshaller() throws JAXBException;
	public Unmarshaller unmarshaller() throws JAXBException;
	
	public static final String JSON_MEDIA_TYPE_PROPERTY = "eclipselink.media-type";
	
	default public Marshaller marshaller(Class clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller marshaller = jaxbContext.createMarshaller();		
        return marshaller;
	}

	default public Unmarshaller unmarshaller(Class clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller;
	}

	default public String toXml() throws JAXBException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller().marshal(this, baos);
        String xml = new String(baos.toByteArray());
        return xml;
	}

	default public Object fromXmlToObject(String document) throws JAXBException {
		Object obj = null;
		if (document != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(document.getBytes());
			obj = fromXmlToObject(bais);
		}
		return obj;
	}
	
	default public Object fromJsonToObject(String document) throws JAXBException {
		Object obj = null;
		if (document != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(document.getBytes());
			obj = fromJsonToObject(bais);
		}
		return obj;
	}
	
	default public Object fromXmlToObject(InputStream isDoc) throws JAXBException {
		Object obj = unmarshaller().unmarshal(isDoc);
        return obj;
	}

	default public Object fromJsonToObject(InputStream isDoc) throws JAXBException {
		Unmarshaller unmarshaller = unmarshaller();
        unmarshaller.setProperty(JSON_MEDIA_TYPE_PROPERTY, "application/json");
//        System.out.println("Unmarshaller media-type: " + unmarshaller.getProperty(JSON_MEDIA_TYPE_PROPERTY));
		Object obj = unmarshaller.unmarshal(isDoc);
        return obj;
	}

	default public ByteArrayOutputStream toJsonOutputStream() throws JAXBException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Marshaller marshaller = marshaller();
        marshaller.setProperty(JSON_MEDIA_TYPE_PROPERTY, "application/json");
        marshaller.marshal(this, baos);
        return baos;
	}
	
	default public String toJson() throws JAXBException {
        ByteArrayOutputStream baos = toJsonOutputStream();
        String xml = new String(baos.toByteArray());
        return xml;
	}
	
	default public JsonReader toJsonReader() throws JAXBException {
		return Json.createReader(new ByteArrayInputStream(toJson().getBytes()));
	}
	
	default public JsonObject toJsonObject() throws JAXBException {
		return toJsonReader().readObject();
	}

}
