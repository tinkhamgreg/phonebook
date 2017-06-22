import java.util.*;
import java.io.*;

//Node class stores data of each DVD.
public class Node
{
	private String title;
	private String director;
	private String year;
	private int actnum;
	private String[] actor;
	private String inout;
	private String rentid;
	private String datestamp;
	private Node link;
	private String[] titleindexs;
	private Node[] titleindexn;

	public Node(String title, String director, String year, int actnum, String[] actor, String inout, String rentid, String datestamp)
	{
		this.title = title;
		this.director = director;
		this.year = year;
		this.actnum = actnum;
		this.actor = actor;
		this.inout = inout;
		this.rentid = rentid;
		this.datestamp = datestamp;
	}

	public void setLink(Node link)
    	{
		this.link = link;
		}

	public Node getLink()
    	{
		return link;
    	}

	public void setTitle(String title)
    	{
		this.title = title;
    	}

    	public String getTitle()
    	{
		return title;
    	}

	public void setDirector(String director)
    	{
		this.director = director;
    	}

    	public String getDirector()
    	{
		return director;
    	}

	public void setYear(String year)
    	{
		this.year = year;
    	}

   	public String getYear()
    	{
		return year;
    	}

	public void setActnum(int actnum)
    	{
		this.actnum = actnum;
    	}

    	public int getActnum()
    	{
		return actnum;
    	}

	public void setActor(String[] actor)
    	{
		this.actor = actor;
    	}

    	public String[] getActor()
    	{
		return actor;
    	}

	public void setCheckout(String inout)
    	{
		this.inout = inout;
	}

	public String getCheckout()
    	{
		return inout;
    	}

	public void setRentID(String rentid)
	{
		this.rentid = rentid;
	}

	public String getRentID()
	{
		return rentid;
	}

	public void setDate(String datestamp)
    	{
		this.datestamp = datestamp;
	}

	public String getDate()
    	{
		return datestamp;
    	}
}
