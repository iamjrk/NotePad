import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;


class notepad extends JFrame implements ActionListener
{
    //variables

    //frame declaration
    JFrame f;

    //text area declaration
    JTextArea t;


    //Constructor
    //Constructor is making the GUI
    notepad()
    {
        //assigning size to jframe
        f= new JFrame("NotePad"); //initialising frame
        // "NotePad" is the Frame name like title of frame

        //initialising the text area
        t= new JTextArea();

        //Menubar declaration
        //creating a Main MenuBar
        JMenuBar menubar= new JMenuBar();

        //1. For File Menu

        //creating a file menu
        JMenu file = new JMenu("File");
        //when we click file submenu appears

        //creating the options/submenu for file
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Save");
        JMenuItem f3 = new JMenuItem("Open");
        JMenuItem f4 = new JMenuItem("Print");

        //adding actionListeners to each of the options
        f1.addActionListener(this); //"this" because it will work for only that option i.e for New
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //menu is created  but not combined with main menu

        //adding the options to the file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //2. For Edit Menu

        //creating a edit menu
        JMenu edit = new JMenu("Edit");

        //creating the options/submenu for file
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        //adding actionListeners to each of the options
        e1.addActionListener(this); //"this" because it will work for only that option i.e for Cut
        e2.addActionListener(this);
        e3.addActionListener(this);


        //menu is created  but not combined with main menu

        //adding to the main menu( Under Edit )

        //adding the options to the edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        //3.Adding Close as a button
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        //Adding File , Edit & Close into the Main MenuBar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);

        //created menubar(which include: file,edit,close)
        //there is no connection between jframe,textarea & menubar

        //creating connection

        //adding menubar to the frame
        f.setJMenuBar(menubar); //swing function

        //adding text area to the frame
        f.add(t);

        //set the size of the frame
        f.setSize(1280,720); //resolution in pixel

        f.show(); /* makes the frame visible to the user */
        //or
        //f.setVisible(true); //bydefault the frame is set to not visible i.e false
        //f.show or f.setVisible(true) both works the same

    }


    //to add the functionalities;
    public void actionPerformed(ActionEvent e)
    {
        //extracting the ActionEvent command in the form of String
        String s = e.getActionCommand();
        switch (s)
        {
            case "New": //the user want to open a blank text area
                t.setText(""); //its a function in Swing that
                // sets the text area to whatever you have added in the argument
                break;
            case "Save":
                JFileChooser j = new JFileChooser("C:\\Users\\Sting\\Documents"); //it is function of swing
                //spawn the dialog box
                int r = j.showSaveDialog(null);
                //null ,to show it in a single/same frame
                // r will store 0/1, 0 means user clicked "save",1 means user haven't.
                //r contains status of dialogbox
                if(r==0)
                {
                    //j stores only the path/pointer  ,
                    // getSelectedFile(): get me the file ,
                    // getAbsolutePath(): this get me total path to the file
                    //declare a file object and store file location
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter fw = new FileWriter(fi);
                        BufferedWriter bw= new BufferedWriter(fw);
                        bw.write(t.getText());

                        bw.flush();
                        bw.close();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(f,"the user has cancelled the operation");
                }

                break;
            case "Open":
                JFileChooser ji = new JFileChooser("C:\\Users\\Sting\\Documents"); //it is function of swing
                // we have a set starting directory path
                //spawn the dialog box
                int ri = ji.showOpenDialog(null);
                //null ,it is in a single/same frame we don't have multiple frames working.
                if(ri==0)
                {
                    //declare a file object and store file location
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());

                    try {
                        FileReader fr = new FileReader(fi);
                        BufferedReader br= new BufferedReader(fr);
                        String s1="",s2="";
                        s1=br.readLine();
                        while ((s2= br.readLine())!=null)
                        {
                            s1=s1+"\n"+s2;
                        }
                        //all the content of the file copied into s1
                        t.setText(s1);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(f,"the user has cancelled the operation");
                }
                break;
            case "Print":
                try
                {
                    /*system inbuilt function in swing*/
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;
        }
    }

}
class Main
{
    public static void main(String args[])
    {
        notepad n = new notepad();
    }
}
