import java.util.Stack;

public class Main {
	
	/**
	 * 
	 * @param infix : 中缀表达式字符串
	 * @return :StringBuffer 转换成的后缀表达式
	 */
	public static StringBuffer toPostfix(String infix) {
		int len = infix.length();
		Stack<String> stack = new Stack<String>(); //运算符栈，顺序栈
		StringBuffer postfix = new StringBuffer(len * 2); //后缀表达式字符串
		int i = 0;
		while(i < len) { //遍历中缀表达式
			char ch = infix.charAt(i); //获得一个字符
			
			switch(ch) { //处理获得的字符
			case '*': //遇到 - 运算符
				while(!stack.isEmpty())  //与栈顶运算符比较
					postfix.append(stack.pop()); //出栈运算符 添加到后缀表达式串
				stack.push(ch + ""); //当前运算符入栈
				i ++;
				break;
				
			case '-':
				while(!stack.isEmpty() && stack.peek().equals("-")) 
					postfix.append(stack.pop());  //栈顶优先级高的运算符出栈
				stack.push(ch + "");
				i ++;
				break;
				
			default: //遇到数字，添加到后缀表达式
				while(i<len && ch>='0' && ch <='9') {
					postfix.append(ch);
					i ++;
					if(i < len)
						ch = infix.charAt(i);
				}
				postfix.append(" "); //作为分隔
				break;
			}
		}
		while(!stack.isEmpty()) { //后面有符号才能促使前面的符号出栈，最后一个的后面没有符号了，所以手动弹出
			postfix.append(stack.pop());
		}
		return postfix;
	}
	/**
	 * 
	 * @param postfix: 后缀表达式（StringBuffer）
	 * @return :后缀表达式的结果值
	 */
	public static int toValue(StringBuffer postfix) {
		Stack<Integer> stack = new Stack<Integer>();
		int len = postfix.length();
		int i = 0;
		while(i < len) {
			char ch = postfix.charAt(i);
			int int1,int2; //都是二目运算，所以两个操作数
			int value = 0;
			
			switch(ch) {
			case '-':
				int1 = stack.pop();
				int2 = stack.pop();
				stack.push(int2 - int1);
				i ++;
				break;
			
			case '*':
				int1 = stack.pop();
				int2 = stack.pop();
				stack.push(int1 * int2);
				i ++;
				break;
				
			case ' ':
				i ++;
				break;
				
			default: //遇到数字的情况
				while(i<len && ch>='0' && ch<='9') {
					value = value*10 + (ch-'0');
					i ++;
					if(i < len) { //有下一个
						ch = postfix.charAt(i);
					}
				}
				stack.push(value);
				break;
			}
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		String a = "12*3-12*2";
		System.out.println(a);
		StringBuffer b = toPostfix(a);
		System.out.println(b);
		System.out.println(toValue(b));
		
		a = "3*5*6-2-1*3";
		System.out.println(a);
		b = toPostfix(a);
		System.out.println(b);
		System.out.println(toValue(b));
		
		a = "2-1-0*3-1*5-3";
		System.out.println(a);
		b = toPostfix(a);
		System.out.println(b);
		System.out.println(toValue(b));
	}

}
