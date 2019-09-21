import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

public class ThroughputCounter {
String Portnameurl="";
String myUrl="";
String results="";
String results1="";
private JFrame frame;
URL url = null;
BufferedReader reader = null;
StringBuilder stringBuilder;
//static String[] yourArray = { "1111", "1112", "1113", "1114", "1115","1119", "1120", "1121", "2222", "2223", "2224", "2227", "3333","5656" };
static String[] yourArray = { "1111", "1112", "1113"};
static String PortNo;
static BufferedWriter bw = null;
static FileWriter fw = null;
int x = yourArray.length;
String temp = "";
String temp1 = "";
private static final String FILENAME = "D:\\filename.txt";

JTable table;
//JScrollPane pane;
//private JTable table_1;
//ReadTextFile t= new ReadTextFile();

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
ThroughputCounter window = new ThroughputCounter();
window.frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

/**
* Create the application.
*/
public ThroughputCounter() {
initialize();
}
public void columnheader(){
/*try {
	//t.readFile("D:\\Sample.txt");
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
*/

}

/**
* Initialize the contents of the frame.
*/
private void initialize() {
frame = new JFrame();
frame.setBounds(100, 100, 450, 300);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
JButton btnNewButton = new JButton("Refresh");
btnNewButton.setBackground(Color.GREEN);
btnNewButton.setForeground(Color.DARK_GRAY);
btnNewButton.setBounds(345, 0, 89, 25);
btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
// columnheader();
//Reading from port
try {
fw = new FileWriter(FILENAME);
} catch (IOException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
bw = new BufferedWriter(fw);
for (int i = 0; i < yourArray.length; i++) {
	
PortNo = yourArray[i];
try {
//http://localhost:2222/data/get_page/?_search=false&nd=1500017217009&rows=20&page=1&sidx=&sord=asc
//http://localhost:4000/data/get_page/?_search=false&nd=1500016848112&rows=20&page=1&sidx=&sord=asc
Portnameurl = "http://localhost:" + PortNo + "/data/get_settings?_=1533197069346";

myUrl = "http://localhost:" + PortNo + "/data/get_page/?_search=false&nd=1533197069346&rows=20&page=1&sidx=&sord=asc";
// if your url can contain weird characters you will want to encode it here, something like this:
// myUrl = URLEncoder.encode(myUrl, "UTF-8");

//String results = doHttpUrlConnectionAction(myUrl);

try {
// create the HttpURLConnection
url = new URL(myUrl);
HttpURLConnection connection = (HttpURLConnection) url.openConnection();



// just want to do an HTTP GET here
connection.setRequestMethod("GET");

// uncomment this if you want to write output to this url
// connection.setDoOutput(true);

// give it 15 seconds to respond
connection.setReadTimeout(60 * 1000);
connection.connect();

// read the output from the server
reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
stringBuilder = new StringBuilder();

String line = null;
while ((line = reader.readLine()) != null) {
stringBuilder.append(line + "\n");
}
results=stringBuilder.toString();
System.out.println(results);
} catch (Exception e) {
e.printStackTrace();
throw e;
} finally {

if (reader != null) {
try {
reader.close();
} catch (IOException ioe) {
ioe.printStackTrace();
}
}
}
//String results1 = doHttpUrlConnectionAction(Portnameurl);
try {
// create the HttpURLConnection
url = new URL(Portnameurl);
HttpURLConnection connection = (HttpURLConnection) url.openConnection();


connection.setRequestMethod("GET");


connection.setReadTimeout(60 * 1000);
connection.connect();

reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
stringBuilder = new StringBuilder();

String line = null;

while ((line = reader.readLine()) != null) {
	
stringBuilder.append(line + "\n");
}
results1=stringBuilder.toString();
} catch (Exception e) {
e.printStackTrace();
throw e;
} finally {

if (reader != null) {
try {
reader.close();
} catch (IOException ioe) {
ioe.printStackTrace();
}
}
}
String pattern2 = "records\":";
String pattern3 = ",\"page";
String pattern4 = "name\":\"";
String pattern5 = "\",\"mode";
CharSequence strLine = results;
CharSequence strLine1 = results1;
java.util.regex.Matcher m1 = Pattern.compile(Pattern.quote(pattern2) + "(.*?)" + Pattern.quote(pattern3)).matcher(strLine);
while (m1.find()) {
temp = m1.group(1).toString();
System.out.println(temp);
}
java.util.regex.Matcher m2 = Pattern.compile(Pattern.quote(pattern4) + "(.*?)" + Pattern.quote(pattern5)).matcher(strLine1);
while (m2.find()) {
temp1 = m2.group(1).toString();
System.out.println(temp1);
}

try {
bw.write(temp1);

bw.write(System.getProperty("line.separator"));
bw.write(PortNo);

bw.write(System.getProperty("line.separator"));

bw.write(temp);
bw.write(System.getProperty("line.separator"));

System.out.println("Done");

} catch (IOException e) {
e.printStackTrace();
}
} catch (Exception e) {
// deal with the exception in your "controller"
}



/*
* try { Runtime.getRuntime().exec(new String[] { "cmd",
* "/c","start notepad " + FILENAME + "" }); } catch (IOException e) {
* e.printStackTrace(); }
*/
}

try {
if (bw != null)
bw.close();

if (fw != null)
fw.close();
} catch (IOException ex) {
ex.printStackTrace();
}
//
int i=0, j=0,m=0,n=0;
//public String[][] multi = new String[2][];
String[][] multi = new String[][]{
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""},
{"","",""}
};
String[] multi1 = new String[100];
try {

File f = new File(FILENAME);

BufferedReader b = new BufferedReader(new FileReader(f));

String readLine = "";

System.out.println("Reading file using Buffered Reader");

while ((readLine = b.readLine()) != null) {
System.out.println(readLine);
multi1[i++]=readLine;
}
i=0;
// System.out.println("heelo");
for(m=0;m<yourArray.length;m++)
{
for(n=0;n<3;n++)
{
// System.out.println(multi[i]);
multi[m][n]=multi1[i];
i++;
}
}

} catch (IOException e) {
e.printStackTrace();
}
String[] columnheaders={"PortContent","PortNo","Data"};
JTable table_1 = new JTable(multi,columnheaders);
//System.out.println(t.multi);
table_1.setPreferredScrollableViewportSize(new Dimension(500,80));
JScrollPane pane = new JScrollPane(table_1);
frame.getContentPane().add(pane,BorderLayout.CENTER);
JScrollPane scrollPane = new JScrollPane();
scrollPane.setBounds(10, 52, 414, 199);
frame.getContentPane().add(scrollPane);
scrollPane.setViewportView(table_1);
table_1.setBackground(Color.WHITE);
table_1.setForeground(Color.BLACK);
/* 
//
DefaultTableModel model = new DefaultTableModel(t.multi,columnheaders);
table_1 = new JTable(model) {
@Override
public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
int columnIndex) {
JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex); 
// System.out.println(getValueAt(rowIndex, 0).toString());
if(Integer.parseUnsignedInt((String) getValueAt(rowIndex, 0))<15 && columnIndex == 2) {
component.setBackground(Color.RED);
} else if(getValueAt(rowIndex, 1).toString().equalsIgnoreCase("j2ee") && columnIndex == 1){
component.setBackground(Color.GREEN);
}
return component;
}
};

*/

//
} 
});
frame.getContentPane().setLayout(null);
//frame.getContentPane().add(btnNewButton,pane);
frame.getContentPane().add(btnNewButton);
JLabel lblCaaVtsAdmin = new JLabel(" VTS Admin View");
lblCaaVtsAdmin.setFont(new Font("Verdana", Font.BOLD, 16));
lblCaaVtsAdmin.setBounds(28, 5, 294, 20);
frame.getContentPane().add(lblCaaVtsAdmin);
}
}
