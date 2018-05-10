import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * 
 * @author Shayan
 *
 * A Basic text editor program.
 * 
 */
@SuppressWarnings("serial")
public class TextEditor extends JFrame {
	
	// initialize variables
	JTextArea textArea = new JTextArea(44, 60);								// text area
	JScrollPane scrollPane = new JScrollPane(textArea);						// scroll pane
	JMenuBar menu = new JMenuBar();											// menu bar
	JMenu file = new JMenu("File");											// file menu
	JFileChooser fileChooser = new JFileChooser();							// file chooser
	FileFilter textFileFilter = new FileNameExtensionFilter("text","txt");	// text file filter
	
	// primary constructor
	public TextEditor() {
		
		// add components
		menu.add(file);														// add file menu to menu bar
		file.add(Open);														// add open command to file menu
		file.add(Save);														// add save command to file menu
		file.add(Exit);														// add exit command to file menu
		fileChooser.setFileFilter(textFileFilter);							// set file chooser filter to text file filter
		
		// configure JFrame
		this.setJMenuBar(menu);												// add menu bar
		this.add(scrollPane);												// add scroll pane
		this.pack();														// resize to fit components
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);						// exit program on close
		this.setVisible(true);												// visibility
		
	}
	
	// define File > Open functionality
	Action Open = new AbstractAction("Open") {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				
				open(fileChooser.getSelectedFile().getAbsolutePath());		// call open method with filepath of chosen file
				
			}
			
		}
		
	};
	
	// define File > Save functionality
	Action Save = new AbstractAction("Save") {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				
				save();														// call save method
			
			}
			
		}
		
	};
	
	// define and implement File > Exit functionality
	Action Exit = new AbstractAction("Exit") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.exit(0);													// terminate program
			
		}
		
	};
	
	// implement File > Open functionality
	public void open(String fileName) {

		try {
			
			FileReader fileReader = new FileReader(fileName);				// read specified file
			textArea.read(fileReader, null);								// copy file text to text area
			fileReader.close();												// close file reader
			this.setTitle(fileName);										// set frame title to filename
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
			
	}
	
	// implement File > Save functionality
	public void save() {
		
		try {
			
			FileWriter fileWriter = new FileWriter(							// specify file name
					fileChooser.getSelectedFile().getAbsolutePath()
					+ ".txt");
			textArea.write(fileWriter);										// copy text from text area to file writer
			fileWriter.close();												// close file writer
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
			
	}
	
	// main
	public static void main(String[] args) {
		
		new TextEditor();													// primary constructor call / program start
	    
	}

}
