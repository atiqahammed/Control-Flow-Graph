package AOP;

public aspect MyAspect {
	
	int countInt=0, countString=0, countChar=0;

//==>
	pointcut CountInt():
		set(static int AOPexampl.*)||set( int AOP.AOPexampl.*) ;
//==>	
	pointcut CountString():
		set( String *);
//==>
	pointcut CountChar():
		set( char *);
	
	before(): CountInt(){
		countInt++;
		System.out.println("Number of int type variable = "+countInt);
	}

	before(): CountString(){
		countString++;
		System.out.println("Number of String type variable = "+countString);
	}
	
	
	before(): CountChar(){
		countChar++;
		System.out.println("Number of char type variable = "+countChar);
	}
	
//==>
	pointcut function():
		call(void AOPexampl.*(int))||call(void AOPexampl.*(int,*));
	
	after(): function(){
		countInt++;
		//System.out.println("\n...ASPECT BEFORE! (accessing via aspect)");
		System.out.println("Number of int type variable = "+countInt);
	}
	
}
