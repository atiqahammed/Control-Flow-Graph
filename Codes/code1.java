class ForLoopExample {
	
	
	public static void main(String[] args) {

        int testscore = 76;
        char grade;

        do{
        	something
        }while(jhjkh);
        
        
        System.out.println("Grade = " + grade);
    }
	
	
	public static void main(String[] args) {

        int testscore = 76;
        char grade;

        for(int i = 0; i < n; i++){
        	a = b+c;
        }
        
        
        System.out.println("Grade = " + grade);
    }
	
	
	private int getNumberOfEnviedElements(IMethod method, String targetClassName, ProjectVersion version) {
		int numberOfEnviedElements = 0;
		if(method != null) {
			ICompilationUnit iCompilationUnit = method.getCompilationUnit();
			ASTParser parser = ASTParser.newParser(ASTReader.JLS);
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			parser.setSource(iCompilationUnit);
			parser.setResolveBindings(true);
			CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(null);
			IType declaringType = method.getDeclaringType();
			TypeDeclaration typeDeclaration = (TypeDeclaration)compilationUnit.findDeclaringNode(declaringType.getKey());
			MethodDeclaration matchingMethodDeclaration = null;
			for(MethodDeclaration methodDeclaration : typeDeclaration.getMethods()) {
				IMethod resolvedMethod = (IMethod)methodDeclaration.resolveBinding().getJavaElement();
				if(resolvedMethod.isSimilar(method)) {
					matchingMethodDeclaration = methodDeclaration;
					break;
				}
			}
			if(matchingMethodDeclaration != null && matchingMethodDeclaration.getBody() != null) {
				methodCodeMap.put(version, matchingMethodDeclaration.toString());
				ASTInformationGenerator.setCurrentITypeRoot(iCompilationUnit);
				MethodBodyObject methodBody = new MethodBodyObject(matchingMethodDeclaration.getBody());
				Map<AbstractVariable, LinkedHashSet<MethodInvocationObject>> invokedMethodsThroughFields = methodBody.getInvokedMethodsThroughFields();
				for(AbstractVariable variable : invokedMethodsThroughFields.keySet()) {
					if(variable.getVariableType().equals(targetClassName) && variable instanceof PlainVariable) {
						LinkedHashSet<MethodInvocationObject> methodInvocations = invokedMethodsThroughFields.get(variable);
						numberOfEnviedElements += methodInvocations.size();
					}
				}
				Map<AbstractVariable, LinkedHashSet<MethodInvocationObject>> invokedMethodsThroughParameters = methodBody.getInvokedMethodsThroughParameters();
				for(AbstractVariable variable : invokedMethodsThroughParameters.keySet()) {
					if(variable.getVariableType().equals(targetClassName) && variable instanceof PlainVariable) {
						LinkedHashSet<MethodInvocationObject> methodInvocations = invokedMethodsThroughParameters.get(variable);
						numberOfEnviedElements += methodInvocations.size();
					}
				}
				Set<AbstractVariable> definedFieldsThroughFields = methodBody.getDefinedFieldsThroughFields();
				for(AbstractVariable variable : definedFieldsThroughFields) {
					if(variable.getVariableType().equals(targetClassName))
						numberOfEnviedElements++;
				}
				Set<AbstractVariable> usedFieldsThroughFields = methodBody.getUsedFieldsThroughFields();
				for(AbstractVariable variable : usedFieldsThroughFields) {
					if(variable.getVariableType().equals(targetClassName))
						numberOfEnviedElements++;
				}
				Set<AbstractVariable> definedFieldsThroughParameters = methodBody.getDefinedFieldsThroughParameters();
				for(AbstractVariable variable : definedFieldsThroughParameters) {
					if(variable.getVariableType().equals(targetClassName))
						numberOfEnviedElements++;
				}
				Set<AbstractVariable> usedFieldsThroughParameters = methodBody.getUsedFieldsThroughParameters();
				for(AbstractVariable variable : usedFieldsThroughParameters) {
					if(variable.getVariableType().equals(targetClassName))
						numberOfEnviedElements++;
				}
			}
		}
		return numberOfEnviedElements;
	}
    
   
    
    
    public static int minFunction(int n1, int n2) {
    	   int min;
    	   if (n1 > n2)
    	      min = n2;
    	   else
    	      min = n1;

    	   return min; 
    	}
    
    public static void main(String[] args) {
        int a = 11;
        int b = 6;
        int c = minFunction(a, b);
        System.out.println("Minimum Value = " + c);
     }
    
    
    public static void main(String[] args) {
        methodRankPoints(255.7);
     }

    public static void methodRankPoints(double points) {
        if (points >= 202.5) {
           System.out.println("Rank:A1");
        }
        else if (points >= 122.4) {
           System.out.println("Rank:A2");
        }
        else {
           System.out.println("Rank:A3");
        }
     }
}