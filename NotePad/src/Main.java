import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

//Class Name & file name should be same only if class is declared public
//below is a normal class
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
        //-----------(name can be written here in brackets)

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
        f2.addActionListener(this); //"this" because it will work for only that option i.e for Save
        f3.addActionListener(this); //"this" because it will work for only that option i.e for Open
        f4.addActionListener(this); //"this" because it will work for only that option i.e for Print

        //till here menu is created  but not combined with main menu
        //now we are adding to the main menu ( Under File )
        //adding the options to the file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //2. For Edit Menu
        //creating a edit menu
        JMenu edit = new JMenu("Edit");
        //when we click file submenu appears
        //creating the options/submenu for file
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        //adding actionListeners to each of the options
        e1.addActionListener(this); //"this" because it will work for only that option i.e for Cut
        e2.addActionListener(this); //"this" because it will work for only that option i.e for Copy
        e3.addActionListener(this); //"this" because it will work for only that option i.e for Paste


        //till here menu is created  but not combined with main menu
        //now we are adding to the main menu( Under Edit )
        //adding the options to the edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        //3.Adding Close as a button because for close menu is not required
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this); //"this" because it will work for only that option i.e for close

        //Adding File , Edit & Close into the Main MenuBar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);

        //till here we have created menubar(which include: file,edit,close)
        //but initially we created jframe, textarea, menubar
        //till now there is no connection between jframe,textarea & menubar

        //now creating connection
        //adding menubar to the frame
        f.setJMenuBar(menubar); //swing function

        //adding text area to the frame
        f.add(t);

        //set the size of the frame
        f.setSize(1280,720); //resolution in pixel

        //bydefault the frame is set to not visible
        //to make it visible
        f.show(); /* makes the frame visible to the user */
        //or
        //f.setVisible(true); //bydefault the frame is set to not visible i.e false
        //f.show or f.setVisible(true) both works the same


    }


    /*
    * Action performed method:
    * whenever a user click file button or anything, a new window gets opened,so the program knows from the
    * action listener, basically whenever user clicks it will calls the actionperformed functiont*/

    //to add the functionalities;
    public void actionPerformed(ActionEvent e) //Doubt: Have to write "actionPerformed" instead of "ActionPerformed"
    //it will have a argument "ActionEvent"
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
                //creating the object of jfilechooser class with starting path of "C:\Users\Sting\Documents"
                JFileChooser j = new JFileChooser("C:\\Users\\Sting\\Documents"); //it is function of swing
                 // we have a set starting directory path
                //but have not done anything to spawn the dialog box
                //now need to spawn the dialog box
                int r = j.showSaveDialog(null); //argument here means where you want to spawn dialog box
                //null is because we want to show it in a single/same frame we don't have multiple frames working.
                // r will store 0 or 1 here 0 means user clicked "save",1 means user haven't.
                //r contains status of dialogbox
                if(r==0)
                {
                    //adding JFileChooser obj(j) j is only the path/pointer  ,
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
                //creating the object of jfilechooser class with starting path of "C:\Users\Sting\Documents"
                JFileChooser ji = new JFileChooser("C:\\Users\\Sting\\Documents"); //it is function of swing
                // we have a set starting directory path
                //but have not done anything to spawn the dialog box
                //now need to spawn the dialog box
                int ri = ji.showOpenDialog(null); //argument here means where you want to spawn dialog box
                //null is because we want to show it in a single/same frame we don't have multiple frames working.
                // r will store 0 or 1 here 0 means user clicked "save",1 means user haven't.
                //r contains status of dialogbox
                if(ri==0)
                {
                    //adding JFileChooser obj(j) j is only the path/pointer  ,
                    // getSelectedFile(): get me the file ,
                    // getAbsolutePath(): this get me total path to the file
                    //declare a file object and store file location
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());

                    try {
                        FileReader fr = new FileReader(fi);
                        BufferedReader br= new BufferedReader(fr);
                        String s1="",s2="";
                        s1=br.readLine();
                        while ((s2= br.readLine())!=null)
                        {
                            s1=s2+"\n";
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
                    /*system inbuilt function in swing
                    prints all the text inside the text area
                    it will open a dialog box */
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
