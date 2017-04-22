package controlpac;

import java.io.*;


class bdatoa
{
	public static void ps(String x)
    {
		System.out.print(x);
    } 
    
	public static int leerEntero() {
		String l�nea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			l�nea = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int ne = 0;
		try {
			ne = Integer.parseInt(l�nea);
		} catch (Exception e) {
		}
		;
		return (ne);
	}
    
    
    public static String leerCadena()
	  	{
	    String l�nea= new String(""); 
		try{ 
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
			l�nea = br.readLine(); 
			}catch(Exception e){ e.printStackTrace();} 
	    double ne=0;
		return(l�nea);		
		}
     	   
    public static void main(String args[]) throws Exception
    {
     String op="";
     int sw=0, sw1=0;
     int op1,op2,op3;  //variables de selecci�n usadas en los diferentes men�s 
     String codpac,cp,nompac,codmed,cm,enfpac,nommed,espmed; //variables usadas en el registro de datos
     String codp="",codpa="",nomp="",nompa="",codm="", codme="", enfp="", nomm="", espm=""; //variables usadas en la lectura de datos
     String codtem;           //variables auxiliares temporales
     
     do
     {
     	op1=0;
     	
     	ps("   .............................................."+"\n");
     	ps("   :-:        C E N T R O  M E D I C O        :-:"+"\n");
     	ps("   :-:   >>>> L O S  L A U R E L E S <<<<     :-:"+"\n");
     	ps("   :-:  C O N T R O L  D E  P A C I E N T E S :-:"+"\n");
     	ps("   :-:........................................:-:"+"\n");
     	ps("   :-: 1.  Ingreso de datos                   :-:"+"\n");
     	ps("   :-: 2.  Informes                           :-:"+"\n");
     	ps("   :-: 3.  Salir                              :-:"+"\n");
     	ps("   .............................................."+"\n");
        ps("   ....Elija la opcion deseada: ");
        //ps("\n");
        op1=leerEntero();
        if (op1<1||op1>3)
         {ps("Debe digitar una opcion del menu"+"\n");
         }
        
        
        if (op1==1)   //seleci�n ingreso de pacientes           
           { 
            
           do 
           { 
             
             
	  	     ps("   ..............................................."+"\n");
	  	     ps("   :-: -I N G R E S O  D E  P A C I E N T E S- :-:"+"\n");
		     ps("   :-:.........................................:-:"+"\n");
		     ps("   :-: 1.  Datos del paciente                  :-:"+"\n");
		     ps("   :-: 2.  Situacion del paciente              :-:"+"\n");
		     ps("   :-: 3.  Datos del medico                    :-:"+"\n");
		     ps("   :-: 4.  Anterior                            :-:"+"\n");
		     ps("   ..............................................."+"\n");
		     ps("   ....Elija la opcion deseada: ");
		     
		     op2=leerEntero();
		     
		     if (op2<1||op2>4)
		      {ps("Debe digitar una opcion del menu"+"\n");
		      }
             
            switch (op2)   
            {
             case 1:   // ingreso de datos, datos del paciente
	                 DataOutputStream datopac=null;
				     datopac=new DataOutputStream (new FileOutputStream("C:\\datopac.txt"));
                try
                {
    			            
	            do
                {
                 
	  	     	 ps("   ..............................................."+"\n");
	  	     	 ps("   :-:  - D A T O S  D E L  P A C I E N T E -  :-:"+"\n");
		     	 ps("   :-:.........................................:-:"+"\n");
                    
						ps("Digite el codigo del paciente: ");
		                codpac=leerCadena();
		                datopac.writeUTF(codpac);
		                ps("Digite el nombre del paciente: ");
		                nompac=leerCadena();
		                
		                datopac.writeUTF(nompac);
		                
		        ps("Desea ingresar otro paciente? S/N"+"\n");
		        
                op=leerCadena();
		        
		        }while (op.equals("S")||op.equals("s")); 
		        datopac.close(); 
		     
		       }catch(IOException ioe){}; 
		     
		     break;
		          //ingreso de datos, situacion del paciente
		      case 2:
		            DataOutputStream situpac=null;
			        situpac=new DataOutputStream (new FileOutputStream("C:\\situpac.txt"));
		        
		        try
			   {
		        do
		        {
		          
	  	     	 ps("   ....................................................."+"\n");
	  	     	 ps("   :-: - S I T U A C I O N  D E L  P A C I E N T E - :-:"+"\n");
		     	 ps("   :-:...............................................:-:"+"\n");
		         
		         
						ps("Digite el codigo del paciente: ");
		                codp=leerCadena();
		                situpac.writeUTF(codp);
		                ps("Digite el codigo del medico: ");
		                codm=leerCadena();
		                situpac.writeUTF(codm);
		                ps("Digite el diagnostico del medico: ");
		                enfpac=leerCadena();
		                situpac.writeUTF(enfpac);
		                
		                
		            
		         
		        ps("Desea ingresar otro registro al historial? S/N");
		        ps("\n");
		        op=leerCadena();
		       
		        }while (op.equals("S")||op.equals("s")); 
		        situpac.close();
		       }catch(IOException ioe){}; 
		       break; 
		        
		         	
		      
		      
		      case 3:
		           DataOutputStream datomed=null;
			       datomed=new DataOutputStream (new FileOutputStream("C:\\datomed.txt"));
		         try
				  { 
		           do
		           {
		           
	  	     	    ps("   ....................................................."+"\n");
	  	     	    ps("   :-:      - D A T O S   D E L   M E D I C O -      :-:"+"\n");
		     	    ps("   :-:...............................................:-:"+"\n");
		     	    
			         	ps("Digite el codigo del medico: ");
		                codmed=leerCadena();
		                datomed.writeUTF(codmed);
		            
						ps("Digite el nombre del medico: ");
		                nommed=leerCadena();
		                datomed.writeUTF(nommed);
		          
		                ps("Digite la especializacion del medico: ");
		                espmed=leerCadena();
		                datomed.writeUTF(espmed);
		                
		                
		                
		           	ps("Desea ingresar otro medico? S/N");
		            ps("\n");
		            
		        
                    op=leerCadena();
		       
		           }while (op.equals("S")||op.equals("s"));
		         }catch(IOException ioe){}; 
		         datomed.close();
	        } 
	       }while (op2!=4); 
          }
       else
       {
   	    if (op1==2)     //seleci�n informes 
   	    {
   	    
   	    do
       	 {
				 ps("   ..............................................."+"\n");
	  	     	 ps("   :-:  C O N T R O L  D E  P A C I E N T E S  :-:"+"\n");
	  	     	 ps("   ..............................................."+"\n");
	  	     	 ps("   :-:           - I N F O R M E S -           :-:"+"\n");
		     	 ps("   :-:.........................................:-:"+"\n");
		     	 ps("   :-: 1. Listado de pacientes por medico      :-:"+"\n");
		     	 ps("   :-: 2. Enfermedades que atiende cada medico :-:"+"\n");
		     	 ps("   :-: 3. Anterior                             :-:"+"\n");
		     	 ps("   ..............................................."+"\n");
		         ps("   ....Elija la opcion deseada: ");
		         op2=leerEntero();
		         if (op2<1||op2>3)
		         {ps("Seleccione una de las opciones del menu"+"\n");
		         }	   	 	
       	 	  
       	 	  switch (op2)
       	 	  {
       	 	  	case 1:
       	 	  	 try
			     {
			     
				 ps("Digite el codigo del medico que desea consultar: ");
                 codtem=leerCadena();
                 
                 DataInputStream datomed=null;
			     datomed=new DataInputStream (new FileInputStream("C:\\datomed.txt"));
			     
			     sw=1;
	                 while (sw!=0)
	                 { try
	                  {
	                   codm=datomed.readUTF();
	                   nomm=datomed.readUTF();
	                   espm=datomed.readUTF();
	                   	                
	                  }catch(EOFException e){sw=0;} 
					 
					if (codm.equals(codtem)) //compara el codigo extraido de la 
					                         //tabla "datomed" con el codigo 
					                         //digitado
					 {
					 	ps("::: El medico "+nomm+" atiende a los siguientes pacientes: "+"\n");
					 	DataInputStream situpac=null;
			            situpac=new DataInputStream (new FileInputStream("C:\\situpac.txt"));
			            
			             
			             sw=1;
		                 while (sw!=0)
		                 { try
		                  {
		                   codp=situpac.readUTF();
		                   codme=situpac.readUTF();
		                   enfp=situpac.readUTF();
		                 
			            			            
			            if (codme.equals(codtem))   //compara el codigo medico de la 
			                                      //tabla "datomed" con el de la 
			                                      //tabla "situpac"
			            {
				            DataInputStream datopac=null;
				            datopac=new DataInputStream (new FileInputStream("C:\\datopac.txt"));
				           
				           sw1=1;
			                 while (sw1!=0)
			                 { try
			                  {
			                   codpa=datopac.readUTF();
			                   nompa=datopac.readUTF();
			                   
			                 
			               
			                 if (codpa.equals(codp))  //compara el codigo del 
			                                          //paciente de la tabla "situpac" 
			                                          //con el codigo del paciente de 
			                                          //la tabla "datopac"
			               {
			               	ps("::: Paciente: "+nompa+"\n");
			               }
			              }catch(EOFException e){sw1=0;}
						 }
			            }
			            }catch(EOFException e){sw=0;} 
					   } 
					 }
					} 
					     
       	 	  	} catch(IOException ioe){};
       	 	  	break;
       	 	  	
       	 	  case 2:
       	 	  
       	 	  ps("Digite el codigo del medico que desea consultar: ");
       	 	  codtem=leerCadena();
       	 	  
       	 	  DataInputStream datomed=null;
			  datomed=new DataInputStream (new FileInputStream("C:\\datomed.txt"));
			  
			  sw1=1;
	                 while (sw1!=0)
	                 { try
	                  {
	                   codm=datomed.readUTF();
	                   nomm=datomed.readUTF();
	                   espm=datomed.readUTF();
	                  
					 
					 if (codm.equals(codtem)) //compara el codigo digitado
					                          //con el codigo del medico de la
					                          //tabla "datomed" 
					 {
					 	ps("El medico " +nomm+" trata las siguientes enfermedades:"+"\n");
					 	
					 	DataInputStream situpac=null;
			            situpac=new DataInputStream (new FileInputStream("C:\\situpac.txt"));
			            
			             sw=1;
		                 while (sw!=0)
		                 { try
		                  {
		                   codp=situpac.readUTF();
		                   codme=situpac.readUTF();
		                   enfp=situpac.readUTF();
		                   
						if(codtem.equals(codme))   //compara el codigo del medico
					                             // de la tabla "datomed"
					                             //con el codigo del medico en la 
					                             //tabla "situpac" 
						
							{
								ps(">>>> "+enfp+"\n");
							}  
					   }catch(EOFException e){sw=0;} 
				    }	 
				   }
				   }catch(EOFException e){sw1=0;} 
			      }
       	 	  break;
       	 	 }
       	 	
       	 }while (op2!=3);
       	
       	}
       }
     }while (op1!=3);
     	
    }
}
 