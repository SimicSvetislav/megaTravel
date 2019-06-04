package com.project.megatravel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Properties;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import com.project.megatravel.model.accomodation.Sj;
import com.project.megatravel.model.accomodation.SmestajniObjekat;

public class ExistDB {
	
	private final static String EXIST_PROPERTIES_FILE = "exist.properties";
	private static final String TARGET_NAMESPACE = "http://www.model.megatravel.project.com/bookstore";
	//private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/examples/xmldb/bookstore";
	
	private static String user;
	private static String password;
	private static String uri;
	private static String driver;
	private static String colRoot;

	private ExistDB() { }
	
	public static void initDatabase() throws Exception {
		
		Properties props = new Properties();
		
		try {
			readProperties(props);
		} catch (IOException e) {
			System.err.println("Error reading exist database properties file");
			e.printStackTrace();
		}
		
		setUser(props.getProperty("exist.connection.user", "admin").trim());
		password = props.getProperty("exist.connection.password", "").trim();
		
		uri = props.getProperty("exist.connection.uri").trim();
		String host = props.getProperty("exist.connection.host").trim();
		int port = Integer.parseInt(props.getProperty("exist.connection.port", "8080").trim());
		
		uri = String.format(uri, host, port);
		
		driver = props.getProperty("exist.connection.driver").trim();

		colRoot = props.getProperty("exist.collection.root").trim();
		
		String action = props.getProperty("exist.action").trim();
		
		System.out.println("[INFO] Loading driver class: " + driver);
		Class<?> cl = Class.forName(driver);
        
        // encapsulation of the database driver functionality
    	Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
        
        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);
		
        Collection col = null;
        XMLResource res = null;
        
        try {
        	
        	addSj();
        	//getSj();
        	if (true) {
        		return;
        	}
        	
        	col = ExistDB.getOrCreateCollection(colRoot);
        
        	String documentId = "prvi.xml";
        	
	        res = (XMLResource) col.createResource("prvi.xml", XMLResource.RESOURCE_TYPE);
	        
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
	        Collection c2 = ExistDB.getOrCreateCollection("/db/megatravel");
	        
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
	        Logger.info("Done");
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
		}
		if (action.equals("create")) {
			//new Repository();
		}
	}
	
	public static void addSj() {
		
		XMLResource res = null;
		
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
		
		
	}
	
	public static void getSj() {
		
		XMLResource res = null;
		
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
                    
                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
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
