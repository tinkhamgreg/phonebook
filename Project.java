import java.util.*;
import java.io.*;


public class Project
{
	private Scanner scan = new Scanner(System.in);
	protected Node start;
	private int rows=0;
	private int fileindex=0;
	private int chunk = 1000000;
	private int[] file = new int[chunk];
	private String[] titleindexs;
	private String[] dirindexs;
	private String[] yearindexs;
	private String[] idindexs;
	private String[][] actorindexs;
	private Node[] titleindexn;
	private Node[] dirindexn;
	private Node[] yearindexn;
	private Node[] idindexn;
	private Node[] actorindexn;
	
	public void Project() throws IOException
	{
		
	}
	
	//Handles user input and the main menu of the program.
	public void run() throws IOException
	{
		System.out.println("Welcome to the DVD checkout tool!");
		while(true)
		{
			System.out.println("\t"+"Menu");
			System.out.println("1. Load a text file");
			System.out.println("2. Search by Title");
			System.out.println("3. Search by Director");
			System.out.println("4. Search by Year");
			System.out.println("5. Search by Actor");
			System.out.println("6. Search by Renter ID");
			System.out.println("0. Exit the Program");
			System.out.print("Please enter a number from the menu: ");
			int choice = scan.nextInt();
			
			if(choice == 1)
			{
				loadFile();
			}
			else if(choice == 2)
			{
				searchTitle();
			}
			else if(choice == 3)
			{
				searchDirector();
			}
			else if(choice == 4)
			{
				searchYear();
			}
			else if(choice == 5)
			{
				searchActor();
			}
			else if(choice == 6)
			{
				searchRenter();
			}
			else if(choice == 0)
			{
				return;
			}
		}
	}
	
	
	public void loadFile() throws IOException
	{
		System.out.print("Please enter the name of a text file to be stored: ");
		String n = scan.next();
		File s = new File(n);
		Scanner filescanner = new Scanner(s);
		String title;
		String director;
		String year;
		String[] actor;
		String inout;
		String rentid;
		String datestamp;
		
		int dbnum;
		int actnum;
		int add;
		
		add = filescanner.nextInt();
		dbnum = filescanner.nextInt();
		
		
		//Algorithm which takes in DVD entries from text file and adds them to the linked list.
		if(fileCheck(dbnum)==true)
		{
			fileAdd(dbnum);
			rows = rows + add;
			for(int i =0; i < add; i++)
			{
				title = filescanner.next();
				director = filescanner.next();
				year = filescanner.next();
				actnum = filescanner.nextInt();
				actor = new String [actnum];
				for(int j = 0; j < actnum; j++)
				{
					actor[j] = filescanner.next();
				}
				inout = filescanner.next();
				if(Objects.equals(inout, new String("out")))
				{
					rentid = filescanner.next();
					datestamp = filescanner.next();
					add(title,director,year,actnum,actor,inout,rentid,datestamp);
				}
				else
				{
					add(title,director,year,actnum,actor,inout," "," ");
				}
			}
			System.out.println("Text file has successfully been stored!");
			indexTitle();
			indexDirector();
			indexYear();
			indexID();
			indexActor();
		}
		else
		{
			System.out.println("This file has already been loaded into the database.");
		}
	}
	
	public void add(String title, String director, String year, int actnum, String[] actor, String inout, String rentid, String datestamp) 
	{
		Node pointer = new Node(title,director, year, actnum, actor, inout, rentid, datestamp);
		
		if(start == null) 
        {
            start = pointer;
        }
        else 
        {
            pointer.setLink(start);
            start = pointer;
        }
	}
	
