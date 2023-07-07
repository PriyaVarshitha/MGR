package SmallProject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainFrame extends Frame {
	private TextField empNoTextField;
	private TextField nameTextField;
	private TextField jobTextField;
	private TextField salaryTextField;
	private Choice departmentChoice;
	private Button addButton;
	private Button saveButton;

	private int currentIndex = 0;
	private int totalRecords = 0;
	private List<String[]> records = new ArrayList<>();
	private boolean isEditMode = false; // Flag to track the mode (read or write)

	public MainFrame() {

		setTitle("Employee Master Entry");
		setSize(400, 400);
		setLayout(new BorderLayout());

		Panel panel = new Panel(new GridLayout(6, 2));

		// Labels
		Label empNoLabel = new Label("Emp No:");
		Label nameLabel = new Label("Name:");
		Label jobLabel = new Label("Job:");
		Label salaryLabel = new Label("Salary:");
		Label departmentLabel = new Label("Department:");

		// TextFields
		empNoTextField = new TextField();
		nameTextField = new TextField();
		jobTextField = new TextField();
		salaryTextField = new TextField();
		departmentChoice = new Choice();
		departmentChoice.add("Development");
		departmentChoice.add("Sales");
		departmentChoice.add("Testing");

		// Add labels and text fields to the panel
		panel.add(empNoLabel);
		panel.add(empNoTextField);
		panel.add(nameLabel);
		panel.add(nameTextField);
		panel.add(jobLabel);
		panel.add(jobTextField);
		panel.add(salaryLabel);
		panel.add(salaryTextField);
		panel.add(departmentLabel);
		panel.add(departmentChoice);

		// Add the panel to the frame
		add(panel, BorderLayout.CENTER);

		// Buttons
		Panel buttonPanel = new Panel(new GridLayout(5, 2));

		String[] buttonNames = { "First", "Next", "Prev", "Last", "Add", "Edit", "Del", "Save", "Search", "Clear",
				"Exit" };

		for (String buttonName : buttonNames) {
			Button button = new Button(buttonName);
			if (buttonName.equals("Exit")) {
				button.addActionListener(e -> dispose());
			} else if (buttonName.equals("Clear")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clearFields();
					}
				});
			} else if (buttonName.equals("Add")) {
				addButton = button; // Store the "Add" button reference
				// Disable the "Add" button by default
				addButton.setEnabled(false);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addDataToFile();
					}
				});
			} else if (buttonName.equals("First")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showFirstRecord();
					}
				});
			} else if (buttonName.equals("Last")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showLastRecord();
					}
				});
			} else if (buttonName.equals("Next")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showNextRecord();
					}
				});
			} else if (buttonName.equals("Prev")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showPreviousRecord();
					}
				});
			} else if (buttonName.equals("Edit")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						toggleEditMode(); // Action listener to toggle the mode (read or write)
					}
				});
			} else if (buttonName.equals("Del")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteCurrentRecord(); // Action listener to delete the current record
					}
				});
			} else if (buttonName.equals("Search")) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						searchRecord();
					}
				});
			} else if (buttonName.equals("Save")) {
				saveButton = button; // Store the "Save" button reference
				// Disable the "Save" button by default
				saveButton.setEnabled(false);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveDataToFile();
					}
				});
			}

			buttonPanel.add(button);
		}

		// Add the button panel to the frame
		add(buttonPanel, BorderLayout.SOUTH);

		// Validate input for empNoTextField
		empNoTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		// Validate input for salaryTextField
		salaryTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == '.' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		// Window listener to close the window
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		readAndPopField("D:\\TEST1\\King\\src\\SmallProject\\Emp.txt");

		setModeUI(); // Set the initial mode (read mode)

		setVisible(true);
	}

	private void clearFields() {
		empNoTextField.setText("");
		nameTextField.setText("");
		jobTextField.setText("");
		salaryTextField.setText("");
		departmentChoice.select(0);
	}

	private void readAndPopField(String fileName) {
		try {
			File file = new File(fileName);
			Scanner sc = new Scanner(file);

			records.clear();
			currentIndex = 0;
			totalRecords = 0;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				String[] data = new String[tokenizer.countTokens()];

				int index = 0;
				while (tokenizer.hasMoreTokens()) {
					data[index] = tokenizer.nextToken();
					index++;
				}

				if (data.length == 5) {
					records.add(data);
					totalRecords++;
				}
			}

			sc.close();

			if (totalRecords > 0) {
				currentIndex = 0;
				displayRecord(currentIndex);
			} else {
				clearFields();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		}
	}

	private void addDataToFile() {
		String empNo = empNoTextField.getText().trim();
		String name = nameTextField.getText().trim();
		String job = jobTextField.getText().trim();
		String salary = salaryTextField.getText().trim();
		String department = departmentChoice.getSelectedItem();

		if (!empNo.isEmpty() && !name.isEmpty() && !job.isEmpty() && !salary.isEmpty()) {
			String[] data = { empNo, name, job, salary, department };
			records.add(data);
			totalRecords++;

			try {
				File file = new File("D:\\TEST1\\King\\src\\SmallProject\\Emp.txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
				writer.write(String.join(",", data));
				writer.newLine();
				writer.close();

				System.out.println("Data added to file successfully!");
				clearFields();
			} catch (IOException e) {
				System.out.println("Failed to write data to file: " + e.getMessage());
			}
		} else {
			System.out.println("Please fill in all fields!");
		}
	}

	private void showFirstRecord() {
		if (totalRecords > 0) {
			if (currentIndex == 0) {
				System.out.println("Already at the first record.");
			} else {
				currentIndex = 0;
				displayRecord(currentIndex);
			}
		}
	}

	private void showLastRecord() {
		if (totalRecords > 0) {
			if (currentIndex == totalRecords - 1) {
				System.out.println("Already at the last record.");
			} else {
				currentIndex = totalRecords - 1;
				displayRecord(currentIndex);
			}
		}
	}

	private void showNextRecord() {
		if (totalRecords > 0) {
			if (currentIndex == totalRecords - 1) {
				System.out.println("Already at the last record.");
			} else {
				currentIndex++;
				displayRecord(currentIndex);
			}
		}
	}

	private void showPreviousRecord() {
		if (totalRecords > 0) {
			if (currentIndex == 0) {
				System.out.println("Already at the first record.");
			} else {
				currentIndex--;
				displayRecord(currentIndex);
			}
		}
	}

	private void toggleEditMode() {
		isEditMode = !isEditMode; // Toggle the mode (read or write)
		setModeUI(); // Update the graphical representation of the mode
		addButton.setEnabled(isEditMode);
		saveButton.setEnabled(isEditMode);
	}

	private void deleteCurrentRecord() {
		if (totalRecords > 0) {
			records.remove(currentIndex);
			totalRecords--;
			System.out.println("Record deleted succesfully.");

			updateDataFile();

			if (totalRecords > 0) {
				if (currentIndex >= totalRecords) {
					currentIndex = totalRecords - 1;
				}
				displayRecord(currentIndex);
			} else {
				clearFields();
				System.out.println("All records deleted.");
			}
		}
	}

	private void searchRecord() {
		String searchTerm = empNoTextField.getText().trim();

		if (!searchTerm.isEmpty()) {
			int foundIndex = -1;

			for (int i = 0; i < totalRecords; i++) {
				String[] record = records.get(i);
				if (record[0].equals(searchTerm)) {
					foundIndex = i;
					break;
				}
			}

			if (foundIndex != -1) {
				currentIndex = foundIndex;
				displayRecord(currentIndex);
				System.out.println("Record found.");
			} else {
				clearFields();
				System.out.println("Record not found.");
			}
		}
	}

	private void updateDataFile() {
		try {
			File file = new File("D:\\TEST1\\King\\src\\SmallProject\\Emp.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (String[] record : records) {
				writer.write(String.join(",", record));
				writer.newLine();
			}

			writer.close();
		} catch (IOException e) {
			System.out.println("Failed to update data file: " + e.getMessage());
		}
	}

	private void saveDataToFile() {
		String empNo = empNoTextField.getText().trim();
		String name = nameTextField.getText().trim();
		String job = jobTextField.getText().trim();
		String salary = salaryTextField.getText().trim();
		String department = departmentChoice.getSelectedItem();

		if (!empNo.isEmpty() && !name.isEmpty() && !job.isEmpty() && !salary.isEmpty()) {
			String[] data = { empNo, name, job, salary, department };
			records.add(data);
			totalRecords++;

			try {
				File file = new File("D:\\TEST1\\King\\src\\SmallProject\\Emp.txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
				writer.write(String.join(",", data));
				writer.newLine();
				writer.close();

				System.out.println("Data saved to file successfully!");
				clearFields();
			} catch (IOException e) {
				System.out.println("Failed to write data to file: " + e.getMessage());
			}
		} else {
			System.out.println("Please fill in all fields!");
		}
	}

	private void displayRecord(int index) {
		if (index >= 0 && index < totalRecords) {
			String[] data = records.get(index);
			empNoTextField.setText(data[0].trim());
			nameTextField.setText(data[1].trim());
			jobTextField.setText(data[2].trim());
			salaryTextField.setText(data[3].trim());
			departmentChoice.select(data[4].trim());
		}
	}

	private void setModeUI() {
		if (isEditMode) {
			// Enable text fields and choice field
			empNoTextField.setEditable(true);
			nameTextField.setEditable(true);
			jobTextField.setEditable(true);
			salaryTextField.setEditable(true);
			departmentChoice.setEnabled(true);
		} else {
			// Disable text fields and choice field
			empNoTextField.setEditable(false);
			nameTextField.setEditable(false);
			jobTextField.setEditable(false);
			salaryTextField.setEditable(false);
			departmentChoice.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
}
