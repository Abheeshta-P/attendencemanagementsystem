//student attendence management
package oops;
import java.util.InputMismatchException;
import java.util.Scanner;
abstract class person{
	private String name;
	private String id;
	person(String name,String id) {
		this.name=name;
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}
	//abstract void display(attendenceSystem ams);
}

class student extends person{
	private String subjectCode[]=new String[8];
	private int attendence[]=new int[8];
	private int tot_attended[]=new int[8];
	student(String name,String id) {
		super(name,id);
		for(int i=0;i<8;i++) {
			subjectCode[i]="BCS30"+(i+1);
		}
		for(int i=0;i<8;i++) {
			attendence[i]=100;
		}
		for(int i=0;i<8;i++) {
			tot_attended[i]=0;
		}
	}
	
	public String[] getSubjectCode(){
		return subjectCode ;
	}
	public int[] getTotalAttendence(){
		return attendence ;
	}
	public int[] getAttendence(){
		return tot_attended ;
	}
	void display() {//ams taken
		System.out.println("Subject\t\tAttendence");
		for(int i=0;i<8;i++) {
			System.out.println(this.subjectCode[i]+"\t\t"+this.attendence[i]);
		}
	}
}

class subjectTeacher extends person{
	private String department;
	subjectTeacher(String name,String id,String department) {
		super(name,id);
		this.department=department;
	}
	private String subjectCode;
	private int tot_Cls=0;
	public void setSubjectCode(String subjectCode) {
		this.subjectCode=subjectCode;
	}
	public void updateAttendence(attendenceSystem ams) {
		int no_Cls,cls_Attended;
		Scanner sc=new Scanner(System.in);
		try {
		System.out.print("Enter the number of classes you conducted in a week : ");
		no_Cls=sc.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("Enter a integer value please\n");
			sc.next();
			System.out.print("Enter the number of classes you conducted in a week : ");
			no_Cls=sc.nextInt();
		}
		if (no_Cls == 0) {
	        System.out.println("Number of classes cannot be zero");
	        System.out.print("Enter the number of classes you conducted in a week : ");
			no_Cls=sc.nextInt();
	    }
		tot_Cls+=no_Cls;
		for(student s:ams.getStudents()) {
			try {
			System.out.print("Enter the number of classes "+s.getId()+" attended in a week : ");
			cls_Attended=sc.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Enter a integer value please\n");
				sc.next();
				System.out.print("Enter the number of classes "+s.getId()+" attended in a week : ");
				cls_Attended=sc.nextInt();
			}
			 for (int i = 0; i < 8; i++) {
	                if (this.subjectCode.equals(s.getSubjectCode()[i])) {
	                    s.getTotalAttendence()[i]+=cls_Attended;
	                    double attendance_percentage = ((double) s.getTotalAttendence()[i] / tot_Cls) * 100;
	                    s.getAttendence()[i] = (int) Math.round(attendance_percentage);
	                   
	                }
	            }
		}
	}
	void display(attendenceSystem ams) {
	    System.out.println("Attendance of students in subject : " + this.subjectCode);
	    System.out.println("USN\t\tAttendance");
	    for (student s : ams.getStudents()) {
	      
	            for (int i = 0; i < 8; i++) {
	                if (this.subjectCode.equals(s.getSubjectCode()[i])) {
	                    System.out.println(s.getId() + "\t" + s.getAttendence()[i]);
	                }
	            }
	        
	    }
	}

}