	//Search functions for all searchable terms.
	public void searchTitle()
	{	
		String term;
		String result;
		Node tmp;
		int size;
		int i = 1;
		String[] array = new String[rows];
		Node[] narray = new Node[rows];
		
		System.out.print("Please enter a title to search: ");
		term = scan.next();
		if (start.getLink() == null) 
        	{
            		System.out.println("Database is empty.");
           		 return;
        	}
		
        	for(int j=0; j< rows; j++)
		{
			result = titleindexs[j];
			size = term.length();
			if(size <= result.length())
			{
				result = result.substring(0,size);
			}
            		if(term.toLowerCase().equals(result.toLowerCase()))
			{
				tmp = titleindexn[j];
				array[i] = i+". "+tmp.getTitle()+" "+tmp.getDirector()+" "+tmp.getYear()+" "+Arrays.toString(tmp.getActor()).replace(",", "").replace("[", "").replace("]", "").trim()+" "+ tmp.getCheckout()+" "+tmp.getRentID()+ " "+tmp.getDate();
				narray[i] = tmp;
				i++;
			}
		}
		checkOut(array,narray);
	}
	
	public void searchDirector()
	{
		String term;
		String result;
		Node tmp;
		int size;
		int i = 1;
		String[] array = new String[rows];
		Node[] narray = new Node[rows];
		
		System.out.print("Please enter a director to search: ");
		term = scan.next();
		if (start.getLink() == null) 
        	{
            		System.out.println("Database is empty.");
            		return;
        	}
		
        	for(int j=0; j< rows; j++)
		{
			result = dirindexs[j];
			size = term.length();
			if(size <= result.length())
			{
				result = result.substring(0,size);
			}
            		if(term.toLowerCase().equals(result.toLowerCase()))
			{
				tmp = dirindexn[j];
				array[i] = i+". "+tmp.getTitle()+" "+tmp.getDirector()+" "+tmp.getYear()+" "+Arrays.toString(tmp.getActor()).replace(",", "").replace("[", "").replace("]", "").trim()+" "+ tmp.getCheckout()+" "+tmp.getRentID()+ " "+tmp.getDate();
				narray[i] = tmp;
				i++;
			}
		}
		checkOut(array,narray);
	}
	
	public void searchYear()
	{
		String term;
		String result;
		Node tmp;
		int size;
		int i = 1;
		String[] array = new String[rows];
		Node[] narray = new Node[rows];
		
		System.out.print("Please enter a year to search: ");
		term = scan.next();
		if (start.getLink() == null) 
        	{
            		System.out.println("Database is empty.");
            		return;
        	}
		
        	for(int j=0; j< rows; j++)
		{
			result = yearindexs[j];
			size = term.length();
			if(size <= result.length())
			{
				result = result.substring(0,size);
			}
            		if(term.toLowerCase().equals(result.toLowerCase()))
			{
				tmp = yearindexn[j];
				array[i] = i+". "+tmp.getTitle()+" "+tmp.getDirector()+" "+tmp.getYear()+" "+Arrays.toString(tmp.getActor()).replace(",", "").replace("[", "").replace("]", "").trim()+" "+ tmp.getCheckout()+" "+tmp.getRentID()+ " "+tmp.getDate();
				narray[i] = tmp;
				i++;
			}
		}
		checkOut(array,narray);
	}
	
	public void searchActor()
	{
		String term;
		String actor;
		String[] actarray;
		Node tmp;
		int size;
		int i = 1;
		String[] array = new String[rows];
		Node[] narray = new Node[rows];
		
		System.out.print("Please enter an actor to search: ");
		term = scan.next();
		
		if (start.getLink() == null) 
        	{
            		System.out.println("Database is empty.");
            		return;
        	}
       	 	tmp = start;
		
       		for(int n = 0;n <rows; n++)
	   	{
		    	actarray = actorindexs[n];
			for(int j = 0; j < actarray.length; j++)
			{
				size = term.length();
				actor = actarray[j];
				if(size <= actor.length())
				{
					actor = actor.substring(0,size);
				}
				if(term.toLowerCase().equals(actor.toLowerCase()))
				{
					array[i] = i+". "+tmp.getTitle()+" "+tmp.getDirector()+" "+tmp.getYear()+" "+Arrays.toString(tmp.getActor()).replace(",", "").replace("[", "").replace("]", "").trim()+" "+ tmp.getCheckout()+" "+tmp.getRentID()+ " "+tmp.getDate();
					narray[i] = tmp;
					i++;
				}
			}
			tmp = tmp.getLink();
        	}
		checkOut(array,narray);
	}
	
