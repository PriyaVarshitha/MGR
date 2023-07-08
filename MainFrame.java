package Training;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
public class MainFrame extends Frame {
    private TextField empNoTextField;
    private TextField nameTextField;
    private TextField jobTextField;
    private TextField salaryTextField;
    private Choice departmentChoice;
    private int currentIndex = 0;
    private int totalRecords = 0;
    private List<String[]> records = new ArrayList<>();
    private boolean isEditMode = false;
    private static final String DATA_FILE_PATH = "D:\\Eclipse_workplace\\JavaTraining\\src\\Training\\Emp.txt";
    private static final Font BOLD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    private Button addButton;
    private Button saveButton;
    private Button delButton;
    private Button searchButton;
    public MainFrame() {
        setTitle("Employee Master Entry");
        setSize(300, 300);
        setLayout(new BorderLayout());
        initializeGUI();
        readDataFromFile(DATA_FILE_PATH);
        setModeUI();
        this.setBackground(Color.WHITE);
        setVisible(true);
    }
    private void initializeGUI() {
        Panel panel = new Panel(new GridLayout(6, 2));
        Label empNoLabel = createBoldLabel("Emp No:");
        Label nameLabel = createBoldLabel("Name:");
        Label jobLabel = createBoldLabel("Job:");
        Label salaryLabel = createBoldLabel("Salary:");
        Label departmentLabel = createBoldLabel("Department:");
        empNoTextField = new TextField();
        nameTextField = new TextField();
        jobTextField = new TextField();
        salaryTextField = new TextField();
        departmentChoice = new Choice();
        departmentChoice.add("Development");
        departmentChoice.add("Sales");
        departmentChoice.add("Testing");
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
        add(panel, BorderLayout.CENTER);
        Panel buttonPanel = new Panel(new GridLayout(5, 2));
        // Add other buttons here...
        String[] buttonNames = { "First", "Next", "Prev", "Last", "Add", "Edit", "Del", "Save", "Search", "Clear",
                "Exit" };
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        for (String buttonName : buttonNames) {
            Button button = new Button(buttonName);
            button.setFont(buttonFont);
            button.setBackground(Color.YELLOW);
            button.setForeground(Color.BLACK);
            switch (buttonName) {
                case "Exit":
                    button.addActionListener(e -> dispose());
                    break;
                case "Clear":
                    button.addActionListener(e -> clearFields());
                    break;
                case "Add":
                	 addButton = button;
                     addButton.setEnabled(false);
                     addButton.addActionListener(e -> addDataToFile());
                     break;
                case "First":
                    button.addActionListener(e -> showFirstRecord());
                    break;
                case "Last":
                    button.addActionListener(e -> showLastRecord());
                    break;
                case "Next":
                    button.addActionListener(e -> showNextRecord());
                    break;
                case "Prev":
                    button.addActionListener(e -> showPreviousRecord());
                    break;
                case "Edit":
                    button.addActionListener(e -> toggleEditMode());
                    break;
                case "Save":
                	 saveButton = button;
                     saveButton.setEnabled(false);
                     saveButton.addActionListener(e -> saveDataToFile());
                     break;
                case "Del":
                	delButton = button;
                    delButton.setEnabled(false);
                    delButton.addActionListener(e -> deleteCurrentRecord());
                    break;
                case "Search":
                	searchButton = button;
                    searchButton.setEnabled(false);
                    searchButton.addActionListener(e -> searchRecord());
                    break;
            }
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.SOUTH); 
    }
    private Label createBoldLabel(String text) {
        Label label = new Label(text);
        label.setFont(BOLD_FONT);
        label.setForeground(Color.BLUE);
        return label;
    }
    private void readDataFromFile(String filePath) {
        try (Scanner sc = new Scanner(new File(filePath))) {
            records.clear();
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
                    records.add(data);
                    totalRecords++;  
            }
            if (totalRecords > 0) {
                currentIndex = 0;
                displayRecord(currentIndex);
            } else {
                clearFields();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }
    private void writeDataToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to update data file: " + e.getMessage());
        }
    }
    private void clearFields() {
        empNoTextField.setText("");
        nameTextField.setText("");
        jobTextField.setText("");
        salaryTextField.setText("");
        departmentChoice.select(0);
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
            writeDataToFile(DATA_FILE_PATH);
            System.out.println("Data added to file successfully!");
            clearFields();
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
        empNoTextField.setEditable(isEditMode);
        nameTextField.setEditable(isEditMode);
        jobTextField.setEditable(isEditMode);
        salaryTextField.setEditable(isEditMode);
        departmentChoice.setEnabled(isEditMode);
    }
    private void deleteCurrentRecord() {
        if (totalRecords > 0) {
            records.remove(currentIndex);
            totalRecords--;
            writeDataToFile(DATA_FILE_PATH);
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
    private void saveDataToFile() {
        String empNo = empNoTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String job = jobTextField.getText().trim();
        String salary = salaryTextField.getText().trim();
        String department = departmentChoice.getSelectedItem();
        if (!empNo.isEmpty() && !name.isEmpty() && !job.isEmpty() && !salary.isEmpty()) {
            String[] data = { empNo, name, job, salary, department };
            records.set(currentIndex, data);
            writeDataToFile(DATA_FILE_PATH);
            System.out.println("Data saved to file successfully!");
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
        isEditMode = !isEditMode;
        setModeUI();
        updateButtonEnabledStates();
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
        } else {
            clearFields();
            System.out.println("Please enter a search term.");
        }
    }
    private void updateButtonEnabledStates() {
        addButton.setEnabled(isEditMode);
        saveButton.setEnabled(isEditMode);
        delButton.setEnabled(isEditMode);
        searchButton.setEnabled(isEditMode);
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}