class attendenceSystem{
	private student[] students;//private to public
	private subjectTeacher[] teachers;
	private int countStu=0,countTea=0,maxTea,maxStu;
	attendenceSystem(int maxStu,int maxTea){
		this.maxStu=maxStu;
		this.maxTea=maxTea;
		students=new student[maxStu];
		teachers=new subjectTeacher[maxTea];
	}
	public void addStudent(student s) {
		students[countStu++]=s;
		
	}
	public void addTeacher(subjectTeacher s) {
		teachers[countTea++]=s;
		
	}
	public student[] getStudents() {
		return students;
	}
	public void displayReport() {
		System.out.println("\nAttendence report of the class");
		System.out.print("USN\t\t");
		for(int i=0;i<8;i++) {
			System.out.print(students[0].getSubjectCode()[i]+"\t\t");
		}
		System.out.println();
		for(student s:students) {
			System.out.print(s.getId() + "\t");
			for (int i = 0; i < 8; i++) {	
				 System.out.print( s.getAttendence()[i]+"\t\t");
			}
			System.out.println();
		}
	}
	public student searchStudent(String usn) {
	    for(student s:students) {
	        if (s.getId().equals(usn)) {
	            return  s; 
	        }
	    }
	    return  null; 
	}
	public int searchSubjectCode(String subjectcode) {
		for(int i=0;i<8;i++) {
			if(students[0].getSubjectCode()[i].equals(subjectcode)) {
				return 1;
			}
		}
		System.out.println("\nSubject code is not present in the system \n");
		return 0;
	}
	public subjectTeacher searchTeacher(String id) {
	    for(subjectTeacher s:teachers) {
	        if (s.getId().equals(id)) {
	            return  s; 
	        }
	    }
	    return null; 
	}

}

public class miniproject{
	public static void main(String args[]) {
		String usn,id,view,code,st;
		int choice,ch,check;
		Scanner s=new Scanner(System.in);
		
		student s1=new student("Abheeshta P", "4CB22CS001");
		//s1.setInfo("Abheeshta P", "4CB22CS001");
		student s2=new student("Abhijna", "4CB22CS002");
		//s2.setInfo("Abhijna", "4CB22CS002");
		student s3=new student("Aditi", "4CB22CS003");
		//s3.setInfo("Aditi", "4CB22CS003");
		subjectTeacher t1=new subjectTeacher("Swathi", "PR001","Computer Science and Engineering");
		subjectTeacher t2=new subjectTeacher("Anoop", "PR002","Computer Science and Engineering");
		attendenceSystem ams=new attendenceSystem(3,2);
		ams.addStudent(s1);
		ams.addStudent(s2);
		ams.addStudent(s3);
		ams.addTeacher(t1);
		ams.addTeacher(t2);
		
		System.out.println("************STUDENT ATTENDENCE MANAGEMENT SYSTEM************");
		while(true) {
			System.out.print("\n1.Student\n2.Professor\n3.Exit\nEnter your choice : ");
			ch=s.nextInt();	
			
			switch(ch) {
			case 1:
				System.out.print("Enter your USN : ");
				usn=s.next();
				student stu=ams.searchStudent(usn);
				
				if(stu != null) {
					System.out.print("Do you wish to view your attendence in subjects? (y/n) : ");
					view=s.next();
					if(view.equals("y")||view.equals("Y")) {
						stu.display();
					}
				}
				else {
					System.out.println("Student not found!");
				}
				
			
			break;
			case 2:
				System.out.print("Enter your id : ");
				id=s.next();
				subjectTeacher teacher=ams.searchTeacher(id);
				if(teacher != null) {
					System.out.print("Are you a Subject teacher? (y/n) : ");
					st=s.next();
					if(st.equals("y")||st.equals("Y")) {
						System.out.print("Enter your subject code : ");
						code=s.next();
						check=ams.searchSubjectCode(code);
						
						while(check==0){
							System.out.print("Enter your subject code : ");
							code=s.next();
							check=ams.searchSubjectCode(code);
						}
						
						
						teacher.setSubjectCode(code);
						while(true) {
						System.out.print("\n1.Update attendence\n2.View attendence of students\n3.View class report\n4.Exit\nEnter your choice : ");
						choice=s.nextInt();
						if (choice == 4) {
							System.out.println("Returning to the main menu.");
							break;
					    }
					switch(choice) {
						case 1:
								teacher.updateAttendence(ams);
								break;
							
						case 2:
							teacher.display(ams);
							break;
						case 3:
							ams.displayReport();
							break;
						
						default:System.out.println("Invalid choice");
						}
						
					}
					}
						
					else {
						System.out.print("Do you wish to view the class report?(y/n) : ");
						view=s.next();
						if(view.equals("y")||view.equals("Y")) {
							ams.displayReport();
						}
						else {
							System.out.println("\nThank you");
						}
					}
					
				}
				else {
					System.out.println("Teacher not found!");
				}
					
				break;
			case 3: 
				System.out.println("\n***********Thank you for using the system****************");
				System.exit(0);
			default :System.out.println("Invalid choice");
			}
			
		}
		
	}
		}
