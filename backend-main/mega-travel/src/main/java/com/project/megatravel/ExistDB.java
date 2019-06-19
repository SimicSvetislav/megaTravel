package com.project.megatravel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

public class ExistDB {
	
	private final static Logger logger = Logger.getLogger(ExistDB.class.getName());
	
	private final static String EXIST_PROPERTIES_FILE = "exist.properties";
	//private static final String TARGET_NAMESPACE = "http://www.model.megatravel.project.com/bookstore";
	//private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/examples/xmldb/bookstore";
	
	private static String user;
	private static String password;
	private static String uri;
	private static String driver;
	private static String colRoot;
	private static Database database;

	private ExistDB() { }
	
	public static <T> T save(T entity, Long id, String collectionName, String schemaLocation, String jaxbContext) {
		
		try {
			Collection col = ExistDB.getOrCreateCollection(colRoot + collectionName);
		
		
			JAXBContext context = JAXBContext.newInstance(jaxbContext);
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
			
			OutputStream os = new ByteArrayOutputStream();
			marshaller.marshal(entity, os);
			
			//System.out.println("Data:\n" + os.toString());
			
			XMLResource resource = (XMLResource) col.createResource(id + ".xml", XMLResource.RESOURCE_TYPE);
			
			resource.setContent(os);
			col.storeResource(resource);
	        logger.info("Storing the document: " + resource.getId() + " in collection " + col.getName());
		} catch (XMLDBException | JAXBException e) {
			logger.warning("Exception occured while savind resource");
			e.printStackTrace();
		}
	        
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getOneById(Long id, String collectionName, String jaxbContext) {
		
		XMLResource res = null;
		T entity = null;
		
		try {
			
			Collection collection = ExistDB.getOrCreateCollection(colRoot + collectionName);
			
			JAXBContext context = JAXBContext.newInstance(jaxbContext);
			
			Unmarshaller um = context.createUnmarshaller();
			
			res = (XMLResource) collection.getResource(id + ".xml");
			
			if (res == null) {
				logger.warning("Entity with ID " + id + " doesn't exist in collection " + collection.getName());
				return null;
			}
			
			//um.setValidating(true);
			entity = (T) um.unmarshal(res.getContentAsDOM());
			
			logger.info("Fetched the document '" + res.getId() + "' from collection " + collection.getName() + " as JAXB instance.");
			
		} catch (XMLDBException | JAXBException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public static <T> java.util.Collection<T> getAll(String collectionName, String jaxbContext) {
		
		java.util.Collection<T> entities = new ArrayList<T>();
		XMLResource res = null;
		
		try {
			
			Collection collection = ExistDB.getOrCreateCollection(colRoot + collectionName);
		
			JAXBContext context = JAXBContext.newInstance(jaxbContext);
			
			Unmarshaller um = context.createUnmarshaller();
			
			String[] resources = collection.listResources();
			
			for (String resourceName : resources) {
				
				res = (XMLResource) collection.getResource(resourceName);
				
				@SuppressWarnings("unchecked")
				T entity = (T) um.unmarshal(res.getContentAsDOM());
				
				entities.add(entity);
				
			}
		
		} catch (XMLDBException | JAXBException e) {
			e.printStackTrace();
		}
		
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deleteById(Long id, String collectionName, String jaxbContext) {
		
		
		XMLResource res = null;
		T entity = null;
		
		try {
			
			Collection collection = ExistDB.getOrCreateCollection(colRoot + collectionName);
			
			JAXBContext context = JAXBContext.newInstance(jaxbContext);
			
			Unmarshaller um = context.createUnmarshaller();
			
			res = (XMLResource) collection.getResource(id + ".xml");
			
			if (res == null) {
				logger.warning("Entity with ID " + id + " doesn't exist in collection " + collection.getName());
				return null;
			}
			
			entity = (T) um.unmarshal(res.getContentAsDOM());
			
			collection.removeResource(res);
			
			logger.info("Deleted document '" + res.getId() + "' from collection " + collection.getName());
			
		} catch (XMLDBException | JAXBException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	/**
	 * Odredjuje od kog broja ce se dodeljivati id-evi u nekoj kolekciji
	 * @param collectionName
	 * @return
	 */
	public static Long determineId(String collectionName) {
		
		Collection collection = null;
		Long max = null;
		try {
			collection = ExistDB.getOrCreateCollection(colRoot + collectionName);
			
			String[] resources = collection.listResources();
	        
	        List<Long> ids = Arrays.asList(resources).stream().map(name -> {
	        	name = name.substring(0, name.indexOf('.'));
	        	Long id = Long.parseLong(name);
	        	return id;
	        } ).collect(Collectors.toList());
	        
	        
	        max = ids.size()!=0 ? Collections.max(ids) : 0L;
        
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
        
        return max;
		
	}
	
	public static void initDatabase() throws Exception {
		
		Properties props = new Properties();
		
		// Read database properties
		try {
			readProperties(props);
		} catch (IOException e) {
			System.err.println("Error reading exist database properties file");
			e.printStackTrace();
		}
		
		// Set database properties
		setUser(props.getProperty("exist.connection.user", "admin").trim());
		password = props.getProperty("exist.connection.password", "").trim();
		
		uri = props.getProperty("exist.connection.uri").trim();
		String host = props.getProperty("exist.connection.host").trim();
		int port = Integer.parseInt(props.getProperty("exist.connection.port", "8080").trim());
		
		uri = String.format(uri, host, port);
		
		driver = props.getProperty("exist.connection.driver").trim();

		colRoot = props.getProperty("exist.collection.root").trim();
		
		String action = props.getProperty("exist.action").trim();
		
		// Load driver
		logger.info("Loading driver class: " + driver);
		Class<?> cl = Class.forName(driver);
        
        // Encapsulation of the database driver functionality
    	database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
        
        // Entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);
		
        if (action.equals("none")) {
			
        	ExistDB.getOrCreateCollection(colRoot + "/jedinice");
        	ExistDB.getOrCreateCollection(colRoot + "/dodaci");
        	ExistDB.getOrCreateCollection(colRoot + "/korisnici");
        	ExistDB.getOrCreateCollection(colRoot + "/objekti");
        	ExistDB.getOrCreateCollection(colRoot + "/rezervacije");
        	ExistDB.getOrCreateCollection(colRoot + "/tipovi");
        	ExistDB.getOrCreateCollection(colRoot + "/kategorije");
        	//ExistDB.getOrCreateCollection(colRoot + "/poruke");
        	
        	/*try {
        		
        		JAXBContext context = JAXBContext.newInstance("com.project.megatravel.model.accomodation");
        		
        		Marshaller marshaller = context.createMarshaller();
    			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "www.model.megatravel.project.com/accomodation");
    			
    			OutputStream os = new ByteArrayOutputStream();
    			//marshaller.marshal(sj, os);
    			
    			System.out.println("Data:\n" + os.toString());
        		
    			res = (XMLResource) col.createResource("101.xml", XMLResource.RESOURCE_TYPE);
    			
    			res.setContent(os);
    			col.storeResource(res);
                logger.info("Storing the document: " + res.getId());
                
                String[] list = col.listResources();
                
                List<Long> l2 = Arrays.asList(list).stream().map(name -> {
                	name = name.substring(0, name.indexOf('.'));
                	Long id = Long.parseLong(name);
                	return id;
                } ).collect(Collectors.toList());
                
                Long max = Collections.max(l2);
                
                logger.info("Max: " + max);
                
                /*Collection c2 = ExistDB.getOrCreateCollection(colRoot + "/jedinice");
    			
    			context = JAXBContext.newInstance("com.project.megatravel.model.accomodation");
    			
    			Unmarshaller um = context.createUnmarshaller();
    			
    			res = (XMLResource) c2.getResource("3.xml");
    			
    			//um.setValidating(true);
    			//Sj sj2 = (Sj) um.unmarshal(res.getContentAsDOM());
                
    			//System.out.println(sj2.getBrojKreveta());
    			//System.out.println(sj2.getId());
    			
    			col.removeResource(res);*/
        		
        		/*Collection c2 = ExistDB.getOrCreateCollection("/db/megatravel/jedinice");
    	        
        		String[] list = c2.listResources();
        		
                XPathQueryService xpathService = (XPathQueryService) c2.getService("XPathQueryService", "1.0");
                xpathService.setProperty("indent", "yes");
                xpathService.setNamespace("", TARGET_NAMESPACE);
                
    	        //String xpathExp = "doc(\"prvi.xml\")//book[@category=\"WEB\" and price>35]/title";
    	        String xpathExp = "//book";
    	        
    	        System.out.println("[INFO] Invoking XPath query service for: " + xpathExp);
    	        ResourceSet result = xpathService.query(xpathExp);
    	        
    	        ResourceIterator i = result.getIterator();
                Resource res1 = null;
                String toUm = "";
                
                while(i.hasMoreResources()) {
                
                	try {
                        res1 = i.nextResource();
                        toUm = res1.getContent().toString();
                        System.out.println(toUm);
                        
                    } finally {
                        
                    	// don't forget to cleanup resources
                        try { 
                        	((EXistResource)res1).freeResources(); 
                        } catch (XMLDBException xe) {
                        	xe.printStackTrace();
                        }
                    }
                }*/
                
                //Unmarshaller um = context.createUnmarshaller();
                
                //Book b2 = (Book) um.unmarshal(new StringReader(toUm)); // getContentAsDOM()
                
               //System.out.println(b2.getCategory());
        		
        		/*Collection c2 = ExistDB.getOrCreateCollection(colRoot + "/jedinice");
    			
    			XPathQueryService xpathService = (XPathQueryService) c2.getService("XPathQueryService", "1.0");
                xpathService.setProperty("indent", "yes");
                xpathService.setNamespace("", TARGET_NAMESPACE);
                
    	        //String xpathExp = "doc(\"prvi.xml\")//book[@category=\"WEB\" and price>35]/title";
    	        String xpathExp = "//rejting";
    	        
    	        System.out.println("[INFO] Invoking XPath query service for: " + xpathExp);
    	        ResourceSet result = xpathService.query(xpathExp);
    	        
    	        ResourceIterator i = result.getIterator();
                Resource res1 = null;
                String toUm = "";
                
                while(i.hasMoreResources()) {
                
                	try {
                        res1 = i.nextResource();
                        toUm = res1.getContent().toString();
                        System.out.println(toUm);
                        
                    } finally {
                        
                    	// don't forget to cleanup resources
                        try { 
                        	((EXistResource)res1).freeResources(); 
                        } catch (XMLDBException xe) {
                        	xe.printStackTrace();
                        }
                    }
                }
    		
        	} catch (XMLDBException e) {
				// TODO: handle exception
			}*/
        	
		} else {
			
		}
        
        /*try {
        	
        	addSj();
        	//getSj();
        	if (true) {
        		return;
        	}
        	
        	col = ExistDB.getOrCreateCollection(colRoot);
        
        	String documentId = "prvi.xml";
        	
	        res = (XMLResource) col.createResource("prvi.xml", XMLResource.RESOURCE_TYPE);*/
	        
	        /*String filePath = "data/books.xml";
	        
	        File f = new File(filePath);
	        
	        if(!f.canRead()) {
	            System.out.println("[ERROR] Cannot read the file: " + filePath);
	            return;
	        }
	        
	        res.setContent(f);
	        col.storeResource(res);
	        
	        Logger.info("Storing the document: " + res.getId());
	        
	        String contextXPath = "/bookstore";
	        
	        XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
	        xupdateService.setProperty("indent", "yes");
	    
    		String xmlFragment = 
    				"<book category=\"TEST\">\r\n" + 
    				"	<title lang=\"en\">Test book</title>\r\n" + 
    				"	<author>Test Author</author>\r\n" + 
    				"	<year>2016</year>\r\n" + 
    				"	<price>59.99</price>\r\n" + 
    				"</book>";
    		
    		Sj sj = new Sj();
    		sj.setBrojKreveta(new BigInteger("1"));
    		
    		Book b = new Book();
    		b.setCategory("Kids");
    		b.setPrice(new BigDecimal(20));
    		Book.Title title = new Book.Title();
    		title.setLang("eng");
    		title.setValue("Kids books");
    		b.setTitle(title);
    		b.setYear(2010);
    		b.getAuthor().add("Author 1");
    		
	        
    		JAXBContext context = JAXBContext.newInstance("com.project.megatravel.model.bookstore");
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "www.model.megatravel.project.com/accomodation");
			
			OutputStream os = new ByteArrayOutputStream();
			marshaller.marshal(b, os);
			System.out.println(os.toString());
			
    		String str = os.toString();
    		str = str.substring(str.indexOf('\n'));
    		System.out.println(str);
			
	        long mods = xupdateService.updateResource(documentId, String.format(APPEND, contextXPath, str));
	        
	        System.out.println(mods);
	        
            /*************************************************************************************************************/
             
            //Collection c2 = ExistDB.getOrCreateCollection("/db/sample/library");
	        /*Collection c2 = ExistDB.getOrCreateCollection("/db/megatravel");
	        
            XPathQueryService xpathService = (XPathQueryService) c2.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", TARGET_NAMESPACE);
            
	        //String xpathExp = "doc(\"prvi.xml\")//book[@category=\"WEB\" and price>35]/title";
	        String xpathExp = "//book";
	        
	        System.out.println("[INFO] Invoking XPath query service for: " + xpathExp);
	        ResourceSet result = xpathService.query(xpathExp);
	        
	        ResourceIterator i = result.getIterator();
            Resource res1 = null;
            String toUm = "";
            
            while(i.hasMoreResources()) {
            
            	try {
                    res1 = i.nextResource();
                    toUm = res1.getContent().toString();
                    System.out.println(toUm);
                    
                } finally {
                    
                	// don't forget to cleanup resources
                    try { 
                    	((EXistResource)res1).freeResources(); 
                    } catch (XMLDBException xe) {
                    	xe.printStackTrace();
                    }
                }
            }
            
            //Unmarshaller um = context.createUnmarshaller();
            
            //Book b2 = (Book) um.unmarshal(new StringReader(toUm)); // getContentAsDOM()
            
           //System.out.println(b2.getCategory());
	        
	        col.storeResource(res);
	        logger.info("Done");
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
            if(res != null) {
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
            
            if(col != null) {
                try { 
                	col.close(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
		}*/
		
	}
	
	public static void addSj() {
		
		/*XMLResource res = null;
		
		try {
			Collection c2 = ExistDB.getOrCreateCollection("/db/sample/library");
			
			Sj sj = new Sj();
    		sj.setBrojKreveta(new BigInteger("1"));
    		SmestajniObjekat so = new SmestajniObjekat();
    		so.setId(2L);
    		so.setTipSmestaja(UUID.randomUUID().toString());
    		sj.setSObjekat(so);
    		
    		JAXBContext context = JAXBContext.newInstance("com.project.megatravel.model.accomodation");
    		
    		Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "www.model.megatravel.project.com/accomodation");
			
			OutputStream os = new ByteArrayOutputStream();
			marshaller.marshal(sj, os);
			
			System.out.println("Data:\n" + os.toString());
    		
			res = (XMLResource) c2.createResource(so.getTipSmestaja() + ".xml", XMLResource.RESOURCE_TYPE);
			
			res.setContent(os);
			c2.storeResource(res);
            System.out.println("[INFO] Storing the document: " + res.getId());
			
		} catch (XMLDBException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
	public static void getSj() {
		
		/*XMLResource res = null;
		
		try {
			
			Collection c2 = ExistDB.getOrCreateCollection("/db/sample/library");
			
			JAXBContext context = JAXBContext.newInstance("com.project.megatravel.model.accomodation");
			
			Unmarshaller um = context.createUnmarshaller();
			
			res = (XMLResource) c2.getResource("so.xml");
			
			//um.setValidating(true);
			Sj sj = (Sj) um.unmarshal(res.getContentAsDOM());
			
			System.out.println("[INFO] Showing the document as JAXB instance: ");
			System.out.println(sj.getSObjekat().getTipSmestaja());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
	

	public static void readProperties(Properties props) throws IOException {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream(EXIST_PROPERTIES_FILE);
		
		props.load(stream);

	}
	
    public static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }
    
    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
        
        Collection col = DatabaseManager.getCollection(uri + collectionUri, user, password);
        
        // create the collection if it does not exist
        if(col == null) {
        
         	if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            
        	String pathSegments[] = collectionUri.split("/");
            
        	if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
            
                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }
                
                Collection startCol = DatabaseManager.getCollection(uri + path, user, password);
                
                if (startCol == null) {
                	
                	// child collection does not exist
                    
                	String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(uri + parentPath, user, password);
                    
                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
                    
                    logger.info("Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                    
                    col.close();
                    parentCol.close();
                    
                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		ExistDB.user = user;
	}
	
	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ExistDB.password = password;
	}

	public static String getUri() {
		return uri;
	}

	public static void setUri(String uri) {
		ExistDB.uri = uri;
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		ExistDB.driver = driver;
	}

	public static String getExistPropertiesFile() {
		return EXIST_PROPERTIES_FILE;
	}

}