	public void searchRenter()
	{
		String term;
		String result;
		Node tmp;
		int size;
		int i = 1;
		String[] array = new String[rows];
		Node[] narray = new Node[rows];
		
		System.out.print("Please enter a year to search: ");
		term = scan.next();
		if (start.getLink() == null) 
        	{
            		System.out.println("Database is empty.");
            		return;
        	}
		
        	for(int j=0; j< rows; j++)
		{
			result = idindexs[j];
			size = term.length();
			if(size <= result.length())
			{
				result = result.substring(0,size);
			}
            		if(term.toLowerCase().equals(result.toLowerCase()))
			{
				tmp = idindexn[j];
				array[i] = i+". "+tmp.getTitle()+" "+tmp.getDirector()+" "+tmp.getYear()+" "+Arrays.toString(tmp.getActor()).replace(",", "").replace("[", "").replace("]", "").trim()+" "+ tmp.getCheckout()+" "+tmp.getRentID()+ " "+tmp.getDate();
				narray[i] = tmp;
				i++;
			}
		}
		checkOut(array,narray);
	}
	
	//Function to handle the checking in and out of a DVD.
	public void checkOut(String[] array, Node[] narray)
	{
		Scanner checkscan = new Scanner(System.in);
		
		if(array[1] == null)
		{
			System.out.println("No results found. Please search again.");
			return;
		}
		for (int i = 1; i < array.length; i++) 
		{
			if(array[i] != null)
			{
				System.out.println(array[i]);
			}
		}
		System.out.print("Please enter a number to view the corresponding title or a 0 to return to the main menu: ");
		int choice = checkscan.nextInt();
		
		if(choice == 0)
		{
			return;
		}
		else
		{
			System.out.println(array[choice]);
			System.out.print("Please enter 1 to checkin or 2 to checkout the title. Enter a zero to return to the main menu: ");
			String check = checkscan.next();
			if(check.equals("1"))
			{
				narray[choice].setCheckout("in");
				narray[choice].setRentID(" ");
				narray[choice].setDate(" ");
				System.out.println("DVD successfully checked in!");
			}
			if(check.equals("2"))
			{
				narray[choice].setCheckout("out");
				System.out.print("Please enter the Renter's ID: ");
				narray[choice].setRentID(checkscan.next());
				System.out.print("Please enter the Date: ");
				narray[choice].setDate(checkscan.next());
				System.out.println("DVD successfully checked out!");
			}
			if(check.equals("0"))
			{
				return;
			}
		}
	}
	
	//Functions to build index arrays for all searchable terms.
	public void indexTitle()
	{
		titleindexs = new String[rows];
		titleindexn = new Node[rows];
		
		String temps;
		Node tempn;
		Node tmp = start;

		for(int i=0; i < rows; i++)
		{
			titleindexs[i] = tmp.getTitle();
			titleindexn[i] = tmp;
			tmp = tmp.getLink();
		}

		//Exhcange sort algorithm to sort the index arrays.
		for(int j=0; j<titleindexs.length;j++)
		{
			for (int i=j+1 ; i<titleindexs.length; i++)
			{
				if(titleindexs[i].toLowerCase().compareTo(titleindexs[j].toLowerCase())>0)
				{
					temps = titleindexs[i];
					tempn = titleindexn[i];
					titleindexs[i]= titleindexs[j];
					titleindexn[i]= titleindexn[j];
					titleindexs[j]=temps;
					titleindexn[j]=tempn;
				}
			}
		}
	}
	
