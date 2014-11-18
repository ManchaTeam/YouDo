package com.manchateam.youdo;

public class ModelCheckBox{
	 
    private int icon;
    private String title;
    private boolean checkbox;
 
    private boolean isGroupHeader = false;
 
    public ModelCheckBox(String title) {
       this(title,false);
        isGroupHeader = true;
    }
    public ModelCheckBox(String title, boolean counter) {
        super();
       // this.icon = icon;
        this.title = title;
        this.checkbox = counter;
    }
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getCounter() {
		return checkbox;
	}
	public void setCounter(boolean counter) {
		this.checkbox = counter;
	}
	public boolean isGroupHeader() {
		return isGroupHeader;
	}
	public void setGroupHeader(boolean isGroupHeader) {
		this.isGroupHeader = isGroupHeader;
	}
 
    
    
}