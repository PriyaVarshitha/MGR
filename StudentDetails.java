// Abstract class representing a University student
abstract class Student {
    protected int rollNo;
    protected String name;

    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public abstract void displayProfile();
    public abstract double calculateSemesterFee();
}

// B.Tech CSE Student
class CSEStudent extends Student {
    private String specialization;
    private byte engDrawingMarks;
    private byte engMathsMarks;
    private byte programmingScore;

    public CSEStudent(int rollNo, String name, String specialization, byte engDrawingMarks, byte engMathsMarks, byte programmingScore) {
        super(rollNo, name);
        this.specialization = specialization;
        this.engDrawingMarks = engDrawingMarks;
        this.engMathsMarks = engMathsMarks;
        this.programmingScore = programmingScore;
    }

    public byte getEngDrawingMarks() {
        return engDrawingMarks;
    }

    public byte getEngMathsMarks() {
        return engMathsMarks;
    }

    public byte getProgrammingScore() {
        return programmingScore;
    }

    @Override
    public void displayProfile() {
        System.out.println("CSE Student Profile:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
    }

    @Override
    public double calculateSemesterFee() {
        // Calculate the semester fee for CSE student
        // Implementation here
        return 0.0;
    }
}

// B.Tech ECE Student
class ECEStudent extends Student {
    private String industrialVisitInfo;
    private byte engDrawingMarks;
    private byte engMathsMarks;
    private byte industrialVisitScore;

    public ECEStudent(int rollNo, String name, String industrialVisitInfo, byte engDrawingMarks, byte engMathsMarks, byte industrialVisitScore) {
        super(rollNo, name);
        this.industrialVisitInfo = industrialVisitInfo;
        this.engDrawingMarks = engDrawingMarks;
        this.engMathsMarks = engMathsMarks;
        this.industrialVisitScore = industrialVisitScore;
    }

    public byte getEngDrawingMarks() {
        return engDrawingMarks;
    }

    public byte getEngMathsMarks() {
        return engMathsMarks;
    }

    public byte getIndustrialVisitScore() {
        return industrialVisitScore;
    }

    @Override
    public void displayProfile() {
        System.out.println("ECE Student Profile:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Industrial Visit Info: " + industrialVisitInfo);
    }

    @Override
    public double calculateSemesterFee() {
        // Calculate the semester fee for ECE student
        // Implementation here
        return 0.0;
    }
}

// MBA Student
class MBAStudent extends Student {
    private String internshipInfo;
    private byte orgBehaviourMarks;
    private byte riskManagementMarks;
    private byte internshipScore;

    public MBAStudent(int rollNo, String name, String internshipInfo, byte orgBehaviourMarks, byte riskManagementMarks, byte internshipScore) {
        super(rollNo, name);
        this.internshipInfo = internshipInfo;
        this.orgBehaviourMarks = orgBehaviourMarks;
        this.riskManagementMarks = riskManagementMarks;
        this.internshipScore = internshipScore;
    }

    public byte getOrgBehaviourMarks() {
        return orgBehaviourMarks;
    }

    public byte getRiskManagementMarks() {
        return riskManagementMarks;
    }

    public byte getInternshipScore() {
        return internshipScore;
    }

    @Override
    public void displayProfile() {
        System.out.println("MBA Student Profile:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Internship Info: " + internshipInfo);
    }

    @Override
    public double calculateSemesterFee() {
        // Calculate the semester fee for MBA student
        // Implementation here
        return 0.0;
    }
}

// MHRM Student
class MHRMStudent extends Student {
    private String caseStudyTitle;
    private byte orgBehaviourMarks;
    private byte riskManagementMarks;
    private byte caseStudyScore;

    public MHRMStudent(int rollNo, String name, String caseStudyTitle, byte orgBehaviourMarks, byte riskManagementMarks, byte caseStudyScore) {
        super(rollNo, name);
        this.caseStudyTitle = caseStudyTitle;
        this.orgBehaviourMarks = orgBehaviourMarks;
        this.riskManagementMarks = riskManagementMarks;
        this.caseStudyScore = caseStudyScore;
    }

    public byte getOrgBehaviourMarks() {
        return orgBehaviourMarks;
    }

    public byte getRiskManagementMarks() {
        return riskManagementMarks;
    }

    public byte getCaseStudyScore() {
        return caseStudyScore;
    }

    @Override
    public void displayProfile() {
        System.out.println("MHRM Student Profile:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Case Study Title: " + caseStudyTitle);
    }

    @Override
    public double calculateSemesterFee() {
        // Calculate the semester fee for MHRM student
        // Implementation here
        return 0.0;
    }
}

// StudentPerformance class
class StudentPerformance {
    public void performance(Student student) {
        student.displayProfile();
        System.out.println("Semester Fee: " + student.calculateSemesterFee());
        System.out.println("Semester Scores/Marks:");
        
        if (student instanceof CSEStudent) {
            CSEStudent cseStudent = (CSEStudent) student;
            // Display CSE student-specific scores/marks
            System.out.println("Engineering Drawing Marks: " + cseStudent.getEngDrawingMarks());
            System.out.println("Engineering Maths Marks: " + cseStudent.getEngMathsMarks());
            System.out.println("Programming Score: " + cseStudent.getProgrammingScore());
        } else if (student instanceof ECEStudent) {
            ECEStudent eceStudent = (ECEStudent) student;
            // Display ECE student-specific scores/marks
            System.out.println("Engineering Drawing Marks: " + eceStudent.getEngDrawingMarks());
            System.out.println("Engineering Maths Marks: " + eceStudent.getEngMathsMarks());
            System.out.println("Industrial Visit Score: " + eceStudent.getIndustrialVisitScore());
        } else if (student instanceof MBAStudent) {
            MBAStudent mbaStudent = (MBAStudent) student;
            // Display MBA student-specific scores/marks
            System.out.println("Org Behaviour Marks: " + mbaStudent.getOrgBehaviourMarks());
            System.out.println("Risk Management Marks: " + mbaStudent.getRiskManagementMarks());
            System.out.println("Internship Score: " + mbaStudent.getInternshipScore());
        } else if (student instanceof MHRMStudent) {
            MHRMStudent mhrmStudent = (MHRMStudent) student;
            // Display MHRM student-specific scores/marks
            System.out.println("Org Behaviour Marks: " + mhrmStudent.getOrgBehaviourMarks());
            System.out.println("Risk Management Marks: " + mhrmStudent.getRiskManagementMarks());
            System.out.println("Case Study Score: " + mhrmStudent.getCaseStudyScore());
        }
    }
}

// StudentDemo class
class StudentDetails {
    public static void main(String[] args) {
        StudentPerformance studentPerformance = new StudentPerformance();
        
        // Create different student objects
        CSEStudent cseStudent = new CSEStudent(1, "John", "AI", (byte) 90, (byte) 95, (byte) 85);
        ECEStudent eceStudent = new ECEStudent(2, "Jane", "Company XYZ", (byte) 80, (byte) 85, (byte) 90);
        MBAStudent mbaStudent = new MBAStudent(3, "Mike", "Company ABC", (byte) 85, (byte) 90, (byte) 95);
        MHRMStudent mhrmStudent = new MHRMStudent(4, "Mary", "Case Study XYZ", (byte) 80, (byte) 75, (byte) 85);
        
        // Display performance of different students
        studentPerformance.performance(cseStudent);
        System.out.println();
        studentPerformance.performance(eceStudent);
        System.out.println();
        studentPerformance.performance(mbaStudent);
        System.out.println();
        studentPerformance.performance(mhrmStudent);
    }
}
