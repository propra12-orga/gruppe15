package game;

import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Settings {
	/**
	 * Static var for Singleton
	 */
	private static Settings instance = null;

	private String file = "/ressources/settings.xml";

	/**
	 * Get Singleton-Instance
	 * 
	 * @return Game
	 */
	public static Settings getInstance() {
		if (Settings.instance == null) {
			Settings.instance = new Settings();
		}
		return Settings.instance;
	}

	private Document doc;

	private Settings() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			this.doc = builder.parse(Main.class.getResourceAsStream(this.file));
		} catch (Exception e) {
			Debug.log(Debug.ERROR, "Can't load settings");
		}
	}

	public String getString(String key) {
		NodeList list = this.doc.getDocumentElement().getElementsByTagName(key);
		if (list.getLength() > 0) {
			return list.item(0).getTextContent();
		} else {
			return "";
		}
	}

	public boolean getBoolean(String key) {
		NodeList list = this.doc.getDocumentElement().getElementsByTagName(key);
		if (list.getLength() > 0) {
			return Boolean.valueOf(list.item(0).getTextContent());
		} else {
			return false;
		}
	}

	public void set(String key, Object value) {
		NodeList list = this.doc.getDocumentElement().getElementsByTagName(key);
		if (list.getLength() > 0) {
			list.item(0).setTextContent(value.toString());
		} else {
			Element el = this.doc.createElement(key);
			el.setTextContent(value.toString());
			this.doc.getDocumentElement().appendChild(el);
		}
		this.save();
	}

	public void save() {
		DOMSource source = new DOMSource(this.doc);

		// PrintStream will be responsible for writing
		// the text data to the file
		PrintStream ps;
		try {
			ps = new PrintStream(Main.class.getResource(this.file).getPath());
			StreamResult result = new StreamResult(ps);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			Debug.log(Debug.ERROR, "Can't write settings");
		}
	}
}
