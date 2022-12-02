import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String[][] dataarray2 =new String[100][6];
    public static ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<String>> courselist1 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> courselist2 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> courselist3 = new ArrayList<ArrayList<String>>();


       //Read notes from a input text file.
        readnotes();

        //list each course in a separate file
        String Course1= "Math";
        String Course2= "English";
        String Course3= "Computer";

        //System.out.println(studentlist);

        String coursename;
        int j1=0;
        int j2=0;
        int j3=0;

        ArrayList<String> studentlist = new ArrayList<>();
        //System.out.println(list.size());
        for (int i=1; i< list.size() ; i++ ) {
            studentlist = list.get(i);
            //System.out.println(studentlist);
            coursename = studentlist.get(3);
            //System.out.println(coursename);
            switch (coursename)
            {
                case "Math":
                    //System.out.println(j1+"Math"+studentlist);
                    courselist1.add(j1, studentlist);
                    j1++;
                    break;
                case "English":
                    courselist2.add(j2, studentlist);
                    //System.out.println(j2+"English"+studentlist);
                    j2++;
                    break;
                case "Computer":
                    //System.out.println(j3+"Computer"+studentlist);
                    courselist3.add(j3, studentlist);
                    j3++;
                    break;
                case "default":
                    System.out.println("Invalid input line, please check your input file");
                    break;
            }
        }


        calculateGrades(courselist1);
        calculateGrades(courselist2);
        calculateGrades(courselist3);

    }

    private static void calculateGrades(ArrayList<ArrayList<String>> courselist) throws Exception {
        Integer[] courseAve =new Integer[courselist.size()];
        String[][] gradeCourse = new String[courselist.size()][8];
        ArrayList<String> studentlist =new ArrayList<>();
        int Average;
        int Midterm;
        int Final;
        int Course_mean;
        int noteincreament;
        int total=0;
        int studentAve;
//***************************************************************************************************
//***************************************************************************************************
        for (int i=0; i< courselist.size(); i++ ) {
            studentlist = courselist.get(i);
            //***********************************
            gradeCourse[i][0]=studentlist.get(0);
            gradeCourse[i][1]=studentlist.get(1);
            gradeCourse[i][2]=studentlist.get(2);
            gradeCourse[i][3]=studentlist.get(3);
            gradeCourse[i][4]=studentlist.get(4);
            gradeCourse[i][5]=studentlist.get(5);
            //***********************************
            Midterm = Integer.parseInt(studentlist.get(4));
            Final = Integer.parseInt(studentlist.get(5));
            Average = (Midterm + Final) / 2;
            total = total + Average;
            courseAve[i] = Average;
            //***********************************
            gradeCourse[i][6]=Integer.toString(Average);
            //***********************************
        }
        Course_mean=total/courselist.size();
        noteincreament=(100-Course_mean)/6; //AA,BA,BB,CB,CC,DC ve DD
        int Grade1=Course_mean+noteincreament;
        int Grade2=Grade1+noteincreament;
        int Grade3=Grade2+noteincreament;
        int Grade4=Grade3+noteincreament;
        int Grade5=Grade4+noteincreament;
        //int Grade6=Grade5+noteincreament; //100

        for (int i=0; i< courselist.size() ; i++ )
            {
            studentAve =courseAve[i];
            if (studentAve <= Course_mean)
            {
                gradeCourse[i][7]="DD";
           }else if (studentAve > Course_mean && studentAve <= Grade1)
            {
                gradeCourse[i][7]="DC";
            }else if (studentAve > Grade1 && studentAve <= Grade2)
            {
                gradeCourse[i][7]="CC";
            }else if (studentAve > Grade2 && studentAve <= Grade3)
            {
                gradeCourse[i][7]="CB";
            }else if (studentAve > Grade3 && studentAve <= Grade4)
            {
                gradeCourse[i][7]="BB";
            }else if (studentAve > Grade4 && studentAve <= Grade5)
            {
                gradeCourse[i][7]="BA";
            }else if (studentAve > Grade5 && studentAve <= 100)
            {
                gradeCourse[i][7]="AA";
            }
        }
        writeGrades(gradeCourse);
    }

    private static void writeGrades(String[][] gradeCourse) throws Exception {
        String coursename =gradeCourse[0][3];
        File file = new File("C:/Users/S/Desktop/TESTER-Weeks_1-7/GradeCal/GradCal/src/"+coursename+".txt");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        String title= "Name        Surname     StudentID    Average     Grade";
        printWriter.println(title);
        for (int i=0; i< gradeCourse.length ; i++ )
        {
            for (int j=0; j< 3; j++ ) {
                printWriter.print(gradeCourse[i][j]+"\t\t"); // Name Surname No
            }
            printWriter.print(gradeCourse[i][6]+"\t\t");    // Average
            printWriter.println(gradeCourse[i][7]);         // Grade
        }
        printWriter.close();
    }


    public static void readnotes () throws Exception
    {
        File file = new File("C:/Users/S/Desktop/TESTER-Weeks_1-7/GradeCal/GradCal/src/Notes.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String[] dataarray1 =new String[6];
        int j = -1;
        String dataline = bufferedReader.readLine();
        while (dataline != null)
        {j++;
            dataarray1 = dataline.split("\\s+");
            ArrayList<String> list1 = new ArrayList<>();
                for (int i=0; i< dataarray1.length ; i++ )
                {
                    list1.add(dataarray1[i]);
                }
                 list.add(j,list1);
            dataline = bufferedReader.readLine();
        }
        bufferedReader.close();
    }
}


