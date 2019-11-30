import java.util.Stack;

public class Main {
	
	/**
	 * 
	 * @param infix : ��׺���ʽ�ַ���
	 * @return :StringBuffer ת���ɵĺ�׺���ʽ
	 */
	public static StringBuffer toPostfix(String infix) {
		int len = infix.length();
		Stack<String> stack = new Stack<String>(); //�����ջ��˳��ջ
		StringBuffer postfix = new StringBuffer(len * 2); //��׺���ʽ�ַ���
		int i = 0;
		while(i < len) { //������׺���ʽ
			char ch = infix.charAt(i); //���һ���ַ�
			
			switch(ch) { //�����õ��ַ�
			case '*': //���� - �����
				while(!stack.isEmpty())  //��ջ��������Ƚ�
					postfix.append(stack.pop()); //��ջ����� ��ӵ���׺���ʽ��
				stack.push(ch + ""); //��ǰ�������ջ
				i ++;
				break;
				
			case '-':
				while(!stack.isEmpty() && stack.peek().equals("-")) 
					postfix.append(stack.pop());  //ջ�����ȼ��ߵ��������ջ
				stack.push(ch + "");
				i ++;
				break;
				
			default: //�������֣���ӵ���׺���ʽ
				while(i<len && ch>='0' && ch <='9') {
					postfix.append(ch);
					i ++;
					if(i < len)
						ch = infix.charAt(i);
				}
				postfix.append(" "); //��Ϊ�ָ�
				break;
			}
		}
		while(!stack.isEmpty()) { //�����з��Ų��ܴ�ʹǰ��ķ��ų�ջ�����һ���ĺ���û�з����ˣ������ֶ�����
			postfix.append(stack.pop());
		}
		return postfix;
	}
	/**
	 * 
	 * @param postfix: ��׺���ʽ��StringBuffer��
	 * @return :��׺���ʽ�Ľ��ֵ
	 */
	public static int toValue(StringBuffer postfix) {
		Stack<Integer> stack = new Stack<Integer>();
		int len = postfix.length();
		int i = 0;
		while(i < len) {
			char ch = postfix.charAt(i);
			int int1,int2; //���Ƕ�Ŀ���㣬��������������
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
				
			default: //�������ֵ����
				while(i<len && ch>='0' && ch<='9') {
					value = value*10 + (ch-'0');
					i ++;
					if(i < len) { //����һ��
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
