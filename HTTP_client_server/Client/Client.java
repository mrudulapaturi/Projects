import java.io.*;
import java.net.*;
import java.awt.*;

class Client{
	 static String signal=new String();
	 public final static int FILE_SIZE = 6022386;
	public static void main(String[]args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		String command = args[2];
		String filename = args[3];
		
		Socket client = new Socket(hostname,port);
		

		OutputStream out=client.getOutputStream();
		DataOutputStream o=new DataOutputStream(out);
		
		o.writeUTF(command);
		o.writeUTF(filename);

		InputStream i=client.getInputStream();
		DataInputStream in=new DataInputStream(i);


		
		
		int bytesRead;
    		int current = 0;
		File myFile=null;
    		FileOutputStream fos=null;FileInputStream fis=null;
   		BufferedOutputStream bos=null; BufferedInputStream bis=null; 
		
		if(command.equalsIgnoreCase("GET")){			
			try{

				String str=in.readUTF();
				if(str.equals("404 Not Found")){
					System.out.println("404 Not Found");
					return;
				}
      				// receive file
				System.out.println(str);
      				byte [] mybytearray  = new byte [FILE_SIZE];
      				InputStream is = client.getInputStream();
      				fos = new FileOutputStream(filename);
     				bos = new BufferedOutputStream(fos);
		
      				bytesRead = is.read(mybytearray,0,mybytearray.length);
		
      				current = bytesRead;
				System.out.println("Now");
      				while(bytesRead != -1){
         				bytesRead =is.read(mybytearray, current, (mybytearray.length-current));
         				if(bytesRead >= 0) 
						current += bytesRead;
      				}

      				bos.write(mybytearray, 0 , current);
      				bos.flush();
      				System.out.println("File " + filename + " downloaded (" + current + " bytes read)");
		
				while(true){
					System.out.println("Do you wish to view the file in the command prompt or in a browser ??? ");
					System.out.println("Enter : 1) Command Prompt \n2)Browser");
					int choice = Integer.parseInt(br.readLine());
					if(choice == 1){
						FileReader fr=new FileReader(new File(filename));
						BufferedReader br1=new BufferedReader(fr);
						String s=br1.readLine();
						while(s!=null){
							System.out.println(s);
							s=br1.readLine();
						}
						break;
					}
					else if(choice==2){
						//Check whether it is an HTML file or not
						if(filename.indexOf(".html")!=-1){
							if(Desktop.isDesktopSupported())
							{
  								Desktop.getDesktop().browse(new URI("index.html"));
								break;
							}
						}
						else{
							System.out.println("Not an html file");	
					
						}
					}
					else{
						System.out.println("Wrong option. Enter again:");
					}
				}
   	 
			}
		
			finally{
				if(  fos!=null && bos!=null ){
    					fos.close();
    					bos.close();
				}
    			
			}
			
				
		
		}
		else if(command.equalsIgnoreCase("put")){
			String s=new String();
			try{
			
			myFile = new File (filename);
			fis = new FileInputStream(myFile);
			s = "Present";	
			o.writeUTF(s);
			signal = in.readUTF();
			}
			catch(FileNotFoundException e){
				s = "Absent";
				System.out.println("No such files present.");
				o.writeUTF(s);
				return;
			}
			
          		byte [] mybytearray  = new byte [(int)myFile.length()];
          		
          		bis = new BufferedInputStream(fis);
          		bis.read(mybytearray,0,mybytearray.length);
          		System.out.println("Sending " + filename + "(" + mybytearray.length + " bytes)");
			System.out.println(signal);
          		out.write(mybytearray,0,mybytearray.length);
			
          		out.flush();
          		System.out.println("Done.");
			
			
			
			
			fis.close();
			bis.close();
			

		}
		else if(command.equalsIgnoreCase("shutdown")){
			if(filename.equalsIgnoreCase("server")){
				System.out.println("Server successfully shutdown...");
				System.out.println("Since you didn't request the server anything , even the client is shutting down.");
			}
		}
		
		client.close();
		
	}	 
	
}
