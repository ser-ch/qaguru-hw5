package qa.demo.data.enums;

public enum Subject {
    ARTS("Arts"),
    MATHS("Maths"),
    CHEMISTRY("Chemistry"),
    COMPUTER_SCIENCE("Computer Science");

    private final String subject;

    Subject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public static Subject fromString(String subject) {
        for (Subject subj : Subject.values()) {
            if (subj.subject.equals(subject)) {
                return subj;
            }
        }
        return null;
    }
}