	public void indexDirector()
	{
		dirindexs = new String[rows];
		dirindexn = new Node[rows];
		
		String temps;
		Node tempn;
		Node tmp = start;

		for(int i=0; i < rows; i++)
		{
			dirindexs[i] = tmp.getDirector();
			dirindexn[i] = tmp;
			tmp = tmp.getLink();
		}

		//Exhcange sort algorithm to sort the index arrays.
		for(int j=0; j<dirindexs.length;j++)
		{
			for (int i=j+1 ; i<dirindexs.length; i++)
			{
				if(dirindexs[i].toLowerCase().compareTo(dirindexs[j].toLowerCase())>0)
				{
					temps = dirindexs[i];
					tempn = dirindexn[i];
					dirindexs[i]= dirindexs[j];
					dirindexn[i]= dirindexn[j];
					dirindexs[j]=temps;
					dirindexn[j]=tempn;
				}
			}
		}
	}
	
	public void indexYear()
	{
		yearindexs = new String[rows];
		yearindexn = new Node[rows];
		String temps;
		Node tempn;
		Node tmp = start;

		for(int i=0; i < rows; i++)
		{
			yearindexs[i] = tmp.getYear();
			yearindexn[i] = tmp;
			tmp = tmp.getLink();
		}

		//Exchange sort algorithm to sort the index arrays.
		for(int j=0; j<yearindexs.length;j++)
		{
			for (int i=j+1 ; i<yearindexs.length; i++)
			{
				if(yearindexs[i].toLowerCase().compareTo(yearindexs[j].toLowerCase())>0)
				{
					temps = yearindexs[i];
					tempn = yearindexn[i];
					yearindexs[i]= yearindexs[j];
					yearindexn[i]= yearindexn[j];
					yearindexs[j]=temps;
					yearindexn[j]=tempn;
				}
			}
		}
	}
	
	public void indexID()
	{
		idindexs = new String[rows];
		idindexn = new Node[rows];
		String temps;
		Node tempn;
		Node tmp = start;

		for(int i=0; i < rows; i++)
		{
			idindexs[i] = tmp.getRentID();
			idindexn[i] = tmp;
			tmp = tmp.getLink();
		}

		//Exchange sort algorithm to sort the index arrays.
		for(int j=0; j<idindexs.length;j++)
		{
			for (int i=j+1 ; i<idindexs.length; i++)
			{
				if(idindexs[i].toLowerCase().compareTo(idindexs[j].toLowerCase())>0)
				{
					temps = idindexs[i];
					tempn = idindexn[i];
					idindexs[i]= idindexs[j];
					idindexn[i]= idindexn[j];
					idindexs[j]=temps;
					idindexn[j]=tempn;
				}
			}
		}
	}
	
	public void indexActor()
	{
		actorindexs = new String[rows][];
		actorindexn = new Node[rows];
		String[] temps;
		Node tempn;
		Node tmp = start;

		for(int i=0; i < rows; i++)
		{
			actorindexs[i] = tmp.getActor();
			actorindexn[i] = tmp;
			tmp = tmp.getLink();
		}

		//Exchange sort algorithm to sort the index arrays.
		for(int j=0; j<actorindexs.length;j++)
		{
			for (int i=j+1 ; i<actorindexs.length; i++)
			{
				if(Arrays.toString(actorindexs[i]).toLowerCase().compareTo(Arrays.toString(actorindexs[j]).toLowerCase())>0)
				{
					temps = actorindexs[i];
					tempn = actorindexn[i];
					actorindexs[i]= actorindexs[j];
					actorindexn[i]= actorindexn[j];
					actorindexs[j]=temps;
					actorindexn[j]=tempn;
				}
			}
		}
	}
	
	public boolean fileCheck(int dbnum)
	{
		for(int i=0;i<file.length;i++)
		{
			if(file[i]==dbnum)
			{
				return false;
			}
		}
		return true;
	}
	
	public void fileAdd(int dbnum)
	{
		if(fileindex==chunk-1)
		{
			chunk = chunk*10;
			int[] temp = file;
			file = new int[chunk];
			for(int i=0; i <temp.length; i++)
			{
				file[i]=temp[i];
			}
			
		}
		file[fileindex]=dbnum;
		fileindex++;
	}
}			