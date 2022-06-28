/**
 * @author Madhu with credit to Reeta Suman.
 * 
 *         
 * 
 */

package utilities;

public class XMLTag {
	
	//Attributes
	private String tagName;
	private int line;
	private boolean closeTag;
	
	//Constructors
	public XMLTag(String tagName, boolean closeTag)
	{
		this.setTagName(tagName);
		this.setCloseTag(closeTag);
	}
	
	public XMLTag(String tagName, int line, boolean closeTag)
	{
		this.setTagName(tagName);
		this.setLine(line);
		this.setCloseTag(closeTag);
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public boolean isCloseTag() {
		return closeTag;
	}

	public void setCloseTag(boolean closeTag) {
		this.closeTag = closeTag;
	}
	
		

}
