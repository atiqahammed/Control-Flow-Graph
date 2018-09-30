package AOP;

public aspect MyAspect {
	pointcut function():
		call(void AOPexampl.method*(..));
	
	before(): function(){
		System.out.println("\n...ASPECT BEFORE! (accessing via aspect)");
	}
	after(): function(){
		System.out.println("ASPECT AFTER...! (accessing via aspect)\n");
	}

}
