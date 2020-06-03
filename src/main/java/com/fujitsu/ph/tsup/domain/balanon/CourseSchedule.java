package com.fujitsu.ph.tsup.domain.balanon;

public class CourseSchedule {

    private Long Id;
    private Long CourseId;
    private Long InstructionId;
    private Long VenueId;
    private int MinimumParti;
    private int MaxiParti;
    private String Status;

    private CourseSchedule(Builder builder) {
        this.Id = builder.Id;
        this.CourseId = builder.CourseId;
        this.InstructionId = builder.InstructionId;
        this.VenueId = builder.VenueId;
        this.MinimumParti = builder.MinimumParti;
        this.MaxiParti = builder.MaxiParti;
        this.Status = builder.Status;
    }

    public Long getId() {
        return Id;
    }

    public Long getCourseId() {
        return CourseId;
    }

    public Long getInstructionId() {
        return InstructionId;
    }

    public Long getVenueId() {
        return VenueId;
    }

    public int getMinimumParti() {
        return MinimumParti;
    }

    public int getMaxiParti() {
        return MaxiParti;
    }

    public String getStatus() {
        return Status;
    }

    public static class Builder {
        private Long Id;
        private Long CourseId;
        private Long InstructionId;
        private Long VenueId;
        private int MinimumParti;
        private int MaxiParti;
        private String Status;

        public Builder(Long CourseId, Long InstructionId, Long VenueId, int MinimumParti, int MaxiParti,
                String Status) {
            validateCourseId(CourseId);
            validateInstructionId(InstructionId);
            validateVenueId(VenueId);
            validateMinimumParti(MinimumParti);
            validateMaxiParti(MaxiParti);
            validateStatus(Status);

            this.CourseId = CourseId;
            this.InstructionId = InstructionId;
            this.VenueId = VenueId;
            this.MinimumParti = MinimumParti;
            this.MaxiParti = MaxiParti;
            this.Status = Status;
        }

        private void validateCourseId(Long CourseId) {
            if (CourseId == null || CourseId == 0) {
                throw new IllegalArgumentException("Please provide the appropriate Course Id.");
            }
        }

        private void validateInstructionId(Long InstructionId) {
            if (InstructionId == null || InstructionId == 0) {
                throw new IllegalArgumentException("Please provide the appropriate InstructionId.");
            }
        }

        private void validateVenueId(Long VenueId) {
            if (VenueId == null || VenueId == 0) {
                throw new IllegalArgumentException("Please provide the appropriate VenueId.");
            }
        }

        private void validateMinimumParti(int MinimumParti) {
            if (MinimumParti < 1 || MinimumParti > 99999) {
                throw new IllegalArgumentException("Please provide the Minimum Number of Participants.");
            }
        }

        private void validateMaxiParti(int MaxiParti) {
            if (MaxiParti < 1 || MaxiParti > 99999) {
                throw new IllegalArgumentException("Please provide the Maximum Number of Participants.");
            }
        }

        private void validateStatus(String Status) {
            if (Status == null || Status.isEmpty() || Status.length() != 1) {
                throw new IllegalArgumentException("Status should not be empty");
            }

        }

    }
}
