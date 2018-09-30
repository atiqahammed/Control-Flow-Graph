package forspl;
//import forspl.LocalVariableCounting;
//import forspl.BarChart;

public aspect ClassVariableAndParameterCounting {
	
	//int countInt=0, countString=0, countChar=0;

	//==>
		pointcut CountInt():
			set(static int forspl.ReadIt.*)||set( int forspl.ReadIt.*) ;
	//==>	
		pointcut CountString():
			set( String forspl.ReadIt.*);
	//==>
		pointcut CountChar():
			set( char  forspl.ReadIt.*);
		
		pointcut CountFloat():
			set(float forspl.ReadIt.*);
		
		pointcut CountDouble():
			set(double forspl.ReadIt.*);
		
		pointcut CountBoolean():
			set(boolean forspl.ReadIt.*);
		
		//====================================
		
		after(): CountInt(){
			LocalVariableCounting.countInt++;
			System.out.println("Integer class variable found.");
			
			BarChart.main(new String[]{});
		}

		after(): CountString(){
			LocalVariableCounting.countString++;
			System.out.println("String class variable found.");
			
			BarChart.main(new String[]{});
		}
		
		
		after(): CountChar(){
			LocalVariableCounting.countChar++;
			System.out.println("Char class variable found.");
			
			BarChart.main(new String[]{});
		}
		
		
		
		after(): CountDouble(){
			LocalVariableCounting.countDouble++;
			System.out.println("Double class variable found.");
			
			BarChart.main(new String[]{});
		}

		after(): CountFloat(){
			LocalVariableCounting.countFloat++;
			System.out.println("Float class variable found.");
			
			BarChart.main(new String[]{});
		}
		
		
		after(): CountBoolean(){
			LocalVariableCounting.countBoolean++;
			System.out.println("Boolean class variable found.");
			
			BarChart.main(new String[]{});
		}
		
	//==>
		
	//==>
		
		pointcut MethodInt():
			call(* forspl.ReadIt.*(int))||call(* forspl.ReadIt.*(int,*))
			|| call(* forspl.ReadIt.*(*,int,*)) ||call(* forspl.ReadIt.*(*,int));
		
		
		
		pointcut MethodDouble():
			call(* ReadIt.*(double ))||call(* ReadIt.*(double ,*))
			|| call(* ReadIt.*(*,double ,*));
		
		
		
		pointcut MethodFloat():
			call(* forspl.ReadIt.*(float))||call(* forspl.ReadIt.*(float,*))
			|| call(* forspl.ReadIt.*(*,float,*));
				
			
				
		pointcut MethodChar():
			call(* forspl.ReadIt.*(char))||call(* forspl.ReadIt.*(char,*))
			|| call(* forspl.ReadIt.*(*,char,*));
				
				
				//==>
		pointcut MethodBoolean():
			call(* forspl.ReadIt.*(boolean))||call(* forspl.ReadIt.*(boolean,*))
			|| call(* forspl.ReadIt.*(*,boolean,*));
				
				
				
		pointcut MethodString():
			call(* forspl.ReadIt.*(char))||call(* forspl.ReadIt.*(char,*))
			|| call(* forspl.ReadIt.*(*,char,*));
		
		
		before(): MethodDouble(){
			LocalVariableCounting.countDouble++;
			System.out.println("Double parameter found.");
			
			BarChart.main(new String[]{});
		}
				
		after(): MethodBoolean(){
			LocalVariableCounting.countBoolean++;
			System.out.println("Boolean parameter found.");
			
			BarChart.main(new String[]{});
		}
				
		after(): MethodChar(){
			LocalVariableCounting.countChar++;
			System.out.println("Char parameter found.");
			
			BarChart.main(new String[]{});
		}
		
		after(): MethodFloat(){
			LocalVariableCounting.countFloat++;
			System.out.println("Float parameter found.");
			
			BarChart.main(new String[]{});
		}
		
		
		after(): MethodInt(){
			LocalVariableCounting.countInt++;
			System.out.println("Integer parameter found.");
			
			BarChart.main(new String[]{});
		}
		
		after(): MethodString(){
			LocalVariableCounting.countString++;
			System.out.println("String parameter found.");
			
			BarChart.main(new String[]{});
		}

}
