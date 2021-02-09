package com.fujitsu.ph.tsup.course.category.model;

import org.springframework.util.StringUtils;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */
public class CourseCategory {

    private Long id;
    private String category;
    private String detail;

    public CourseCategory() {

    }

    public CourseCategory(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.detail = builder.detail;
    }

    @Override
    public String toString() {
        return "CourseCategory [id=" + id + ", category=" + category + ", detail=" + detail + "]";
    }

    /**
     * Getter method for Course Category Id
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for Course Id
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for Course Category
     * 
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter method for Course
     * 
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter method for Course Category Detail
     * 
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Setter method for Course Detail
     * 
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Builder Class
     * 
     * @author a.batongbaca
     *
     */
    public static class Builder {

        private Long id;
        private String category;
        private String detail;

        /**
         * Builder Constructor
         * 
         * @param category
         */
        public Builder(String category) {

            validateCategory(category);
            this.category = category;
        }

        /**
         * Builder Constructor
         * 
         * @param id
         * @param category
         */
        public Builder(Long id, String category) {

            validateId(id);
            validateCategory(category);
            this.id = id;
            this.category = category;
        }

        /**
         * Builder Constructor
         * 
         * @param category
         * @param detail
         */
        public Builder(String category, String detail) {

            validateCategory(category);
            validateDetail(detail);
            this.category = category;
            this.detail = detail;
        }

        /**
         * Builder Constructor
         * 
         * @param id
         * @param category
         * @param detail
         */
        public Builder(Long id, String category, String detail) {
            validateId(id);
            validateCategory(category);
            validateDetail(detail);
            this.id = id;
            this.category = category;
            this.detail = detail;
        }

        /**
         * Builder Constructor
         * 
         * @param detail
         * @return
         */
        public Builder detail(String detail) {

            validateDetail(detail);
            this.detail = detail;
            return this;
        }

        public CourseCategory build() {
            return new CourseCategory(this);
        }

        /**
         * Validate course category name if null or empty
         * 
         * @param name Course Category Name
         */
        private void validateCategory(String category) {

            if (category.equals(null) || StringUtils.isEmpty(category)) {
                throw new IllegalArgumentException("Course category should not be empty");
            }
        }

        /**
         * Validate Course Category id if null or 0
         * 
         * @param id
         */
        private void validateId(Long id) {

            if (id == null || id == 0) {
                throw new IllegalArgumentException("Id should not be empty");
            }
        }

        /**
         * Validate Course Category Detail if null or empty
         * 
         * @param detail Course Category
         */
        private void validateDetail(String detail) {

            if (detail.equals(null) || StringUtils.isEmpty(detail)) {
                throw new IllegalArgumentException("detail should not be empty");
            }
        }

    }
}
