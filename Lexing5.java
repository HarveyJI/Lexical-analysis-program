package lexicalAnalysis;
//https://blog.csdn.net/weixin_43340697/article/details/120437084
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexing5 {
	
	public static ArrayList<String> readFile() {      //读入文件
	    Scanner in =new Scanner (System.in);
	    
	    ArrayList<String> alines=new ArrayList<>();
		BufferedReader fis;
		
		File path=new File("D:\\作业\\大三下学期\\编译原理");
		File file_input=new File(path,"pl0_1.txt");
		try{
			fis=new BufferedReader(new FileReader(file_input));
			String aline;
			while((aline=fis.readLine())!=null){
				alines.add(aline.trim()); 	         
			}
			fis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return alines;
	}
	
	public static String isKeyword(String word) {       //判断是否是关键字,并返回关键字
			String keyword[]= {"begin","end","if","then","while","write","read","do","call","const","var","procedure","odd","nul"};   
			for(int i=0;i<keyword.length;i++) {
				if(word.equals(keyword[i])) {
					return keyword[i];
				}
			}
			return null;
	}
	
	public static String isDelimiter(char word) {      //判断是不是分隔符,并返回分隔符种类
		String delimiter_category[]= {"lparen","rparen","comma","semicolon","period"};
		char delimiter_value[]= {'(',')',',',';','.'};
		
		for(int i=0;i<delimiter_value.length;i++) {
			if(word==delimiter_value[i]) {
				return delimiter_category[i];
			}
		}
		return null;
}
	
	public static String isOperator(String word) {      ////判断是不是操作符,并返回操作符种类
		String Operator_category[]= {"plus","minus","times","slash","eql","neq","lss","leq", "gtr","geq","becomes"};
		String Operator_value[]= {"+","-","*","/","=","#","<","<=",">",">=",":="};
		for(int i=0;i<Operator_value.length;i++) {
			if(word.equals(Operator_value[i])) {
				return Operator_category[i];
			}
		}
		return null;
}
	
	public static ArrayList<Lexer> lexing(ArrayList<String> alines) {
		ArrayList<Lexer> result=new ArrayList();
		
		for(String aline:alines) {
			int i=0;
			while(i<aline.length()) {
				String word="";
					while(i<aline.length()&&Character.isLetter(aline.charAt(i))) {   //生成单词串
						word+=aline.charAt(i++);
					}
					if(word!="") {
						if(isKeyword(word)!=null){         //判断单词是关键字还是标识符
						result.add(new Lexer("",word));
						}else{
						result.add(new Lexer("ident",word));
						}
						word="";
					}

					while(i<aline.length()&&Character.isDigit(aline.charAt(i))) {    //生成数字串
						word+=aline.charAt(i++);
					}
					if(word!="") {
						result.add(new Lexer("number",word));
						word="";
					}
					
			
					while(i<aline.length()&&!Character.isLetter(aline.charAt(i))&&!Character.isDigit(aline.charAt(i))
							&&aline.charAt(i)!=' '&&isDelimiter(aline.charAt(i))==null) {    //生成运算符串
	                  word+=aline.charAt(i++);	
	                  
					}
					if(word!="") {
						if(isOperator(word)!=null){
							result.add(new Lexer(isOperator(word),word));
							}
						else {
							result.add(new Lexer("nul",word));
	
						}
						word="";
					}
					
					if(i<aline.length()&&isDelimiter(aline.charAt(i))!=null) {     //分隔符直接判断
						  word+=aline.charAt(i++);	
						  result.add(new Lexer(isDelimiter(word.charAt(0)),word));
					}
					
					if(i<aline.length()&&aline.charAt(i)==' ') {   //空字符跳过
						i++;
					}
					
			
					
			}
		}	
		return result;
		
	}
	
	public static void writeFile(ArrayList<Lexer> result){         //写入文件
	    Scanner in =new Scanner (System.in);
		BufferedWriter fos;
		File path=new File("D:\\作业\\大三下学期\\编译原理");
		File file_input=new File(path,"pl0.txt");
		File file_output=new File(path,"token_1.txt");
		try{
			
           fos=new BufferedWriter(new FileWriter(file_output));
			for(Lexer r:result) {
				if(r.getCategory()=="") {
					fos.write("("+r.getValue()+")");
					fos.write("\n");
				}else {
					fos.write("("+r.getCategory()+","+r.getValue()+")");
					fos.write("\n");
				}
			}
			fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	
	
	
	public static void main(String[] args) {
		ArrayList<Lexer> result=new ArrayList();
		
		result=lexing(readFile());    //读入文件并分析
		for(Lexer r:result) {
			if(r.getCategory()=="") {
				System.out.println("("+r.getValue()+")");
			
			}else {
				System.out.println("("+r.getCategory()+","+r.getValue()+")");
			
			}
		}
		writeFile(result);   //写入文件
	
}
}

