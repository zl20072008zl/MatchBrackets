
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class MatchBrackets {

	// Map uses the right bracket as key and the left bracket as the value.
    private Map<Character, Character> map = null;
    private static final char L_PAREN    = '(';
    private static final char R_PAREN    = ')';
    private static final char L_BRACE    = '{';
    private static final char R_BRACE    = '}';
    private static final char L_BRACKET  = '[';
    private static final char R_BRACKET  = ']';

    public MatchBrackets()
    {
    	map = new HashMap<Character, Character>();
    	map.put(R_PAREN, L_PAREN);
    	map.put(R_BRACE, L_BRACE);
    	map.put(R_BRACKET, L_BRACKET);
    }

    public static void main(String[] args) {
    	
    	MatchBrackets obj = new MatchBrackets();
    	//Use Map to math brackets
    	System.out.println("=======Use Map to math brackets=======");
    	System.out.println(obj.isMatchedByMap("3")); //true
        System.out.println(obj.isMatchedByMap("(){}[]")); //true
        System.out.println(obj.isMatchedByMap("[12345]--{2222222}")); //true
        System.out.println(obj.isMatchedByMap("{}[[[(]")); //false
        System.out.println(obj.isMatchedByMap("{{{{}}")); //false
        
        //To check brackets without Map
        System.out.println("=======To check brackets without Map=======");
        System.out.println(obj.isMathed("3")); //true
        System.out.println(obj.isMathed("(){}[]")); //true
        System.out.println(obj.isMathed("[12345]--{2222222}")); //true
        System.out.println(obj.isMathed("{}[[[(]")); //false
        System.out.println(obj.isMathed("{{{{}}")); //false
        
        boolean continueOrNot = true;
        Scanner scanner=new Scanner(System.in);
        while(continueOrNot) {
        	System.out.println("=======Please put some string to test the both functions=======");
        	System.out.println("=======Put exit to stop it=======");
        	String str=scanner.nextLine();
        	if(str.equals("exit"))
        		continueOrNot = false;
        	else {
        		System.out.print("Use Map to math brackets: ");
        		System.out.println(obj.isMatchedByMap(str));
        		System.out.print("To check brackets without Map: ");
        		System.out.println(obj.isMathed(str));
        		
        	}
        }
	}
    
    /**
     * Use Map to math brackets
     * @param s
     * @return
     */
    public boolean isMatchedByMap(String str)
    {
        Stack<Character> sc = new Stack<Character>();
        for (int i = 0; i < str.length(); i++)
        {
            Character ch = str.charAt(i);
            if (map.containsValue(ch))// If it is left bracket, put it in the stack.
            {
                sc.push(ch);
            } else if (map.containsKey(ch)) // If it is right bracket
            {
                if (sc.empty()) // The stack is empty, and the header has no characters matching the right brackets.
                {
                    return false;
                }
                if (sc.peek() == map.get(ch)) // The stack is not empty, and the header character matches the right bracket.
                {
                    sc.pop();
                } else 
                { 
                    return false; // The stack is not empty, and the header character does not match the right bracket.
                }
            }
        }
        return sc.empty() ? true : false;
    }

    /**
     * To check brackets without Map
     * @param s
     * @return
     */
    public boolean isMathed(String s){
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==L_PAREN || s.charAt(i)==L_BRACKET || s.charAt(i)==L_BRACE)
                stack.push(s.charAt(i)); // If it is a left parenthesis, the stack is pushed into the stack.
            else if(s.charAt(i)==R_PAREN){
                if(stack.isEmpty())    return false;   // Check whether the stack is empty.
                if(stack.pop()!=L_PAREN)   return false;   
            }
            else if(s.charAt(i)==R_BRACKET){
                if(stack.isEmpty())    return false;
                if(stack.pop()!=L_BRACKET)   return false;    
            }
            else if(s.charAt(i)==R_BRACE){
                if(stack.isEmpty())    return false;
                if(stack.pop()!=L_BRACE)   return false;        
            }
        }
        return stack.isEmpty(); // At this point, there should be no left brackets in the stack.
    }
}
